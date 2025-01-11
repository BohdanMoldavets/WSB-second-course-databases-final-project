package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.service.DepartmentService;
import com.moldavets.finalproject.service.EmployeeService;
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

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.EMPLOYEE_SERVICE = employeeService;
        this.DEPARTMENT_SERVICE = departmentService;
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
        return "publicPage";
    }

    @GetMapping("/add")
    public String getAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", DEPARTMENT_SERVICE.getAll());
        return "employeeAddForm";
    }

    @GetMapping("/updateForm")
    public String getUpdateEmployeePage(@RequestParam("employeeId") int employeeId,
                                        Model model) {
        Employee employee = EMPLOYEE_SERVICE.getById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", DEPARTMENT_SERVICE.getAll());
        return "employeeUpdateForm";
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
            return "employeeAddForm";
        }
        EMPLOYEE_SERVICE.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        EMPLOYEE_SERVICE.deleteById(employeeId);
        return "redirect:/";
    }

}
