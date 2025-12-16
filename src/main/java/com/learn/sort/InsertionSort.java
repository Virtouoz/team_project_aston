package com.learn.sort;

import com.learn.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Insertion Sort algorithm.
 */
public class InsertionSort implements SortStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }

        for (int i = 1; i < students.size(); i++) {
            Student key = students.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(students.get(j), key) > 0) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, key);
        }
    }
}

