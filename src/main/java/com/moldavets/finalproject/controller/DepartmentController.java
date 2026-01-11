package com.moldavets.finalproject.controller;

import com.moldavets.finalproject.model.Department;
import com.moldavets.finalproject.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String listDepartments(@RequestParam(value = "sort", required = false) String sort,
                                  Model model) {
        List<Department> departments;
        if(sort != null) {
            departments = switch (sort) {
                case "idOrderByAsc" -> departmentService.getAllOrderByIdAsc();
                case "idOrderByDesc" -> departmentService.getAllOrderByIdDesc();
                case "abbreviationOrderByAsc" -> departmentService.getAllOrderByAbbreviationAsc();
                case "abbreviationOrderByDesc" -> departmentService.getAllOrderByAbbreviationDesc();
                case "nameOrderByAsc" -> departmentService.getAllOrderByNameAsc();
                case "nameOrderByDesc" -> departmentService.getAllOrderByNameDesc();
                default -> departmentService.getAll();
            };
        } else {
            departments = departmentService.getAll();
        }

        model.addAttribute("departments", departments);
        return "departments/departments";
    }

    @GetMapping("/add")
    public String addDepartment(Model model) {
        model.addAttribute("departments", new Department());
        return "departments/departmentsAddForm";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("departmentId") int departmentId,
                             Model model) {
        Department department = departmentService.getById(departmentId);
        if(department == null) {
            return "redirect:/departments/?departmentNotFound=" + departmentId;
        } else {
            model.addAttribute("department", department);
            return "departments/departmentsUpdateForm";
        }
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute("departments") Department department,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "departments/departmentsAddForm";
        }

        departmentService.save(department);
        return "redirect:/departments/?addedDepartmentId=" + department.getId();
    }

    @PostMapping("/update")
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/departments/updateForm?departmentId=" + department.getId() + "&error";
        }
        departmentService.save(department);
        return "redirect:/departments/?updatedDepartmentId=" + department.getId();
    }

    @PostMapping("/delete")
    public String deleteDepartment(@RequestParam("departmentId") int departmentId) {
        departmentService.delete(departmentId);
        return "redirect:/departments/?deletedDepartmentId=" + departmentId;
    }
}
