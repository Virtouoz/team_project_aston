package com.learn.service;

import com.learn.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceMultithreadTest {

    @Test
    void testMultithreadedCountOccurrences() throws InterruptedException {
        StudentService service = new StudentService();

        for (int i = 0; i < 100; i++) {
            int book = (i % 5 == 0) ? 99999 : 10000 + i;

            double grade = 2.0 + (i % 30) / 10.0;

            service.getStudents().add(Student.builder()
                    .groupNumber("G" + (i + 1))
                    .averageGrade(grade)
                    .recordBookNumber(book)
                    .build());
        }

        int count = service.countOccurrences(99999, 4);

        assertEquals(20, count);
    }

    @Test
    void testCountWithOneThread() {
        StudentService service = new StudentService();

        // Исправлено: все поля заполнены корректно
        service.getStudents().add(Student.builder()
                .groupNumber("TestGroup")
                .averageGrade(4.0)
                .recordBookNumber(12345)
                .build());

        assertEquals(1, service.countOccurrences(12345, 1));
        assertEquals(1, service.countOccurrences(12345, 10));
    }

    @Test
    void testCountZeroOccurrences() {
        StudentService service = new StudentService();

        service.getStudents().add(Student.builder()
                .groupNumber("A1")
                .averageGrade(3.5)
                .recordBookNumber(11111)
                .build());

        assertEquals(0, service.countOccurrences(99999, 2));
    }
}