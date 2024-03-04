package com.conecta.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class CirculationRules {

    public boolean canCirculate(String placa, LocalDateTime dateTime) {
        // get day of the week number, Monday = 1
        LocalDate date = dateTime.toLocalDate();
        int dayOfWeek = date.getDayOfWeek().getValue();

        char lastDigit = placa.charAt(placa.length() - 1);

        // get restricted circulation days with the last digit of plate
        List<Integer> noCirculationDays = getNoCirculationDays(lastDigit);

        if (noCirculationDays.contains(dayOfWeek)) {
            LocalTime time = dateTime.toLocalTime();
            if (time.isAfter(LocalTime.of(6, 0)) && time.isBefore(LocalTime.of(9, 30))
                    || time.isAfter(LocalTime.of(16, 0)) && time.isBefore(LocalTime.of(20, 0))) {
                // Restricted circulation
                return false;
            } else {
                // Circulation allowed
                return true;
            }
        } else {
            // Circulation allowed
            return true;
        }
    }

    private List<Integer> getNoCirculationDays(char lastDigit) {
        switch (lastDigit) {
            case '1':
            case '2':
                return Arrays.asList(1); // Monday
            case '3':
            case '4':
                return Arrays.asList(2); // Tuesday
            case '5':
            case '6':
                return Arrays.asList(3); // Wednesday
            case '7':
            case '8':
                return Arrays.asList(4); // Thursday
            case '9':
            case '0':
                return Arrays.asList(5); // Friday
            default:
                return Arrays.asList(6, 7); // weekend
        }
    }
}
