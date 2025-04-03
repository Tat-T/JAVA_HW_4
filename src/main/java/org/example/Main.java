package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        
        // Проверка, является ли год високосным
        Function<Integer, Boolean> isLeapYear = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        System.out.println("2000 - високосный? " + isLeapYear.apply(2000)); // true
        System.out.println("1900 - високосный? " + isLeapYear.apply(1900)); // false

        // Подсчёт количества дней между двумя датами
        BiFunction<LocalDate, LocalDate, Long> daysBetween = ChronoUnit.DAYS::between;

        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 12, 31);
        System.out.println("Дней между " + date1 + " и " + date2 + ": " + daysBetween.apply(date1, date2));

        // Подсчет количества полных недель между двумя датами
        BiFunction<LocalDate, LocalDate, Long> weeksBetween = ChronoUnit.WEEKS::between;

        System.out.println("Полных недель между " + date1 + " и " + date2 + ": " + weeksBetween.apply(date1, date2));

        // Определение дня недели по дате
        Function<LocalDate, String> getDayOfWeek = date -> date.getDayOfWeek().toString();

        LocalDate exampleDate = LocalDate.of(1969, 7, 20);
        System.out.println("20 июля 1969 года было: " + getDayOfWeek.apply(exampleDate));
    }
}