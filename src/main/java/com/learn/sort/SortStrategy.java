package com.learn.sort;

import com.learn.model.Student;
import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(List<Student> students, Comparator<Student> comparator);
}
