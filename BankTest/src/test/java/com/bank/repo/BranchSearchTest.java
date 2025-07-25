package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.entity.BranchEntity;
import com.bank.service.IBranchService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BranchSearchTest {

    @Autowired
    private IBranchRepo branchRepo;

    @Autowired
    private IBranchService branchService;

    private static BranchEntity savedBranch;

    @BeforeAll
    static void init(@Autowired IBranchRepo branchRepo) {
        BranchEntity branch = new BranchEntity();
        branch.setBranchName("Kuala Lumpur");
        branch.setBranchPostCode("67000");
        branch.setCreationDate(LocalDateTime.now());

        savedBranch = branchRepo.save(branch);
    }

    @Test
    @Order(1)
    void testSearchBranchByBranchName() {
        String branchName = "Kuala Lumpur";
        List<BranchEntity> entities = branchService.getAllBranchWithBranchName(branchName);

        assertNotNull(entities);
        assertFalse(entities.isEmpty());
        assertEquals("Kuala Lumpur", entities.get(0).getBranchName());
    }

    @Test
    @Order(2)
    void testSearchBranchBetweenDate() {
        LocalDateTime start = savedBranch.getCreationDate().minusDays(1);
        LocalDateTime end = savedBranch.getCreationDate().plusDays(1);

        List<BranchEntity> results = branchRepo.findByCreationDateBetween(start, end);

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
}
