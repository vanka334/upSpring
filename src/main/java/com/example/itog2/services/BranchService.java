package com.example.itog2.services;
import com.example.itog2.models.Branch;
import com.example.itog2.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService  {
    @Autowired
    private BranchRepository branchRepository;
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();

    }
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);


    }
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }
    public Branch updateBranch(Branch branch) {
        return branchRepository.save(branch);
    }
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }




}
