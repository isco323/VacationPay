package com.example.vacationpay.repo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class WeekndRepo {
    public static List<LocalDate> generateDatas(LocalDate start){
        int currentYear = start.getYear();
        return Arrays.asList(LocalDate.of(currentYear, 1, 1),
                LocalDate.of(currentYear, 2, 23),
                LocalDate.of(currentYear, 3, 8),
                LocalDate.of(currentYear, 5, 1),
                LocalDate.of(currentYear, 5, 9),
                LocalDate.of(currentYear, 6, 12),
                LocalDate.of(currentYear, 9, 5));
    }
}
