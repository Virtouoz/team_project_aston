package com.learn;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import com.learn.sort.*;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Student> students = new CustomArrayList<Student>();
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
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
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
        System.out.print("Ваш выбор: ");
        
        int fillChoice = getChoice();
        students = new CustomArrayList<Student>(size);
        
        switch (fillChoice) {
            case 1 -> fillRandomData(size);
            case 2 -> fillManualData(size);
            default -> {
                System.out.println("Неверный выбор. Список не заполнен.");
                students = new CustomArrayList<Student>();
            }
        }
        
        if (!students.isEmpty()) {
            System.out.println("\nСписок успешно заполнен! Количество студентов: " + students.size());
        }
    }

    private static void fillRandomData(int size) {
        String[] groupNumbers = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
        for (int i = 0; i < size; i++) {
            String groupNumber = groupNumbers[random.nextInt(groupNumbers.length)];
            double averageGrade = Math.round((2.0 + random.nextDouble() * 3.0) * 10.0) / 10.0; // от 2.0 до 5.0
            int recordBookNumber = random.nextInt(9999) + 1; // от 1 до 9999
            
            try {
                students.add(new Student(groupNumber, averageGrade, recordBookNumber));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при создании студента: " + e.getMessage());
                i--; // повторить попытку
            }
        }
    }

    private static void fillManualData(int size) {
        System.out.println("\nВведите данные для каждого студента:");
        for (int i = 0; i < size; i++) {
            System.out.println("\n--- Студент #" + (i + 1) + " ---");
            
            String groupNumber = getGroupNumber();
            double averageGrade = getAverageGrade();
            int recordBookNumber = getRecordBookNumber();
            
            try {
                students.add(new Student(groupNumber, averageGrade, recordBookNumber));
                System.out.println("Студент успешно добавлен!");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации: " + e.getMessage());
                System.out.println("Попробуйте снова.");
                i--; // повторить попытку
            }
        }
    }

    private static String getGroupNumber() {
        while (true) {
            System.out.print("Введите номер группы: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Номер группы не может быть пустым. Попробуйте снова.");
        }
    }

    private static double getAverageGrade() {
        while (true) {
            System.out.print("Введите средний балл (0.0 - 5.0): ");
            try {
                double grade = Double.parseDouble(scanner.nextLine().trim());
                if (grade >= 0.0 && grade <= 5.0) {
                    return grade;
                }
                System.out.println("Средний балл должен быть от 0.0 до 5.0. Попробуйте снова.");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Попробуйте снова.");
            }
        }
    }

    private static int getRecordBookNumber() {
        while (true) {
            System.out.print("Введите номер зачетки (положительное число): ");
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number > 0) {
                    return number;
                }
                System.out.println("Номер зачетки должен быть положительным числом. Попробуйте снова.");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Попробуйте снова.");
            }
        }
    }

    private static int getPositiveInt() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void sortMenu() {
        if (students.isEmpty()) {
            System.out.println("\nСписок студентов пуст! Сначала заполните список (пункт 1).");
            return;
        }

        System.out.println("\n=== Выберите алгоритм сортировки ===");
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
        if (algorithm < 1 || algorithm > 8) {
            System.out.println("Неверный выбор алгоритма.");
            return;
        }

        System.out.println("\n=== Выберите поле для сортировки ===");
        System.out.println("1. По номеру группы (groupNumber)");
        System.out.println("2. По среднему баллу (averageGrade)");
        System.out.println("3. По номеру зачетки (recordBookNumber)");
        System.out.print("Выберите поле: ");
        
        int field = getChoice();
        Comparator<Student> comparator = getComparator(field);
        if (comparator == null) {
            System.out.println("Неверный выбор поля.");
            return;
        }

        SortStrategy strategy = getStrategy(algorithm);
        if (strategy == null) {
            System.out.println("Неверный выбор алгоритма.");
            return;
        }

        List<Student> sortedStudents = copyList(students);
        strategy.sort(sortedStudents, comparator);
        
        System.out.println("\n=== Результат сортировки ===");
        printStudents(sortedStudents);
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

    private static List<Student> copyList(List<Student> source) {
        List<Student> copy = new CustomArrayList<Student>();
        for (Student student : source) {
            copy.add(student);
        }
        return copy;
    }

    private static void showOriginalList() {
        if (students.isEmpty()) {
            System.out.println("\nСписок студентов пуст! Сначала заполните список (пункт 1).");
            return;
        }
        System.out.println("\n=== Исходный список ===");
        System.out.println("Количество студентов: " + students.size());
        printStudents(students);
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            String marker = student.getRecordBookNumber() % 2 == 0 ? "(чет)" : "(нечет)";
            System.out.println("  " + student + " " + marker);
        }
    }

    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
