package com.example.itog2.controllers;

import com.example.itog2.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchController {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/v1/api/branch";
    
    @Autowired
    public BranchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getBranchesPage(Model model) {
        // Получение списка веток через API
        Branch[] branchesArray = restTemplate.getForObject(apiUrl, Branch[].class);
        List<Branch> branches = Arrays.asList(branchesArray);
        model.addAttribute("branches", branches);
        return "branches";
    }

    @PostMapping("/add")
    public String addBranch(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        // Создание новой ветки через API
        Branch newBranch = new Branch();
        newBranch.setName(name);
        newBranch.setAddress(address);
        newBranch.setPhoneNumber(phoneNumber);
        restTemplate.postForObject(apiUrl + "/add", newBranch, Branch.class);
        return "redirect:/branches";
    }

    @PostMapping("/update")
    public String updateBranch(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        // Обновление ветки через API
        Branch updatedBranch = new Branch();
        updatedBranch.setId(id);
        updatedBranch.setName(name);
        updatedBranch.setAddress(address);
        updatedBranch.setPhoneNumber(phoneNumber);
        restTemplate.put(apiUrl + "/update/" + id, updatedBranch);
        return "redirect:/branches";
    }

    @PostMapping("/delete")
    public String deleteBranch(@RequestParam Long id) {
        // Удаление ветки через API
        restTemplate.delete(apiUrl + "/delete/" + id);
        return "redirect:/branches";
    }
}

