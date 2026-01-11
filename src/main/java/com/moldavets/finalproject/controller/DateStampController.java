package com.moldavets.finalproject.controller;

import com.moldavets.finalproject.model.DateStamp;
import com.moldavets.finalproject.service.DateStampService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DateStampController {

    private final DateStampService dateStampService;

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
                case "idOrderByASC" -> dateStampService.getAllOrderByIdAsc();
                case "idOrderByDesc" -> dateStampService.getAllOrderByIdDesc();
                case "employeeIdOrderByAsc" -> dateStampService.getAllOrderByEmployeeIdAsc();
                case "employeeIdOrderByDesc" -> dateStampService.getAllOrderByEmployeeIdDesc();
                case "employeeNameOrderByAsc" -> dateStampService.getAllOrderByEmployeeFirstNameAsc();
                case "employeeNameOrderByDesc" -> dateStampService.getAllOrderByEmployeeFirstNameDesc();
                case "employmentDateOrderByAsc" -> dateStampService.getAllOrderByEmploymentDateAsc();
                case "employmentDateOrderByDesc" -> dateStampService.getAllOrderByEmploymentDateDesc();
                case "paymentDateOrderByAsc" -> dateStampService.getAllOrderByPaymentDateAsc();
                case "paymentDateOrderByDesc" -> dateStampService.getAllOrderByPaymentDateDesc();
                default -> dateStampService.getAll();
            };
        } else {
            dateStamps = dateStampService.getAll();
        }

        model.addAttribute("dateStamps", dateStamps);
        return "datestamps/datestamps";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("dateStampId") int dateStampId,
                             Model model) {

        DateStamp dateStamp = dateStampService.getById(dateStampId);

        if(dateStamp == null) {
            return "redirect:/datestamps/?dateStampNotFound=" + dateStampId;
        } else {
            model.addAttribute("dateStamp", dateStamp);
            return "datestamps/datestampsUpdateForm";
        }
    }

    @PostMapping("/makePayment")
    public String makePayment(@RequestParam("dateStampId") int dateStampId) {

        DateStamp dateStamp = dateStampService.getById(dateStampId);

        if(dateStamp != null) {
            dateStamp.setPaymentDate(LocalDate.now().plusMonths(1).toString());
            dateStampService.update(dateStamp);
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
                model.addAttribute("dateStamps", sortDateStamps(dateStampService.getAllByInputString(query), sort));
            } else {
                model.addAttribute("dateStamps", dateStampService.getAllByInputString(query));
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
        dateStampService.update(dateStamp);
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
