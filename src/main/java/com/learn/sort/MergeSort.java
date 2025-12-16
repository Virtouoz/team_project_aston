package com.learn.sort;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Merge Sort algorithm.
 */
public class MergeSort implements SortStrategy {
    private Comparator<Student> comparator;

    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }
        this.comparator = comparator;
        mergeSort(students, 0, students.size() - 1);
    }

    private void mergeSort(List<Student> students, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(students, left, mid);
            mergeSort(students, mid + 1, right);
            merge(students, left, mid, right);
        }
    }

    private void merge(List<Student> students, int left, int mid, int right) {
        List<Student> leftList = new CustomArrayList<>();
        List<Student> rightList = new CustomArrayList<>();

        for (int i = left; i <= mid; i++) {
            leftList.add(students.get(i));
        }
        for (int i = mid + 1; i <= right; i++) {
            rightList.add(students.get(i));
        }

        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                students.set(k++, leftList.get(i++));
            } else {
                students.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            students.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            students.set(k++, rightList.get(j++));
        }
    }
}

