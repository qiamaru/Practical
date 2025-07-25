package com.bank.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.BranchEntity;


@Repository
public interface IBranchRepo extends JpaRepository<BranchEntity, Long> {
    List<BranchEntity> findByBranchName(String branchName);
    List<BranchEntity> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}