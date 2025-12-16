package com.learn.sort;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import java.util.Comparator;
import java.util.List;

public class SpecialQuickSort implements SortStrategy {
    private final QuickSort quickSort = new QuickSort();

    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }

        List<Integer> evenIndices = new CustomArrayList<Integer>();
        List<Student> evenStudents = new CustomArrayList<Student>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRecordBookNumber() % 2 == 0) {
                evenIndices.add(i);
                evenStudents.add(students.get(i));
            }
        }

        if (evenStudents.isEmpty()) {
            return;
        }

        quickSort.sort(evenStudents, comparator);

        for (int i = 0; i < evenIndices.size(); i++) {
            students.set(evenIndices.get(i), evenStudents.get(i));
        }
    }
}

