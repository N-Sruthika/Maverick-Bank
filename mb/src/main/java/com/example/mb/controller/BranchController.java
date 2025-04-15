package com.example.mb.controller;

import com.example.mb.model.Branch;
import com.example.mb.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/add")
    public ResponseEntity<?> createBranch(@RequestBody Branch branch) {
        return ResponseEntity.ok(branchService.createBranch(branch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable int id) {
        Branch branch = branchService.getBranchById(id);
        return branch != null ? ResponseEntity.ok(branch) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(@PathVariable int id, @RequestBody Branch branch) {
        Branch updated = branchService.updateBranch(id, branch);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable int id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
