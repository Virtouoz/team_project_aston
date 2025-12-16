package com.learn.sort;

import com.learn.model.Student;

import java.util.Comparator;

/**
 * Utility class with ready-made comparators for sorting {@link Student}.
 */
public class StudentComparators {

    /**
     * @return comparator by group number (lexicographically)
     */
    public static Comparator<Student> byGroupNumber() {
        return Comparator.comparing(Student::getGroupNumber);
    }

    /**
     * @return comparator by average grade (ascending)
     */
    public static Comparator<Student> byAverageGrade() {
        return Comparator.comparingDouble(Student::getAverageGrade);
    }

    /**
     * @return comparator by record book number (ascending)
     */
    public static Comparator<Student> byRecordBookNumber() {
        return Comparator.comparingInt(Student::getRecordBookNumber);
    }
}



