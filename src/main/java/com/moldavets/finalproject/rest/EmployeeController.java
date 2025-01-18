package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.entity.Salary;
import com.moldavets.finalproject.service.DateStampService;
import com.moldavets.finalproject.service.DepartmentService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService EMPLOYEE_SERVICE;
    private final DepartmentService DEPARTMENT_SERVICE;
    private final SalaryService SALARY_SERVICE;
    private final DateStampService DATE_STAMP_SERVICE;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              DepartmentService departmentService,
                              SalaryService salaryService,
                              DateStampService dateStampService) {
        this.EMPLOYEE_SERVICE = employeeService;
        this.DEPARTMENT_SERVICE = departmentService;
        this.SALARY_SERVICE = salaryService;
        this.DATE_STAMP_SERVICE = dateStampService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listEmployees(
            @RequestParam(value = "sort", required = false) String sort,
            Model model ) {

        List<Employee> employees;
        if(sort != null) {
            employees = switch (sort) {
                case "IdOrderByAsc" -> EMPLOYEE_SERVICE.getAllOrderByIdAsc();
                case "IdOrderByDesc" -> EMPLOYEE_SERVICE.getAllOrderByIdDesc();
                case "firstNameOrderByAsc" -> EMPLOYEE_SERVICE.getAllByOrderByFirstNameAsc();
                case "firstNameOrderByDesc" -> EMPLOYEE_SERVICE.getAllByOrderByFirstNameDesc();
                case "lastNameOrderByAsc" -> EMPLOYEE_SERVICE.getAllByOrderByLastNameAsc();
                case "lastNameOrderByDesc" -> EMPLOYEE_SERVICE.getAllByOrderByLastNameDesc();
                case "departmentOrderByAsc" -> EMPLOYEE_SERVICE.getAllByOrderByDepartmentAsc();
                case "departmentOrderByDesc" -> EMPLOYEE_SERVICE.getAllByOrderByDepartmentDesc();
                case "birthdayOrderByAsc" -> EMPLOYEE_SERVICE.getAllByOrderByBirthdayAsc();
                case "birthdayOrderByDesc" -> EMPLOYEE_SERVICE.getAllByOrderByBirthdayDesc();
                default -> EMPLOYEE_SERVICE.getAll();
            };
        } else {
            employees = EMPLOYEE_SERVICE.getAll();
        }

        model.addAttribute("employees", employees);
        return "employees/employees";
    }

    @GetMapping("/add")
    public String getAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", DEPARTMENT_SERVICE.getAll());
        return "employees/employeesAddForm";
    }

    @GetMapping("/updateForm")
    public String getUpdateEmployeePage(
            @RequestParam("employeeId") int employeeId,
            Model model) {
        Employee employee = EMPLOYEE_SERVICE.getById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", DEPARTMENT_SERVICE.getAll());
        return "employees/employeesUpdateForm";
    }

    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam("q") String query,
            Model model) {

        Employee searchEmployee;
        String[] splitQuery = query.split(",");

        switch (splitQuery.length) {
            case 1:
                System.out.println("One");
                //todo code like - EMPLOYEE_SERVICE.findByOneParam(String fistParam)
                break;

            case 2:
                System.out.println("Two");
                //todo code like - EMPLOYEE_SERVICE.findByTwoParams(String fistParam,String secondParam)
                break;

            default:
                return "redirect:/employees/?&searchError";
        }

        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Employee employee,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "redirect:/employees/updateForm?employeeId="+employee.getId()+"&error";
        }

        EMPLOYEE_SERVICE.save(employee);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "redirect:/employees/add?&error";
        }

        Salary tempSalary = employee.getSalary();
        tempSalary.setEmployee(employee);
        SALARY_SERVICE.save(tempSalary);

        DateStamp tempDateStamp = new DateStamp(
                employee,
                LocalDate.now().toString(),
                LocalDate.now().plusMonths(1).toString()
        );
        DATE_STAMP_SERVICE.save(tempDateStamp);

        employee.setDate(tempDateStamp);

        EMPLOYEE_SERVICE.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        EMPLOYEE_SERVICE.deleteById(employeeId);
        return "redirect:/";
    }

}
