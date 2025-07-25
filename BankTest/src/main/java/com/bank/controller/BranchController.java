package com.bank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//Practical 8 - End to End Spring Boot 
//Create a branch entity with the data below
//a. BranchEntity.java

//branchID - Index ID auto generated
//branchName - length 100 - Not Nullable
//branchPostCode - length 30 - Not Nullable
//creationDate - Auto created when insert record - LocalDateTime 

//b. BranchController with get by ID, get all, add, and delete by ID only

//c. BranchDTO, BranchMapper and BranchMapperTest.java 
//Ensure table and column created on DB

//d. BranchService and BranchServiceImpl

//e. BranchRepo

//Additional Requirement
//f1. Exception Handling - When adding record, if the branchName is contain empty space, throw a DemoAppException with meaningful error message. i.e. Branch Name cannot be empty
//Enable package scanning "com.demo.exceptions"
//Ensure swagger output contains the DemoAppException type and error exist in the app.log file

//f2. BranchRepo - Basic Search Function
//Add a DOGET into controller above able to search by branchName
//Add on the method to the controller, service and repo
//Note: Refer to CustomerController.java getCustomersByDescriptionAndCreationDateBetween() as a sample

//f3. BranchRepo - Search Function by date in between
//Add a DOGET into controller above able to between date from and date to
//Add on the method to the controller, service and repo

//g - UnitTesting - Create a BranchSearchTest.java for f2 and f3 above.

import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.BranchEntity;
import com.bank.mapper.BranchMapper;
import com.bank.model.BranchDTO;
import com.bank.service.IBranchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final IBranchService branchService;

    private final BranchMapper branchMapper;

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDto) {
        var savedEntity = branchService.createBranch(branchMapper.toEntity(branchDto));
        return ResponseEntity.ok(branchMapper.toDto(savedEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        BranchEntity branchEntity = branchService.getBranchById(id);
        return ResponseEntity.ok(branchMapper.toDto(branchEntity));
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        var entities = branchService.getAllBranches();
        return ResponseEntity.ok(branchMapper.toDtoList(entities));
    }

    @GetMapping("/getAllBranchWithBranchName")
    public ResponseEntity<List<BranchDTO>> getAllBranchWithBranchName(@RequestParam String branchName) {
        var entities = branchService.getAllBranchWithBranchName(branchName);
        return ResponseEntity.ok(branchMapper.toDtoList(entities));
    }

    @GetMapping("/getAllBranchWithDateBetween")
    public ResponseEntity<List<BranchDTO>> getAllBranchWithDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        List<BranchEntity> entities = branchService.getAllBranchWithDateBetween(startDateTime, endDateTime);
        return ResponseEntity.ok(branchMapper.toDtoList(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDto) {
        var updatedEntity = branchService.updateBranch(id, branchMapper.toEntity(branchDto));
        return ResponseEntity.ok(branchMapper.toDto(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
