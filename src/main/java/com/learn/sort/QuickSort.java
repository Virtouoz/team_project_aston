package com.learn.sort;

import com.learn.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Quick Sort algorithm.
 */
public class QuickSort implements SortStrategy {
    private Comparator<Student> comparator;

    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }
        this.comparator = comparator;
        quickSort(students, 0, students.size() - 1);
    }

    private void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(students.get(j), pivot) < 0) {
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

