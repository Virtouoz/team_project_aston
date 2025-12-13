package com.learn.sort;

import com.learn.model.Student;
import java.util.Comparator;

public class StudentComparators {
    public static Comparator<Student> byGroupNumber() {
        return Comparator.comparing(Student::getGroupNumber);
    }

    public static Comparator<Student> byAverageGrade() {
        return Comparator.comparingDouble(Student::getAverageGrade);
    }

    public static Comparator<Student> byRecordBookNumber() {
        return Comparator.comparingInt(Student::getRecordBookNumber);
    }
}

