package com.learn;

import com.learn.collection.CustomArrayList;
import com.learn.file_access.FileManager;
import com.learn.model.Student;
import com.learn.service.StudentService;
import com.learn.sort.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Application entry point.
 * Implements a console menu for working with the student list:
 * filling, viewing, sorting, writing to file, multithreaded search.
 */
public class Main {
    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> fillDataMenu();
                case 2 -> showOriginalList();
                case 3 -> sortMenu();
                case 4 -> countOccurrencesMenu();
                case 5 -> writeToFile();
                case 6 -> studentService.clear();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }

            if (running && choice != 0) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Меню сортировки студентов ===");
        System.out.println("1. Заполнить список студентов");
        System.out.println("2. Показать исходный список");
        System.out.println("3. Выполнить сортировку");
        System.out.println("4. Подсчитать вхождения (многопоточно)");
        System.out.println("5. Записать отсортированный список в файл");
        System.out.println("6. Очистить список");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int getPositiveInt() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value > 0 ? value : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void fillDataMenu() {
        System.out.println("\n=== Заполнение списка студентов ===");
        System.out.print("Введите размер списка (положительное число): ");
        int size = getPositiveInt();
        if (size <= 0) {
            System.out.println("Размер должен быть положительным числом!");
            return;
        }

        System.out.println("\nВыберите способ заполнения:");
        System.out.println("1. Заполнить случайными данными");
        System.out.println("2. Заполнить вручную");
        System.out.println("3. Заполнить из файла");
        System.out.print("Ваш выбор: ");

        int fillChoice = getChoice();

        switch (fillChoice) {
            case 1 -> studentService.fillRandom(size);
            case 2 -> {
                try {
                    studentService.fillManual(size, scanner);
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка валидации: " + e.getMessage());
                }
            }
            case 3 -> {
                System.out.print("Введите путь к файлу (default: src/main/resources/data_files/Students.json): ");
                String path = scanner.nextLine().trim();
                if (path.isEmpty()) path = "src/main/resources/data_files/Students.json";
                try {
                    studentService.fillFromFile(path);
                } catch (IOException e) {
                    System.out.println("Ошибка загрузки: " + e.getMessage());
                }
            }
            default -> System.out.println("Неверный выбор.");
        }
    }

    private static void showOriginalList() {
        if (studentService.getStudents().isEmpty()) {
            System.out.println("\nСписок студентов пуст! Сначала заполните список (пункт 1).");
            return;
        }
        System.out.println("\n=== Исходный список ===");
        System.out.println("Количество студентов: " + studentService.getStudents().size());
        printStudents(studentService.getStudents());
    }

    private static void printStudents(List<Student> students) {
        int i = 0;
        for (Student student : students) {
            i++;
            String marker = student.getRecordBookNumber() % 2 == 0 ? "(чет)" : "(нечет)";
            System.out.printf("%3d. %s %s%n", i, student, marker);
        }
    }

    private static void sortMenu() {
        if (studentService.getStudents().isEmpty()) {
            System.out.println("\nСписок пуст! Сначала заполните (пункт 1).");
            return;
        }

        System.out.println("\n=== Выбор поля для сортировки ===");
        System.out.println("1. По номеру группы");
        System.out.println("2. По среднему баллу");
        System.out.println("3. По номеру зачетки");
        System.out.print("Выберите поле: ");
        int field = getChoice();
        Comparator<Student> comparator = getComparator(field);
        if (comparator == null) {
            System.out.println("Неверный выбор поля.");
            return;
        }

        System.out.println("\n=== Выбор алгоритма сортировки ===");
        System.out.println("1. Сортировка пузырьком");
        System.out.println("2. Сортировка вставками");
        System.out.println("3. Быстрая сортировка");
        System.out.println("4. Сортировка объединением");
        System.out.println("5. Специальная сортировка пузырьком (только четные)");
        System.out.println("6. Специальная сортировка вставками (только четные)");
        System.out.println("7. Специальная быстрая сортировка (только четные)");
        System.out.println("8. Специальная сортировка объединением (только четные)");
        System.out.print("Выберите алгоритм: ");
        int algorithm = getChoice();
        SortStrategy strategy = getStrategy(algorithm);
        if (strategy == null) {
            System.out.println("Неверный выбор алгоритма.");
            return;
        }

        System.out.println("1. Сортировать копию списка");
        System.out.println("2. Сортировать сам список");
        int listCopy = getChoice();
        switch (listCopy) {
            case 1 -> {
                CustomArrayList<Student> toSort = copyList(studentService.getStudents());
                strategy.sort(toSort, comparator);
                System.out.println("\n=== Результат сортировки ===");
                printStudents(toSort);
            }
            default -> {
                studentService.sort(strategy, comparator);
                System.out.println("\n=== Результат сортировки ===");
                printStudents(studentService.getStudents());
            }
        }

    }

    private static Comparator<Student> getComparator(int field) {
        return switch (field) {
            case 1 -> StudentComparators.byGroupNumber();
            case 2 -> StudentComparators.byAverageGrade();
            case 3 -> StudentComparators.byRecordBookNumber();
            default -> null;
        };
    }

    private static SortStrategy getStrategy(int algorithm) {
        return switch (algorithm) {
            case 1 -> new BubbleSort();
            case 2 -> new InsertionSort();
            case 3 -> new QuickSort();
            case 4 -> new MergeSort();
            case 5 -> new EvenOnlySortStrategy(new BubbleSort());
            case 6 -> new EvenOnlySortStrategy(new InsertionSort());
            case 7 -> new EvenOnlySortStrategy(new QuickSort());
            case 8 -> new EvenOnlySortStrategy(new MergeSort());
            default -> null;
        };
    }

    private static CustomArrayList<Student> copyList(CustomArrayList<Student> source) {
        CustomArrayList<Student> copy = new CustomArrayList<>();
        copy.addAll(source);
        return copy;
    }

    private static void countOccurrencesMenu() {
        if (studentService.getStudents().isEmpty()) {
            System.out.println("\nСписок пуст!");
            return;
        }
        System.out.print("Введите номер зачетки для поиска: ");
        int target = getPositiveInt();
        System.out.print("Введите количество потоков (default: 4): ");
        int threads = getPositiveInt();
        if (threads <= 0) threads = 4;
        int count = studentService.countOccurrences(target, threads);
        System.out.println("Вхождений: " + count);
    }

    private static void writeToFile() {
        if (studentService.getStudents().isEmpty()) {
            System.out.println("\nСписок пуст!");
            return;
        }
        System.out.print("Введите путь к файлу для записи (default: src/main/resources/data_files/WriteTest.json): ");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) path = "src/main/resources/data_files/WriteTest.json";
        try {
            FileManager.getInstance().writeData(studentService.getStudents(), path);
            System.out.println("Данные записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }
}