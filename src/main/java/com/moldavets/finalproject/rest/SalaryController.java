package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Salary;
import com.moldavets.finalproject.service.EmployeeService;
import com.moldavets.finalproject.service.SalaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryService SALARY_SERVICE;
    private final EmployeeService EMPLOYEE_SERVICE;

    @Autowired
    public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
        SALARY_SERVICE = salaryService;
        EMPLOYEE_SERVICE = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listSalaries(@RequestParam(value = "sort", required = false) String sort,
                               Model model) {

        List<Salary> salaries;

        if(sort != null) {
            salaries = switch (sort) {
                case "idOrderByASC" -> SALARY_SERVICE.getAllOrderByIdAsc();
                case "idOrderByDesc" -> SALARY_SERVICE.getAllOrderByIdDesc();
                case "employeeIdOrderByAsc" -> SALARY_SERVICE.getAllOrderByEmployeeIdAsc();
                case "employeeIdOrderByDesc" -> SALARY_SERVICE.getAllOrderByEmployeeIdDesc();
                case "employeeNameOrderByAsc" -> SALARY_SERVICE.getAllOrderByEmployeeFirstNameAsc();
                case "employeeNameOrderByDesc" -> SALARY_SERVICE.getAllOrderByEmployeeFirstNameDesc();
                case "amountOrderByAsc" -> SALARY_SERVICE.getAllOrderByAmountAsc();
                case "amountOrderByDesc" -> SALARY_SERVICE.getAllOrderByAmountDesc();
                case "currencyOrderByAsc" -> SALARY_SERVICE.getAllOrderByCurrencyAsc();
                case "currencyOrderByDesc" -> SALARY_SERVICE.getAllOrderByCurrencyDesc();
                default -> SALARY_SERVICE.getAll();
            };
        } else {
            salaries = SALARY_SERVICE.getAll();
        }

        model.addAttribute("salaries", salaries);
        return "salaries/salaries";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("salaryId") int salaryId,
                             Model model) {

        model.addAttribute("salary", SALARY_SERVICE.getById(salaryId));
        return "salaries/salariesUpdateForm";
    }

    @PostMapping("/update")
    public String updateSalary(@Valid @ModelAttribute("salary") Salary salary,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/salaries/updateForm?salaryId=" + salary.getId() + "&error";
        }
        SALARY_SERVICE.update(salary);
        return "redirect:/salaries/";
    }
}








