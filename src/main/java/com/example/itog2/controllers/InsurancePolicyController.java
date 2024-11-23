package com.example.itog2.controllers;

import com.example.itog2.models.Client;
import com.example.itog2.models.Employee;
import com.example.itog2.models.InsurancePolicy;
import com.example.itog2.models.InsuranceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/insurancePolicy")
public class InsurancePolicyController {

    private final RestTemplate restTemplate;

    @Autowired
    public InsurancePolicyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String API_BASE_URL = "http://localhost:8080/v1/api/insurancePolicy";

    /**
     * Получение всех страховых полисов через API.
     */
    @GetMapping
    public String getAllInsurancePolicies(Model model) {

        Client[] client = restTemplate.getForObject("http://localhost:8080/v1/api/client/", Client[].class);
        Employee[] employee =  restTemplate.getForObject("http://localhost:8080/v1/api/employees/", Employee[].class);

        InsuranceProduct[] product = restTemplate.getForObject(
                "http://localhost:8080/v1/api/insuranceProducts/", InsuranceProduct[].class);

        InsurancePolicy[] policies = restTemplate.getForObject(API_BASE_URL, InsurancePolicy[].class);
        List<InsurancePolicy> insurancePolicies = Arrays.asList(policies);
        List<Client> clients = Arrays.asList(client);
        List<Employee> employees = Arrays.asList(employee);
        List<InsuranceProduct> insuranceProducts = Arrays.asList(product);

        model.addAttribute("insurancePolicies", insurancePolicies);
        model.addAttribute("clients", clients);
        model.addAttribute("employees", employees);
        model.addAttribute("products", insuranceProducts);
        return "insurancePolicy";
    }

    /**
     * Добавление нового страхового полиса через API.
     */
    @PostMapping("/add")
    public String addInsurancePolicy(
            @RequestParam Long clientId,
            @RequestParam String issueDate,
            @RequestParam String endDate,
            @RequestParam BigDecimal sumInsured,
            @RequestParam(required = false) Long employeeId,
            @RequestParam String status,
            @RequestParam List<Long> productIds) {

        Client client = restTemplate.getForObject("http://localhost:8080/v1/api/clients/" + clientId, Client.class);
        Employee employee = employeeId != null
                ? restTemplate.getForObject("http://localhost:8080/v1/api/employees/" + employeeId, Employee.class)
                : null;
        List<InsuranceProduct> products = Arrays.asList(restTemplate.postForObject(
                "http://localhost:8080/v1/api/insuranceProducts/bulk",
                productIds, InsuranceProduct[].class));

        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setClient(client);
        insurancePolicy.setIssueDate(LocalDateTime.parse(issueDate));
        insurancePolicy.setEndDate(LocalDateTime.parse(endDate));
        insurancePolicy.setSumInsured(sumInsured);
        insurancePolicy.setEmployee(employee);
        insurancePolicy.setStatus(status);
        insurancePolicy.setInsuranceProducts(products);

       restTemplate.postForObject(API_BASE_URL + "/add", insurancePolicy, InsurancePolicy.class);
        return "redirect:/insurancePolicy";
    }

    /**
     * Обновление страхового полиса через API.
     */
    @PostMapping("/update")
    public String updateInsurancePolicy(
            @RequestParam Long id,
            @RequestParam Long clientId,
            @RequestParam String issueDate,
            @RequestParam String endDate,
            @RequestParam BigDecimal sumInsured,
            @RequestParam(required = false) Long employeeId,
            @RequestParam String status,
            @RequestParam List<Long> productIds) {

        Client client = restTemplate.getForObject("http://localhost:8080/v1/api/clients/" + clientId, Client.class);
        Employee employee = employeeId != null
                ? restTemplate.getForObject("http://localhost:8080/v1/api/employees/" + employeeId, Employee.class)
                : null;
        List<InsuranceProduct> products = Arrays.asList(restTemplate.postForObject(
                "http://localhost:8080/v1/api/insuranceProducts/bulk",
                productIds, InsuranceProduct[].class));

        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setId(id);
        insurancePolicy.setClient(client);
        insurancePolicy.setIssueDate(LocalDateTime.parse(issueDate));
        insurancePolicy.setEndDate(LocalDateTime.parse(endDate));
        insurancePolicy.setSumInsured(sumInsured);
        insurancePolicy.setEmployee(employee);
        insurancePolicy.setStatus(status);
        insurancePolicy.setInsuranceProducts(products);

        restTemplate.put(API_BASE_URL + "/update/" + id, insurancePolicy);
        return "redirect:/insurancePolicy";
    }

    /**
     * Удаление страхового полиса через API.
     */
    @PostMapping("/delete")
    public String deleteInsurancePolicy(@RequestParam Long id) {
        restTemplate.delete(API_BASE_URL + "/delete/" + id);
        return "redirect:/insurancePolicy";
    }
}
