package com.example.itog2.api;

import com.example.itog2.models.Branch;
import com.example.itog2.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/branch")
public class BranchApi  {
    @Autowired
    private final  BranchService branchService;

    public BranchApi(BranchService branchService) {
        this.branchService = branchService;
    }
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }


    @GetMapping("/{id}")
    public Branch getBranch(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    @PostMapping("/add")
    public Branch addBranch(@RequestBody Branch branch) {
        return branchService.saveBranch(branch);
    }
    @PutMapping("/update/{id}")
    public Branch updateBranch(@RequestBody Branch branch) {
        return branchService.updateBranch(branch);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }


}
