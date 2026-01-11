package com.moldavets.finalproject.controller;

import com.moldavets.finalproject.model.DateStamp;
import com.moldavets.finalproject.model.Employee;
import com.moldavets.finalproject.model.Salary;
import com.moldavets.finalproject.service.DateStampService;
import com.moldavets.finalproject.service.DepartmentService;
import com.moldavets.finalproject.service.EmployeeService;
import com.moldavets.finalproject.service.SalaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final SalaryService salaryService;
    private final DateStampService dateStampService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listEmployees(
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        /*In this function, I am required to use sql queries
        instead of Stream API as this is a databases lessons project*/

        List<Employee> employees;

        if(sort != null) {
            employees = switch (sort) {

                case "IdOrderByAsc" -> employeeService.getAllOrderByIdAsc();
                case "IdOrderByDesc" -> employeeService.getAllOrderByIdDesc();
                case "firstNameOrderByAsc" -> employeeService.getAllByOrderByFirstNameAsc();
                case "firstNameOrderByDesc" -> employeeService.getAllByOrderByFirstNameDesc();
                case "lastNameOrderByAsc" -> employeeService.getAllByOrderByLastNameAsc();
                case "lastNameOrderByDesc" -> employeeService.getAllByOrderByLastNameDesc();
                case "departmentOrderByAsc" -> employeeService.getAllByOrderByDepartmentAsc();
                case "departmentOrderByDesc" -> employeeService.getAllByOrderByDepartmentDesc();
                case "birthdayOrderByAsc" -> employeeService.getAllByOrderByBirthdayAsc();
                case "birthdayOrderByDesc" -> employeeService.getAllByOrderByBirthdayDesc();
                default -> employeeService.getAll();
            };
        } else {
            employees = employeeService.getAll();
        }

        model.addAttribute("employees", employees);
        return "employees/employees";
    }

    @GetMapping("/add")
    public String getAddEmployeePage(Model model) {
        if (departmentService.getAll().isEmpty()) {
           return "redirect:/employees/?departmentError";
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAll());
        return "employees/employeesAddForm";
    }

    @GetMapping("/updateForm")
    public String getUpdateEmployeePage(
            @RequestParam("employeeId") int employeeId,
            Model model) {
        Employee employee = employeeService.getById(employeeId);
        if (employee == null) {
            model.addAttribute("employeeNotFound", new Employee());
            return "redirect:/employees/?employeeNotFound=" + employeeId;
        } else {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.getAll());
            return "employees/employeesUpdateForm";
        }
    }

    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        if(query != null) {
            if(sort != null) {
                model.addAttribute("employees", sortEmployees(employeeService.getAllByInputString(query), sort));
            } else {
                model.addAttribute("employees", employeeService.getAllByInputString(query));
            }
            return "employees/employees";
        } else {
            return "redirect:/employees/?searchError";
        }
    }

    @GetMapping("/filter")
    public String filterEmployee(@RequestParam(value = "department", required = false) String department,
                                 @RequestParam(value = "birthday", required = false) String birthday,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 Model model) {

        List<Employee> employees = employeeService.getAll();

        if(department != null && birthday != null) {
            employees = employees.stream()
                    .filter(e-> e.getDepartment().startsWith(department) && e.getBirthday().startsWith(birthday))
                    .collect(Collectors.toList());
        } else if (department != null) {
            employees = employees.stream()
                    .filter(e-> e.getDepartment().startsWith(department))
                    .collect(Collectors.toList());
        } else if (birthday != null) {
            employees = employees.stream()
                    .filter(e-> e.getBirthday().startsWith(birthday))
                    .collect(Collectors.toList());
        } else {
            return "redirect:/employees/?filterError";
        }

        if(sort != null) {
            employees = sortEmployees(employees, sort);
        }

        model.addAttribute("employees", employees);
        return "employees/employees";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Employee employee,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "redirect:/employees/updateForm?employeeId=" + employee.getId() + "&error";
        }

        employeeService.save(employee);
        return "redirect:/employees/?updatedEmployeeId=" + employee.getId();
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "redirect:/employees/add?&error";
        }

        Salary tempSalary = employee.getSalary();
        tempSalary.setEmployee(employee);
        salaryService.save(tempSalary);

        DateStamp tempDateStamp = new DateStamp(
                employee,
                LocalDate.now().toString(),
                LocalDate.now().plusMonths(1).toString()
        );
        dateStampService.save(tempDateStamp);

        employee.setDate(tempDateStamp);

        employeeService.save(employee);
        return "redirect:/employees/?addedEmployeeId=" + employee.getId();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees/?deletedEmployeeId=" + employeeId;
    }

    private static List<Employee> sortEmployees(List<Employee> employees, String sort) {

        List<Employee> sortedEmployees;
        sortedEmployees = switch (sort) {

            case "IdOrderByAsc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getId))
                    .toList();

            case "IdOrderByDesc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getId).reversed())
                    .toList();

            case "firstNameOrderByAsc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getFirstName))
                    .toList();

            case "firstNameOrderByDesc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getFirstName).reversed())
                    .toList();

            case "lastNameOrderByAsc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getLastName))
                    .toList();

            case "lastNameOrderByDesc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getLastName).reversed())
                    .toList();

            case "departmentOrderByAsc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getDepartment))
                    .toList();

            case "departmentOrderByDesc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getDepartment).reversed())
                    .toList();

            case "birthdayOrderByAsc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getBirthday))
                    .toList();

            case "birthdayOrderByDesc" -> employees.stream()
                    .sorted(Comparator.comparing(Employee::getBirthday).reversed())
                    .toList();

            default -> employees;
        };
        return sortedEmployees;
    }

}
