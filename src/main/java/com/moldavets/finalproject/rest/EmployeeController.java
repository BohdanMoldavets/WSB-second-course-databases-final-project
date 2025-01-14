package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.entity.Salary;
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

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService EMPLOYEE_SERVICE;
    private final DepartmentService DEPARTMENT_SERVICE;
    private final SalaryService SALARY_SERVICE;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              DepartmentService departmentService,
                              SalaryService salaryService) {
        this.EMPLOYEE_SERVICE = employeeService;
        this.DEPARTMENT_SERVICE = departmentService;
        this.SALARY_SERVICE = salaryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        List<Employee> employees = EMPLOYEE_SERVICE.getAll();
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
        EMPLOYEE_SERVICE.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        EMPLOYEE_SERVICE.deleteById(employeeId);
        return "redirect:/";
    }

}
