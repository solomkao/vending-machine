package com.solomka.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private DateValidator() {
    }

    public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
