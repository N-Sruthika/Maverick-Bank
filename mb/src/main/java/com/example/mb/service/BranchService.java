package com.example.mb.service;

import com.example.mb.model.Branch;
import com.example.mb.repository.BranchRepository;
import com.example.mb.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    
    public Branch getBranchById(int id) {
        return branchRepository.findById(id).orElse(null);
    }

    
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    
    public Branch updateBranch(int id, Branch branch) {
        Optional<Branch> existing = branchRepository.findById(id);
        if (existing.isPresent()) {
            Branch b = existing.get();
            b.setBranchName(branch.getBranchName());
            b.setBranchAddress(branch.getBranchAddress());
            return branchRepository.save(b);
        } else {
            return null;
        }
    }

    
    public void deleteBranch(int id) {
        branchRepository.deleteById(id);
    }
}
