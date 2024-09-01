package com.example.vacationpay.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {

    private final CalculateService calculateService = new CalculateService();

    @Test
    void testCountMoney() {
        double averageSalary = 3000.0;
        int vacationDays = 10;
        double expected = averageSalary / 29.3 * vacationDays;
        double actual = calculateService.countMoney(averageSalary, vacationDays, "");
        assertEquals(expected, actual);
    }

    @Test
    void testMinusSalary(){
        double averageSalary = -3000.0;
        int vacationDays = 10;
        assertThrows(IllegalArgumentException.class, () -> {
            calculateService.countMoney(averageSalary, vacationDays, "");
        });
    }

    @Test
    void testMinusDays(){
        double averageSalary = 3000.0;
        int vacationDays = -10;
        assertThrows(IllegalArgumentException.class, () -> {
            calculateService.countMoney(averageSalary, vacationDays, "");
        });
    }

    @Test
    void testCountMoneyWithDate() {
        double averageSalary = 3000.0;
        int vacationDays = 10;
        String vacationStartDate = "01/08/2024";
        double expected = averageSalary / 29.3 * (7);;
        double actual = calculateService.countMoney(averageSalary, vacationDays, vacationStartDate);
        assertEquals(expected, actual);
    }

    @Test
    void testDateFormatSec() {
        double averageSalary = 3000.0;
        int vacationDays = 10;
        String vacationStartDate = "2024/09/01"; //correct dd/MM/yyyy

        assertThrows(DateTimeParseException.class, () -> {
            calculateService.countMoney(averageSalary, vacationDays, vacationStartDate);
        });
    }
}