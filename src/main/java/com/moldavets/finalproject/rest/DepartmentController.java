package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Department;
import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.service.DepartmentService;
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
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService DEPARTMENT_SERVICE;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        DEPARTMENT_SERVICE = departmentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listDepartments(Model model) {
        List<Department> departments = DEPARTMENT_SERVICE.getAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/add")
    public String addDepartment(Model model) {
        model.addAttribute("departments", new Department());
        return "departmentsAddForm";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("departmentId") int departmentId,
                             Model model) {
        model.addAttribute("department", DEPARTMENT_SERVICE.getById(departmentId));
        return "departmentsUpdateForm";
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute("departments") Department department,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "departmentsAddForm";
        }
        DEPARTMENT_SERVICE.save(department);
        return "redirect:/departments/";
    }

    @PostMapping("/update")
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/departments/updateForm?departmentId=" + department.getId() + "&error";
        }
        DEPARTMENT_SERVICE.save(department);
        return "redirect:/departments/";
    }

    @PostMapping("/delete")
    public String deleteDepartment(@RequestParam("departmentId") int departmentId) {
        DEPARTMENT_SERVICE.delete(departmentId);
        return "redirect:/departments/";
    }



}
