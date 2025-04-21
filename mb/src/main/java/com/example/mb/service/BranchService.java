package com.example.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.model.Branch;
import com.example.mb.repository.BranchRepository;

@Service
public class BranchService {

		@Autowired
	    private BranchRepository branchRepository;  // Inject your BranchRepository

	    public Branch getById(long bid) {
	        return branchRepository.findById(bid).orElse(null);  // Fetch the branch by ID
	    }
	

}
