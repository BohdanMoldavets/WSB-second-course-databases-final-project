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
    public String getDateStamps(Model model) {
        List<DateStamp> dateStamps = DATE_STAMP_SERVICE.getAll();
        model.addAttribute("dateStamps", dateStamps);
        return "datestamps/datestamps";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("dateStampId") int dateStampId,
                             Model model) {

        model.addAttribute("dateStamp", DATE_STAMP_SERVICE.getById(dateStampId));
        return "datestamps/datestampsUpdateForm";
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
