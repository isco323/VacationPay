package com.example.vacationpay.controller;

import com.example.vacationpay.service.CalculateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    CalculateService calculateService;

    @GetMapping
    public void count(@RequestParam Double averageSalary,
                      @RequestParam int vacationDays,
                      @RequestParam(required = false) String vacationStartDate){
        if (vacationStartDate != null) {
            calculateService.countMoney(averageSalary, vacationDays, vacationStartDate);
        } else {
            calculateService.countMoney(averageSalary, vacationDays);
        }

    }
}
