package com.learn;

import com.learn.service.StudentService;
import com.learn.sort.SortStrategy;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {
        System.out.println("Приложение для работы со списком студентов. Введите '5' для завершения.");

        while (true) {
            printMenu();
            String input = Main.scanner.nextLine();

            switch (input) {
                case ("1") -> fillData();
                case ("2") -> sortData();
                case ("3") -> service.printAll();
                case ("4") -> service.clear();
                case ("5") -> System.out.println("Выход из приложения");
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
            if (input.equals("5")) {
                break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Меню приложения:");
        System.out.println("1. Заполнить список студентов");
        System.out.println("2. Сортировать список");
        System.out.println("3. Показать всех студентов");
        System.out.println("4. Очистить список студентов");
        System.out.println("5. Завершить работу");
        System.out.println("Ваш выбор: ");
    }

    private static void fillData() {
        try {
            System.out.println("Введите количество студентов");
            int count = Integer.parseInt(scanner.nextLine());

            System.out.println("Выберите способ заполнения списка студентов:");
            System.out.println("1. Заполнить список вручную");
            System.out.println("2. Заполнить список рандомно");
            System.out.println("3. Заполнить список из файла");
            System.out.println("Ваш выбор: ");

            String fillDataMethod = scanner.nextLine();

            switch (fillDataMethod) {
                case "1" -> service.fillManual(count, scanner);
                case "2" -> service.fillRandom(count);
                case "3" -> service.fillFromFile("data.txt");
                default -> System.out.println("Неизвестный метод заполнения.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number between 1 and 3");
        } catch (IllegalArgumentException e) {
            System.out.println("Data validation error: " + e.getMessage());
        }
    }

    private static void sortData() {
        if (service.getStudents().isEmpty()) {
            System.out.println("Список студентов пуст. Сначала заполните данные.");
            return;
        }

        System.out.println("Выберите тип сортировки:");
        System.out.println("1 — сортировка пузырьком");
        System.out.println("2 — сортировка вставками");
        System.out.println("3 — быстрая сортировка");
        System.out.println("4 — сортировка слиянием");
        System.out.println("5 — сортировка пузырьком (только четные))");
        System.out.println("6 — сортировка вставками (только четные))");
        System.out.println("7 — быстрая сортировка (только четные))");
        System.out.println("8 — сортировка слиянием (только четные))");
        System.out.print("Ваш выбор: ");

        String sortType = scanner.nextLine();

        System.out.println("Выберите поле для сортировки:");
        System.out.println("1 — по номеру группы");
        System.out.println("2 — по среднему баллу");
        System.out.println("3 — по номеру зачётки");
        System.out.print("Ваш выбор: ");

        String sortField = scanner.nextLine();

        SortStrategy strategy = null;

        /*switch (sortChoice) {
            case "1" -> strategy = new BubbleSort();
            case "2" -> strategy = new InsertionSort();
            case "3" -> strategy = new QuickSort();
            case "4" -> strategy = new MergeSort();
            case "5" -> strategy = new SpecialBubbleSort();
            case "6" -> strategy = new SpecialInsertionSort();
            case "7" -> strategy = new SpecialQuickSort();
            case "8" -> strategy = new SpecialMergeSort();
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }*/

        Comparator comparator = null;

        /*switch (sortField) {
            case "1" -> comparator = StudentComparators.byGroupNumber();
            case "2" -> comparator = StudentComparators.byAverageGrade();
            case "3" -> comparator = StudentComparators.byRecordBookNumber();
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }*/

        service.sort(strategy, comparator);
        System.out.println("Сортировка выполнена.");
    }
}