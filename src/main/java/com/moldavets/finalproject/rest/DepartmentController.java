package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.Department;
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
    public String listDepartments(@RequestParam(value = "sort", required = false) String sort,
                                  Model model) {
        List<Department> departments;
        if(sort != null) {
            departments = switch (sort) {
                case "idOrderByAsc" -> DEPARTMENT_SERVICE.getAllOrderByIdAsc();
                case "idOrderByDesc" -> DEPARTMENT_SERVICE.getAllOrderByIdDesc();
                case "abbreviationOrderByAsc" -> DEPARTMENT_SERVICE.getAllOrderByAbbreviationAsc();
                case "abbreviationOrderByDesc" -> DEPARTMENT_SERVICE.getAllOrderByAbbreviationDesc();
                case "nameOrderByAsc" -> DEPARTMENT_SERVICE.getAllOrderByNameAsc();
                case "nameOrderByDesc" -> DEPARTMENT_SERVICE.getAllOrderByNameDesc();
                default -> DEPARTMENT_SERVICE.getAll();
            };
        } else {
            departments = DEPARTMENT_SERVICE.getAll();
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
        Department department = DEPARTMENT_SERVICE.getById(departmentId);
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

        DEPARTMENT_SERVICE.save(department);
        return "redirect:/departments/?addedDepartmentId=" + department.getId();
    }

    @PostMapping("/update")
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/departments/updateForm?departmentId=" + department.getId() + "&error";
        }
        DEPARTMENT_SERVICE.save(department);
        return "redirect:/departments/?updatedDepartmentId=" + department.getId();
    }

    @PostMapping("/delete")
    public String deleteDepartment(@RequestParam("departmentId") int departmentId) {
        DEPARTMENT_SERVICE.delete(departmentId);
        return "redirect:/departments/?deletedDepartmentId=" + departmentId;
    }
}
