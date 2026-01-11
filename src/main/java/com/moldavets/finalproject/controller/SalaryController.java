package com.moldavets.finalproject.controller;

import com.moldavets.finalproject.model.Salary;
import com.moldavets.finalproject.service.SalaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryService SALARY_SERVICE;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        SALARY_SERVICE = salaryService;
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

        Salary salary = SALARY_SERVICE.getById(salaryId);

        if(salary == null) {
            return "redirect:/salaries/?salaryNotFound=" + salaryId;
        } else {
            model.addAttribute("salary", salary);
            return "salaries/salariesUpdateForm";
        }
    }

    @GetMapping("/search")
    public String searchSalary(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        if(query != null) {
            if(sort != null) {
                model.addAttribute("salaries", sortSalaries(SALARY_SERVICE.getAllByInputString(query), sort));
            } else {
                model.addAttribute("salaries", SALARY_SERVICE.getAllByInputString(query));
            }
            return "salaries/salaries";
        } else {
            return "redirect:/salaries/?searchError";
        }
    }

    @PostMapping("/update")
    public String updateSalary(@Valid @ModelAttribute("salary") Salary salary,
                               BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "redirect:/salaries/updateForm?salaryId=" + salary.getId() + "&error";
        }

        SALARY_SERVICE.update(salary);
        return "redirect:/salaries/?updatedSalaryId=" + salary.getId();
    }

    private static List<Salary> sortSalaries(List<Salary> salaries, String sort) {

        List<Salary> sortedSalaries;

         sortedSalaries = switch (sort) {

            case "IdOrderByAsc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getId))
                    .toList();

            case "idOrderByDesc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getId).reversed())
                    .toList();

            case "employeeIdOrderByAsc" -> salaries.stream()
                    .sorted(Comparator.comparing((Salary s) -> s.getEmployee().getId()))
                    .toList();

            case "employeeIdOrderByDesc" -> salaries.stream()
                    .sorted(Comparator.comparing((Salary s) -> s.getEmployee().getId()).reversed())
                    .toList();

            case "employeeNameOrderByAsc" -> salaries.stream()
                    .sorted(Comparator.comparing((Salary s) -> s.getEmployee().getFirstName()))
                    .toList();

            case "employeeNameOrderByDesc" -> salaries.stream()
                    .sorted(Comparator.comparing((Salary s) -> s.getEmployee().getFirstName()).reversed())
                    .toList();

            case "amountOrderByAsc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getAmount))
                    .toList();

            case "amountOrderByDesc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getAmount).reversed())
                    .toList();

            case "currencyOrderByAsc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getCurrency))
                    .toList();

            case "currencyOrderByDesc" -> salaries.stream()
                    .sorted(Comparator.comparing(Salary::getCurrency).reversed())
                    .toList();

            default -> salaries;
        };

        return sortedSalaries;
    }

}








