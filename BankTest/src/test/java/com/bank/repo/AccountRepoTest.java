// TODO: SpringBoot: Practical Bonus 1 - Unit Test for AccountRepo
// create unit test for create, delete and get account 
// use CustomerRepoTest as an example

package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.bank.entity.CustomerEntity;
import com.bank.entity.ProductEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.AccountEntity;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRepoTest {

    @Autowired
    private IAccountRepo accountRepo;

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private IProductRepo productRepo;

    private static CustomerEntity savedCustomer;
    private static ProductEntity savedProduct;
    private static AccountEntity savedAccount;

    @BeforeAll
    static void init(@Autowired ICustomerRepo customerRepo,
            @Autowired IProductRepo productRepo,
            @Autowired IAccountRepo accountRepo) {
        // Create and save customer
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC654321");
        customer.setLastname("Smith");
        customer.setSurname("Jane");
        customer.setDescription("Test Customer");
        customer.setCreationDate(LocalDateTime.now());
        savedCustomer = customerRepo.save(customer);

        // Create and save product
        ProductEntity product = new ProductEntity();
        product.setProductName("Savings Account");
        product.setDescription("Savings");
        savedProduct = productRepo.save(product);

        // Create and save account
        AccountEntity account = new AccountEntity();
        account.setAccountNumber("ACC654321");
        account.setBalance(5000.0);
        account.setCreationDate(LocalDateTime.now());
        account.setCustomerEntity(savedCustomer);
        account.setProductEntity(savedProduct);
        savedAccount = accountRepo.save(account);
    }

    @Test
    @Order(1)
    void testCreateAccount() {
        assertNotNull(savedAccount.getAccountID());
        assertEquals("ACC654321", savedAccount.getAccountNumber());
        assertEquals(5000.0, savedAccount.getBalance());
        assertNotNull(savedAccount.getCustomerEntity().getCustomerID());
        assertNotNull(savedAccount.getProductEntity().getProductID());
    }

    @Test
    @Order(2)
    void testFindAccountById() {
        Optional<AccountEntity> found = accountRepo.findById(savedAccount.getAccountID());

        assertTrue(found.isPresent());
        assertEquals("ACC654321", found.get().getAccountNumber());
        assertEquals(5000.0, found.get().getBalance());
    }

    // @Test
    // @Order(3)
    // void testDeleteAccount() {
    //     AccountEntity tempAccount = new AccountEntity();
    //     tempAccount.setAccountNumber("ACC999999");
    //     tempAccount.setBalance(1500.0);
    //     tempAccount.setCreationDate(LocalDateTime.now());
    //     tempAccount.setCustomerEntity(savedCustomer);
    //     tempAccount.setProductEntity(savedProduct);

    //     AccountEntity saved = accountRepo.save(tempAccount);

    //     assertThrows(DataIntegrityViolationException.class, () -> {
    //         accountRepo.deleteById(saved.getAccountID());
    //         accountRepo.flush(); // force immediate DB check
    //     });
    // }
}
