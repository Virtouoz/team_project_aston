package com.learn.sort;

import com.learn.model.Student;
import java.util.List;

public class InsertionSort {
    public static void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }

        for (int i = 1; i < students.size(); i++) {
            Student key = students.get(i);
            int j = i - 1;

            while (j >= 0 && students.get(j).getRecordBookNumber() > key.getRecordBookNumber()) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, key);
        }
    }
}

