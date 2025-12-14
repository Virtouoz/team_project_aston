package com.learn.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Student {
    private final String groupNumber;
    private final double averageGrade;
    private final int recordBookNumber;

    public Student(String groupNumber, double averageGrade, int recordBookNumber) {
        validateGroupNumber(groupNumber);
        validateAverageGrade(averageGrade);
        validateRecordBookNumber(recordBookNumber);
        
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }
    
    private void validateGroupNumber(String groupNumber) {
        if (groupNumber == null || groupNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер группы не может быть null или пустым");
        }
        if (groupNumber.trim().length() > 20) {
            throw new IllegalArgumentException("Номер группы не может быть длиннее 20 символов");
        }
    }
    
    private void validateAverageGrade(double averageGrade) {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне от 0.0 до 5.0, получено: " + averageGrade);
        }
    }
    
    private void validateRecordBookNumber(int recordBookNumber) {
        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException("Номер зачетки должен быть положительным числом, получено: " + recordBookNumber);
        }
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
