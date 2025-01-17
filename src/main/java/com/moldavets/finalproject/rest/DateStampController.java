package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.service.DateStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/datestamps")
public class DateStampController {

    private final DateStampService DATE_STAMP_SERVICE;

    @Autowired
    public DateStampController(DateStampService DATE_STAMP_SERVICE) {
        this.DATE_STAMP_SERVICE = DATE_STAMP_SERVICE;
    }

    @GetMapping("/")
    public String getDateStamps(Model model) {
        List<DateStamp> dateStamps = DATE_STAMP_SERVICE.getAll();
        model.addAttribute("dateStamps", dateStamps);
        return "datestamps/datestamps";
    }

}
