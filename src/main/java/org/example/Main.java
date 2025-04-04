package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        LambdaDateTasks();
        LambdaFractions();
        LambdaMinMax();
        LambdaArraySum();
    }

    public static void LambdaDateTasks() {

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

    public static void LambdaFractions() {

        // Лямбда для сложения дробей
        BiFunction<Fraction, Fraction, Fraction> addFractions = (f1,
                f2) -> new Fraction(f1.numerator * f2.denominator + f2.numerator * f1.denominator,
                        f1.denominator * f2.denominator).simplify();

        // Лямбда для вычитания дробей
        BiFunction<Fraction, Fraction, Fraction> subtractFractions = (f1,
                f2) -> new Fraction(f1.numerator * f2.denominator - f2.numerator * f1.denominator,
                        f1.denominator * f2.denominator).simplify();

        // Лямбда для умножения дробей
        BiFunction<Fraction, Fraction, Fraction> multiplyFractions = (f1,
                f2) -> new Fraction(f1.numerator * f2.numerator, f1.denominator * f2.denominator).simplify();

        // Лямбда для деления дробей
        BiFunction<Fraction, Fraction, Fraction> divideFractions = (f1,
                f2) -> new Fraction(f1.numerator * f2.denominator, f1.denominator * f2.numerator).simplify();

        // Тестовые значения
        Fraction f1 = new Fraction(1, 2); // 1/2
        Fraction f2 = new Fraction(2, 3); // 2/3

        System.out.println("Сложение: " + addFractions.apply(f1, f2)); // 7/6
        System.out.println("Вычитание: " + subtractFractions.apply(f1, f2)); // -1/6
        System.out.println("Умножение: " + multiplyFractions.apply(f1, f2)); // 2/6 -> 1/3
        System.out.println("Деление: " + divideFractions.apply(f1, f2)); // 3/4
    }

    public static void LambdaMinMax() {
        // Лямбда для нахождения максимума из четырёх чисел
        Function<int[], Integer> maxOfFour = numbers -> Arrays.stream(numbers).max().orElseThrow();

        // Лямбда для нахождения минимума из четырёх чисел
        Function<int[], Integer> minOfFour = numbers -> Arrays.stream(numbers).min().orElseThrow();

        // Тестовые значения
        int[] numbers = { 7, 2, 9, 4 };

        System.out.println("Максимум из " + Arrays.toString(numbers) + ": " + maxOfFour.apply(numbers));
        System.out.println("Минимум из " + Arrays.toString(numbers) + ": " + minOfFour.apply(numbers));
    }

    public static void LambdaArraySum() {
        int[] numbers = { 1, -3, 5, 8, -2, 7, 10, -6 };

        // Лямбда-выражения для различных условий
        Predicate<Integer> isEqualToFive = num -> num == 5;
        Predicate<Integer> isNotInRange = num -> num < 2 || num > 8;
        Predicate<Integer> isPositive = num -> num > 0;
        Predicate<Integer> isNegative = num -> num < 0;

        // Вызов метода с разными условиями
        System.out.println("Сумма чисел, равных 5: " + sumByCondition(numbers, isEqualToFive));
        System.out.println("Сумма чисел вне диапазона [2, 8]: " + sumByCondition(numbers, isNotInRange));
        System.out.println("Сумма положительных чисел: " + sumByCondition(numbers, isPositive));
        System.out.println("Сумма отрицательных чисел: " + sumByCondition(numbers, isNegative));
    }

    // Метод вычисления суммы по условию (лямбда как параметр)
    public static int sumByCondition(int[] array, Predicate<Integer> condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }
}

// Класс для работы с дробями
class Fraction {
    int numerator; // Числитель
    int denominator; // Знаменатель

    // Конструктор
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Знаменатель не может быть 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Метод для сокращения дроби
    public Fraction simplify() {
        int gcd = gcd(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    // Нахождение НОД (наибольшего общего делителя)
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Преобразование дроби в строку
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

}