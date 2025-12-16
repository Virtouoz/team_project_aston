package com.learn.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testValidStudentCreation() {
        Student student = Student.builder()
                .groupNumber("A1")
                .averageGrade(4.5)
                .recordBookNumber(10001)
                .build();

        assertEquals("A1", student.getGroupNumber());
        assertEquals(4.5, student.getAverageGrade(), 0.001);
        assertEquals(10001, student.getRecordBookNumber());
    }

    @Test
    void testInvalidGroupNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                Student.builder()
                        .groupNumber("")
                        .averageGrade(4.0)
                        .recordBookNumber(10001)
                        .build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder()
                        .groupNumber("ЭтоГруппаДлиннееДвадцатиСимволов")
                        .averageGrade(4.0)
                        .recordBookNumber(10001)
                        .build());
    }

    @Test
    void testInvalidAverageGrade() {
        assertThrows(IllegalArgumentException.class, () ->
                Student.builder()
                        .groupNumber("A1")
                        .averageGrade(-0.1)
                        .recordBookNumber(10001)
                        .build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder()
                        .groupNumber("A1")
                        .averageGrade(5.1)
                        .recordBookNumber(10001)
                        .build());
    }

    @Test
    void testInvalidRecordBookNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                Student.builder()
                        .groupNumber("A1")
                        .averageGrade(4.0)
                        .recordBookNumber(0)
                        .build());
    }

    @Test
    void testToStringAndEquals() {
        Student s1 = Student.builder()
                .groupNumber("A1")
                .averageGrade(4.0)
                .recordBookNumber(10001)
                .build();

        Student s2 = Student.builder()
                .groupNumber("A1")
                .averageGrade(4.0)
                .recordBookNumber(10001)
                .build();

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotNull(s1.toString());
    }
}