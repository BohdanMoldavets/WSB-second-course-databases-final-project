package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "publicPage";
    }

    @GetMapping("/add")
    public String getAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeAddForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById((long) employeeId);
        return "redirect:/";
    }

}
