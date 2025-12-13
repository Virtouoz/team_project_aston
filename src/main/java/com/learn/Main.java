package com.learn;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import com.learn.sort.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Student> students = new CustomArrayList<Student>();
    private static final Scanner scanner = new Scanner(System.in);

    static {
        students.add(new Student("A", 4.5, 5));
        students.add(new Student("B", 3.8, 2));
        students.add(new Student("C", 4.2, 7));
        students.add(new Student("D", 4.9, 4));
        students.add(new Student("E", 3.5, 9));
        students.add(new Student("F", 4.7, 6));
        students.add(new Student("G", 3.9, 3));
        students.add(new Student("H", 5.0, 8));
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1 -> showOriginalList();
                case 2 -> sortMenu();
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
        System.out.println("1. Показать исходный список");
        System.out.println("2. Выполнить сортировку");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void sortMenu() {
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
        System.out.println("\n=== Исходный список ===");
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
