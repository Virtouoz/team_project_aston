package com.learn.sort;

import com.learn.model.Student;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }
        mergeSort(students, 0, students.size() - 1);
    }

    private static void mergeSort(List<Student> students, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(students, left, mid);
            mergeSort(students, mid + 1, right);
            merge(students, left, mid, right);
        }
    }

    private static void merge(List<Student> students, int left, int mid, int right) {
        List<Student> leftList = new ArrayList<>();
        List<Student> rightList = new ArrayList<>();

        for (int i = left; i <= mid; i++) {
            leftList.add(students.get(i));
        }
        for (int i = mid + 1; i <= right; i++) {
            rightList.add(students.get(i));
        }

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getRecordBookNumber() <= rightList.get(j).getRecordBookNumber()) {
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

