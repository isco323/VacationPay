package com.example.vacationpay.service;

import com.example.vacationpay.repo.WeekndRepo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalculateService {
    public double countMoney(Double averageSalary, int vacationDays, String vacationStartDate){
        if(averageSalary < 0 || vacationDays < 0){
            throw new IllegalArgumentException("Data cannot be negative.");
        }
        if (vacationStartDate.isEmpty())
        {
            return (averageSalary / 29.3) * vacationDays;
        }
        LocalDate startDate = LocalDate.parse(vacationStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = startDate.plusDays(vacationDays);
        int workDays = 0;
        int holidays = 0;
        List<LocalDate> holiDates = WeekndRepo.generateDatas(startDate);
        for (LocalDate date = startDate; endDate.isAfter(date); date = date.plusDays(1))
        {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY)
            {
                workDays++;
            }
            for (LocalDate holiDate : holiDates) {
                if (date.getMonth() == holiDate.getMonth() && date.getDayOfMonth() == holiDate.getDayOfMonth()) {
                    holidays++;
                }
            }
        }
        return (averageSalary / 29.3) * (workDays - holidays);
    }
}
