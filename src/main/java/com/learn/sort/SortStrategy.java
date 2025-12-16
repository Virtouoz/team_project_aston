package com.learn.sort;

import com.learn.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Strategy interface for sorting (Strategy pattern).
 * All implementations sort the passed list in-place.
 */
public interface SortStrategy {
    /**
     * Sorts the list of students using the given comparator.
     *
     * @param students   the list to sort (not null)
     * @param comparator the comparator for comparing students (not null)
     */
    void sort(List<Student> students, Comparator<Student> comparator);
}
