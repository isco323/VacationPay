package com.example.vacationpay.service;

import com.example.vacationpay.repo.WeekndRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalculateService {
    public double countMoney(Double averageSalary, int vacationDays, String vacationStartDate){
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
            for (int i = 0; i < holiDates.size(); i++)
            {
                if(date.getMonth()==holiDates.get(i).getMonth() && date.getDayOfMonth()==holiDates.get(i).getDayOfMonth()){
                    holidays++;
                }
            }
        }
        return averageSalary / 29.3 * (workDays - holidays);
    }
    public double countMoney(Double averageSalary, int vacationDays){
        return averageSalary / 29.3 * vacationDays;
    }
}
