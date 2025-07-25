package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.BranchEntity;
import com.bank.model.BranchDTO;

@SpringBootTest
class BranchMapperTest {

    @Autowired
    private BranchMapper branchMapper;

    @Test
    void testEntityToDtoAndBack() {
        // Create test entity
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchID(100L);
        branchEntity.setBranchName("KL Central");
        branchEntity.setBranchPostCode("50470");
        branchEntity.setCreationDate(LocalDateTime.now());

        // Convert to DTO
        BranchDTO dto = branchMapper.toDto(branchEntity);
        assertNotNull(dto);
        assertNotNull(dto.getBranchID());
        assertNotNull(dto.getBranchName());
        assertNotNull(dto.getBranchPostCode());
        assertNotNull(dto.getCreationDate());

        // Convert back to Entity
        BranchEntity convertedBack = branchMapper.toEntity(dto);
        assertNotNull(convertedBack);
        assertNotNull(convertedBack.getBranchID());
        assertNotNull(convertedBack.getBranchName());
        assertNotNull(convertedBack.getBranchPostCode());
        assertNotNull(convertedBack.getCreationDate());
    }
}
