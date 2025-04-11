package com.rnb.restDemo.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class SimparicaServiceImpl implements SimparicaService {
    private static final int PERIOD = 28;
    public static final String DD_MM_YYYY = "dd.MM.yyyy";

    @Override
    public String getNextDayForSimparica(int year, int month, int day) {
        LocalDate from = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        Period period = Period.between(from, now);
        LocalDate needToGive = from.plusDays(PERIOD);

        String needToGiveFormatted = needToGive.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
        String numberOfDays = period.getDays() > 0
                ? ", number of days: " + period.getDays()
                : "";

        return "Simparica was given: " + from + numberOfDays + "\nNext Simparica reception: " + needToGiveFormatted;
    }
}
