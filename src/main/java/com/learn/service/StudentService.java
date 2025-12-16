package com.learn.service;

import com.learn.collection.CustomArrayList;
import com.learn.file_access.FileManager;
import com.learn.model.Student;
import com.learn.sort.SortStrategy;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class StudentService {
    private final CustomArrayList<Student> students = new CustomArrayList<>();
    private Random rnd = new Random();

    public void fillRandom(int count) {
        List<Student> newStudents = IntStream.range(0, count)
                .mapToObj(i -> new Student.Builder()
                        .groupNumber("Г" + (rnd.nextInt(10) + 1))
                        .averageGrade(rnd.nextDouble() * 5.0)
                        .recordBookNumber(1000 + rnd.nextInt(9000))
                        .build())
                .toList();
        students.addAll(newStudents);
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

    public void fillFromFile(String filename) throws IOException {
        Iterable<Student> loaded = FileManager.getInstance().loadData(filename);
        loaded.forEach(students::add);
    }

    public void sort(SortStrategy strategy, Comparator<Student> comparator) {
        strategy.sort(students, comparator);
    }

    public int countOccurrences(int targetRecordBookNumber, int numThreads) {
        if (numThreads <= 0) numThreads = 1;
        int chunkSize = students.size() / numThreads;
        AtomicInteger count = new AtomicInteger(0);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? students.size() : start + chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (students.get(j).getRecordBookNumber() == targetRecordBookNumber) {
                        count.incrementAndGet();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return count.get();
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
