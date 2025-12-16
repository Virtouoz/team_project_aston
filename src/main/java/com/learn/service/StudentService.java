package com.learn.service;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import com.learn.sort.SortStrategy;

import java.util.*;

public class StudentService {
    private final CustomArrayList<Student> students = new CustomArrayList<>();

    public void fillRandom(int count) {
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            Student student = new Student.Builder()
                    .groupNumber("Г" + (rnd.nextInt(10) + 1))
                    .averageGrade(rnd.nextDouble() * 5.0)
                    .recordBookNumber(1000 + rnd.nextInt(9000))
                    .build();
            students.add(student);
        }
    }

    public void fillManual(int count, Scanner scanner) throws IllegalArgumentException {
        for (int i = 0; i < count; i++) {
            System.out.printf("Студент %d:%n", i + 1);

            String group = scanner.nextLine().trim();
            double grade = Double.parseDouble(scanner.nextLine());
            int book = Integer.parseInt(scanner.nextLine());

            Student student = new Student.Builder()
                    .groupNumber(group)
                    .averageGrade(grade)
                    .recordBookNumber(book)
                    .build();

            students.add(student);
        }
    }

    // обращается к классам, реализующим заполнение списка из файла
    public void fillFromFile(String filename) {

    }

    // обращается к классам, реализующим сортировку
    public void sort(SortStrategy strategy, Comparator comparator) {

    }

    public void printAll() {
        if (students.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public CustomArrayList<Student> getStudents() {
        return students;
    }

    public void clear() {
        students.clear();
        System.out.println("Список студентов очищен");
    }
}
