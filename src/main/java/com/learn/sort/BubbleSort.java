package com.learn.sort;

import com.learn.model.Student;
import java.util.List;

public class BubbleSort {
    public static void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }

        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getRecordBookNumber() > students.get(j + 1).getRecordBookNumber()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}

