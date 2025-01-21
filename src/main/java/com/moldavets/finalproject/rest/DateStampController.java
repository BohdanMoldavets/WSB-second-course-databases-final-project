package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.service.DateStampService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/datestamps")
public class DateStampController {

    private final DateStampService DATE_STAMP_SERVICE;

    @Autowired
    public DateStampController(DateStampService DATE_STAMP_SERVICE) {
        this.DATE_STAMP_SERVICE = DATE_STAMP_SERVICE;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String getDateStamps(@RequestParam(value = "sort", required = false) String sort,
                                Model model) {

        List<DateStamp> dateStamps;

        if(sort != null) {
            dateStamps = switch (sort) {
                case "idOrderByASC" -> DATE_STAMP_SERVICE.getAllOrderByIdAsc();
                case "idOrderByDesc" -> DATE_STAMP_SERVICE.getAllOrderByIdDesc();
                case "employeeIdOrderByAsc" -> DATE_STAMP_SERVICE.getAllOrderByEmployeeIdAsc();
                case "employeeIdOrderByDesc" -> DATE_STAMP_SERVICE.getAllOrderByEmployeeIdDesc();
                case "employeeNameOrderByAsc" -> DATE_STAMP_SERVICE.getAllOrderByEmployeeFirstNameAsc();
                case "employeeNameOrderByDesc" -> DATE_STAMP_SERVICE.getAllOrderByEmployeeFirstNameDesc();
                case "employmentDateOrderByAsc" -> DATE_STAMP_SERVICE.getAllOrderByEmploymentDateAsc();
                case "employmentDateOrderByDesc" -> DATE_STAMP_SERVICE.getAllOrderByEmploymentDateDesc();
                case "paymentDateOrderByAsc" -> DATE_STAMP_SERVICE.getAllOrderByPaymentDateAsc();
                case "paymentDateOrderByDesc" -> DATE_STAMP_SERVICE.getAllOrderByPaymentDateDesc();
                default -> DATE_STAMP_SERVICE.getAll();
            };
        } else {
            dateStamps = DATE_STAMP_SERVICE.getAll();
        }

        model.addAttribute("dateStamps", dateStamps);
        return "datestamps/datestamps";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("dateStampId") int dateStampId,
                             Model model) {

        DateStamp dateStamp = DATE_STAMP_SERVICE.getById(dateStampId);

        if(dateStamp == null) {
            return "redirect:/datestamps/?dateStampNotFound=" + dateStampId;
        } else {
            model.addAttribute("dateStamp", dateStamp);
            return "datestamps/datestampsUpdateForm";
        }
    }

    @PostMapping("/makePayment")
    public String makePayment(@RequestParam("dateStampId") int dateStampId) {

        DateStamp dateStamp = DATE_STAMP_SERVICE.getById(dateStampId);

        if(dateStamp != null) {
            dateStamp.setPaymentDate(LocalDate.now().toString());
            DATE_STAMP_SERVICE.update(dateStamp);
        }

        return "redirect:/datestamps/";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dateStamp") DateStamp dateStamp,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/datestamps/updateForm?dateStampId=" + dateStamp.getId() + "&error";
        }
        DATE_STAMP_SERVICE.update(dateStamp);
        return "redirect:/datestamps/";
    }

}
