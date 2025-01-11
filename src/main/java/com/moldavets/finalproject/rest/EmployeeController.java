package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.service.EmployeeService;
import jakarta.validation.Valid;
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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
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

    @GetMapping("/updateForm")
    public String getUpdateEmployeePage(@RequestParam("employeeId") int employeeId,
                                        Model model) {
        Employee employee = employeeService.getById((long) employeeId);
        model.addAttribute("employee", employee);
        return "employeeUpdateForm";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Employee employee,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/employees/updateForm?employeeId="+employee.getId()+"&error";
        }
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "employeeAddForm";
        }
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById((long) employeeId);
        return "redirect:/";
    }

}
