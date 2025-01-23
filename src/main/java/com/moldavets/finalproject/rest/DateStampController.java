package com.moldavets.finalproject.rest;

import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.entity.Salary;
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
import java.util.Comparator;
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
            dateStamp.setPaymentDate(LocalDate.now().plusMonths(1).toString());
            DATE_STAMP_SERVICE.update(dateStamp);
        } else {
            return "redirect:/datestamps/?dateStampNotFound=" + dateStampId;
        }

        return "redirect:/datestamps/?madePaymentToId=" + dateStamp.getEmployee().getId();
    }

    @GetMapping("/search")
    public String searchDateStamp(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        if(query != null) {
            if(sort != null) {
                model.addAttribute("dateStamps", sortDateStamps(DATE_STAMP_SERVICE.getAllByInputString(query), sort));
            } else {
                model.addAttribute("dateStamps", DATE_STAMP_SERVICE.getAllByInputString(query));
            }
            return "datestamps/datestamps";
        } else {
            return "redirect:/datestamps/?searchError";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dateStamp") DateStamp dateStamp,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/datestamps/updateForm?dateStampId=" + dateStamp.getId() + "&error";
        }
        DATE_STAMP_SERVICE.update(dateStamp);
        return "redirect:/datestamps/?updatedDateStampId=" + dateStamp.getId();
    }

    private static List<DateStamp> sortDateStamps(List<DateStamp> dateStamps, String sort) {

        List<DateStamp> sortedDateStamps;

        sortedDateStamps = switch (sort) {

            case "IdOrderByAsc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getId))
                    .toList();

            case "idOrderByDesc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getId).reversed())
                    .toList();

            case "employeeIdOrderByAsc" -> dateStamps.stream()
                    .sorted(Comparator.comparing((DateStamp s) -> s.getEmployee().getId()))
                    .toList();

            case "employeeIdOrderByDesc" -> dateStamps.stream()
                    .sorted(Comparator.comparing((DateStamp s) -> s.getEmployee().getId()).reversed())
                    .toList();

            case "employeeNameOrderByAsc" -> dateStamps.stream()
                    .sorted(Comparator.comparing((DateStamp s) -> s.getEmployee().getFirstName()))
                    .toList();

            case "employeeNameOrderByDesc" -> dateStamps.stream()
                    .sorted(Comparator.comparing((DateStamp s) -> s.getEmployee().getFirstName()).reversed())
                    .toList();

            case "employmentDateOrderByAsc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getEmploymentDate))
                    .toList();

            case "employmentDateOrderByDesc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getEmploymentDate).reversed())
                    .toList();

            case "paymentDateOrderByAsc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getPaymentDate))
                    .toList();

            case "paymentDateOrderByDesc" -> dateStamps.stream()
                    .sorted(Comparator.comparing(DateStamp::getPaymentDate).reversed())
                    .toList();

            default -> dateStamps;
        };
        return sortedDateStamps;
    }

}
