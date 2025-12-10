package com.learn.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Student {
    private final String groupNumber;
    private final double averageGrade;
    private final int recordBookNumber;

    public Student(String groupNumber, double averageGrade, int recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Student[", "]")
                .add("groupNumber='" + groupNumber + "'")
                .add("averageGrade=" + averageGrade)
                .add("recordBookNumber=" + recordBookNumber)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Double.compare(averageGrade, student.averageGrade) == 0 &&
                recordBookNumber == student.recordBookNumber &&
                Objects.equals(groupNumber, student.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageGrade, recordBookNumber);
    }
}
