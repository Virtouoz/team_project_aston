package com.learn.sort;

import com.learn.model.Student;
import java.util.ArrayList;
import java.util.List;

public class SpecialInsertionSort {
    public static void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }

        List<Integer> evenIndices = new ArrayList<>();
        List<Student> evenStudents = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRecordBookNumber() % 2 == 0) {
                evenIndices.add(i);
                evenStudents.add(students.get(i));
            }
        }

        if (evenStudents.isEmpty()) {
            return;
        }

        InsertionSort.sort(evenStudents);

        for (int i = 0; i < evenIndices.size(); i++) {
            students.set(evenIndices.get(i), evenStudents.get(i));
        }
    }
}

