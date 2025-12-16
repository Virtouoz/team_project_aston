package com.learn.sort;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Generic "special" sorting strategy for the additional task.
 * <p>
 * Only students with an <b>even</b> record book number are sorted.
 * Students with an odd record book number remain in their original positions.
 * </p>
 * <p>
 * Works with any base sorting strategy passed via constructor.
 * </p>
 */
public class EvenOnlySortStrategy implements SortStrategy {

    private final SortStrategy baseStrategy;

    /**
     * Constructs a special strategy using the provided base sorting algorithm.
     *
     * @param baseStrategy the underlying sorting algorithm to apply to even elements
     */
    public EvenOnlySortStrategy(SortStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }

    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }

        List<Integer> evenIndices = new CustomArrayList<>();
        List<Student> evenStudents = new CustomArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRecordBookNumber() % 2 == 0) {
                evenIndices.add(i);
                evenStudents.add(students.get(i));
            }
        }

        if (evenStudents.isEmpty()) {
            return;
        }

        baseStrategy.sort(evenStudents, comparator);

        for (int i = 0; i < evenIndices.size(); i++) {
            students.set(evenIndices.get(i), evenStudents.get(i));
        }
    }
}