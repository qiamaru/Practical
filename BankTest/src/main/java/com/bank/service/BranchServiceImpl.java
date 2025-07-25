package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.BranchEntity;
import com.bank.repo.IBranchRepo;
import com.bank.validation.BranchValidation;

@Service
public class BranchServiceImpl implements IBranchService {

    @Autowired
    private IBranchRepo branchRepo;

    @Override
    public List<BranchEntity> getAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public List<BranchEntity> getAllBranchWithBranchName(String branchName) {
        BranchValidation.validateBranchNameNotNull(branchName);
        return branchRepo.findByBranchName(branchName);
    }

    @Override
    public List<BranchEntity> getAllBranchWithDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return branchRepo.findByCreationDateBetween(startDate,endDate);
    }

    @Override
    public BranchEntity getBranchById(Long id) {
        return branchRepo.findById(id).orElse(null);
    }

    @Override
    public BranchEntity createBranch(BranchEntity branch) {
       BranchValidation.validateBranchNameNotNull(branch.getBranchName());
        return branchRepo.save(branch);
    }

    @Override
    public BranchEntity updateBranch(Long id, BranchEntity updatedBranch) {
        return branchRepo.findById(id).map(existing -> {
            existing.setBranchName(updatedBranch.getBranchName());
            existing.setBranchPostCode(updatedBranch.getBranchPostCode());
            return branchRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Branch not found"));
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepo.deleteById(id);
    }

}

