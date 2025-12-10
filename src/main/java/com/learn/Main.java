package com.learn;

import com.learn.model.Student;
import com.learn.sort.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("A", 4.5, 5));
        students.add(new Student("B", 3.8, 2));
        students.add(new Student("C", 4.2, 7));
        students.add(new Student("D", 4.9, 4));
        students.add(new Student("E", 3.5, 9));
        students.add(new Student("F", 4.7, 6));
        students.add(new Student("G", 3.9, 3));
        students.add(new Student("H", 5.0, 8));

        System.out.println("Исходный список:");
        printStudents(students);

        System.out.println("\n--- Обычная сортировка пузырьком ---");
        List<Student> bubbleStudents = new ArrayList<>(students);
        BubbleSort.sort(bubbleStudents);
        printStudents(bubbleStudents);

        System.out.println("\n--- Специальная сортировка пузырьком (только четные) ---");
        List<Student> specialBubbleStudents = new ArrayList<>(students);
        SpecialBubbleSort.sort(specialBubbleStudents);
        printStudents(specialBubbleStudents);

        System.out.println("\n--- Обычная быстрая сортировка ---");
        List<Student> quickStudents = new ArrayList<>(students);
        QuickSort.sort(quickStudents);
        printStudents(quickStudents);

        System.out.println("\n--- Специальная быстрая сортировка (только четные) ---");
        List<Student> specialQuickStudents = new ArrayList<>(students);
        SpecialQuickSort.sort(specialQuickStudents);
        printStudents(specialQuickStudents);

        System.out.println("\n--- Обычная сортировка слиянием ---");
        List<Student> mergeStudents = new ArrayList<>(students);
        MergeSort.sort(mergeStudents);
        printStudents(mergeStudents);

        System.out.println("\n--- Специальная сортировка слиянием (только четные) ---");
        List<Student> specialMergeStudents = new ArrayList<>(students);
        SpecialMergeSort.sort(specialMergeStudents);
        printStudents(specialMergeStudents);

        System.out.println("\n--- Обычная сортировка вставками ---");
        List<Student> insertionStudents = new ArrayList<>(students);
        InsertionSort.sort(insertionStudents);
        printStudents(insertionStudents);

        System.out.println("\n--- Специальная сортировка вставками (только четные) ---");
        List<Student> specialInsertionStudents = new ArrayList<>(students);
        SpecialInsertionSort.sort(specialInsertionStudents);
        printStudents(specialInsertionStudents);
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            String marker = student.getRecordBookNumber() % 2 == 0 ? "(чет)" : "(нечет)";
            System.out.println("  " + student + " " + marker);
        }
    }
}
