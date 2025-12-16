package com.learn;

import com.learn.collection.CustomArrayList;
import com.learn.file_access.FileManager;
import com.learn.model.Student;
import com.learn.service.StudentService;
import com.learn.sort.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

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
        for (Student student : students) {
            String marker = student.getRecordBookNumber() % 2 == 0 ? "(чет)" : "(нечет)";
            System.out.println("  " + student + " " + marker);
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
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Special Bubble Sort (только четные)");
        System.out.println("6. Special Insertion Sort (только четные)");
        System.out.println("7. Special Quick Sort (только четные)");
        System.out.println("8. Special Merge Sort (только четные)");
        System.out.print("Выберите алгоритм: ");
        int algorithm = getChoice();
        SortStrategy strategy = getStrategy(algorithm);
        if (strategy == null) {
            System.out.println("Неверный выбор алгоритма.");
            return;
        }

        CustomArrayList<Student> sortedStudents = copyList(studentService.getStudents());
        studentService.sort(strategy, comparator);

        System.out.println("\n=== Результат сортировки ===");
        printStudents(studentService.getStudents());
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
            case 5 -> new SpecialBubbleSort();
            case 6 -> new SpecialInsertionSort();
            case 7 -> new SpecialQuickSort();
            case 8 -> new SpecialMergeSort();
            default -> null;
        };
    }

    private static CustomArrayList<Student> copyList(CustomArrayList<Student> source) {
        CustomArrayList<Student> copy = new CustomArrayList<>();
        for (Student student : source) {
            copy.add(student);
        }
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