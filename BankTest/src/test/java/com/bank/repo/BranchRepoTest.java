package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.BranchEntity;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BranchRepoTest {

    @Autowired
    private IBranchRepo branchRepo;

    private static BranchEntity savedBranch;

    @BeforeAll
    static void init(@Autowired IBranchRepo branchRepo) {
        // Create and save branch
        BranchEntity branch = new BranchEntity();
        branch.setBranchName("Bukit Jalil");
        branch.setBranchPostCode("21000");
        branch.setCreationDate(LocalDateTime.now());
        savedBranch = branchRepo.save(branch);
    }

    @Test
    @Order(1)
    void testCreateBranch() {
        assertNotNull(savedBranch.getBranchID());
        assertEquals("Bukit Jalil", savedBranch.getBranchName());
        assertEquals("21000", savedBranch.getBranchPostCode());
    }

    @Test
    @Order(2)
    void testFindBranchById() {
        Optional<BranchEntity> found = branchRepo.findById(savedBranch.getBranchID());

        assertTrue(found.isPresent());
        assertEquals("Bukit Jalil", found.get().getBranchName());
        assertEquals("21000", found.get().getBranchPostCode());
    }

    @Test
    @Order(3)
    void testDeleteBranch() {
        BranchEntity tempBranch = new BranchEntity();
        tempBranch.setBranchName("Bukit Jalil");
        tempBranch.setBranchPostCode("55000");
        tempBranch.setCreationDate(LocalDateTime.now());

        BranchEntity saved = branchRepo.save(tempBranch);
        Long id = saved.getBranchID();

        branchRepo.deleteById(id);

        Optional<BranchEntity> deleted = branchRepo.findById(id);

        assertFalse(deleted.isPresent());  
    }

}
