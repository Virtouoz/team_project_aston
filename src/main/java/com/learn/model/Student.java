package com.learn.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Student {
    private final String groupNumber;
    private final double averageGrade;
    private final int recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageGrade = builder.averageGrade;
        this.recordBookNumber = builder.recordBookNumber;
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