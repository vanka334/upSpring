package com.example.itog2.controllers;


import com.example.itog2.models.Employee;
import com.example.itog2.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/v1/api/employee";
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    public EmployeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Получение списка сотрудников
    @GetMapping
    public String getEmployeesPage(Model model) {
        Employee[] employeesArray = restTemplate.getForObject(apiUrl, Employee[].class);
        model.addAttribute("employees", employeesArray);
        return "employees";
    }

    // Добавление нового сотрудника
    @PostMapping("/add")
    public String addEmployee(
            @RequestParam String firstName,
            @RequestParam String surname,
            @RequestParam(required = false) String secondName,
            @RequestParam Role post,
            @RequestParam Long branchId,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String password // Получаем пароль
    ) {
        // Генерация соли и хэширование пароля
        String hashPassword = password;
        String salt = "1";
        // Хэшируем пароль с использованием соли

        // Создание нового сотрудника
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(firstName);
        newEmployee.setSurname(surname);
        newEmployee.setSecondName(secondName);
        newEmployee.setPost(post);
        newEmployee.setPhoneNumber(phoneNumber);
        newEmployee.setEmail(email);
        newEmployee.setHashPassword(hashPassword); // Сохраняем хэш пароля
        newEmployee.setSalt(salt); // Сохраняем соль

        // Отправляем запрос на добавление сотрудника
        restTemplate.postForObject(apiUrl + "/add?idBranch=" + branchId, newEmployee, Employee.class);

        return "redirect:/employees"; // Перенаправляем после успешного добавления
    }

    // Обновление сотрудника
    @PostMapping("/update")
    public String updateEmployee(
            @RequestParam Long id,
            @RequestParam String firstName,
            @RequestParam String surname,
            @RequestParam(required = false) String secondName,
            @RequestParam Role post,
            @RequestParam Long branchId,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String password // Получаем новый пароль
    ) {
        // Генерация соли и хэширование пароля
             // Хэшируем новый пароль
        String hashPassword = password;
        String salt = "1";


        // Создание объекта для обновления сотрудника
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(id);
        updatedEmployee.setFirstName(firstName);
        updatedEmployee.setSurname(surname);
        updatedEmployee.setSecondName(secondName);
        updatedEmployee.setPost(post);
        updatedEmployee.setPhoneNumber(phoneNumber);
        updatedEmployee.setEmail(email);
        updatedEmployee.setHashPassword(hashPassword); // Обновляем хэш пароля
        updatedEmployee.setSalt(salt); // Обновляем соль

        // Отправляем запрос на обновление сотрудника
        restTemplate.put(apiUrl + "/update/" + id, updatedEmployee);

        return "redirect:/employees"; // Перенаправляем после успешного обновления
    }
}
