package com.learn.sort;

import com.learn.model.Student;
import java.util.List;

public class QuickSort {
    public static void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }
        quickSort(students, 0, students.size() - 1);
    }

    private static void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private static int partition(List<Student> students, int low, int high) {
        int pivot = students.get(high).getRecordBookNumber();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getRecordBookNumber() < pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }
}

