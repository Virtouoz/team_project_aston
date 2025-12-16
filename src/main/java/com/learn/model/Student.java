package com.learn.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A class representing a student with immutable fields.
 * <p>
 * Required fields: group number, average grade (from 0.0 to 5.0), record book number (positive integer).
 * Object creation is only possible through the Builder pattern with mandatory data validation.
 * </p>
 */
public class Student {
    private final String groupNumber;
    private final double averageGrade;
    private final int recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageGrade = builder.averageGrade;
        this.recordBookNumber = builder.recordBookNumber;

        validateGroupNumber(this.groupNumber);
        validateAverageGrade(this.averageGrade);
        validateRecordBookNumber(this.recordBookNumber);
    }

    private void validateGroupNumber(String groupNumber) {
        if (groupNumber == null || groupNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("The group number cannot be null or empty");
        }
        if (groupNumber.trim().length() > 20) {
            throw new IllegalArgumentException("The group number cannot be longer than 20 characters");
        }
    }

    private void validateAverageGrade(double averageGrade) {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new IllegalArgumentException("GPA must be in the range of 0.0 to 5.0, obtained: " + averageGrade);
        }
    }

    private void validateRecordBookNumber(int recordBookNumber) {
        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException("The number of the record must be a positive number, received: " + recordBookNumber);
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for the {@link Student} object.
     * All fields must be set before calling {@link #build()}.
     */
    public static class Builder {
        private String groupNumber;
        private double averageGrade;
        private int recordBookNumber;

        private Builder() {
        }

        public Builder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}