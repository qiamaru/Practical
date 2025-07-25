package com.bank.service;

import com.bank.entity.BranchEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IBranchService {
    List<BranchEntity> getAllBranches();
    List<BranchEntity> getAllBranchWithBranchName(String branchName);
    List<BranchEntity> getAllBranchWithDateBetween(LocalDateTime startDate, LocalDateTime endDat);
    BranchEntity getBranchById(Long id);
    BranchEntity createBranch(BranchEntity branch);
    BranchEntity updateBranch(Long id, BranchEntity updatedBranch);
    void deleteBranch(Long id);
}
