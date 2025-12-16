package com.learn;

import com.learn.collection.CustomArrayList;
import com.learn.file_access.FileManager;
import com.learn.model.Student;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = FileManager.getInstance();

        try {
            // Шаг 1: Загружаем исходные данные из Students.json
            System.out.println("=== Загрузка исходных данных из Students.json ===");
            CustomArrayList<Student> initialStudents = (CustomArrayList<Student>) fileManager.loadData();
            System.out.println("Загружено студентов: " + initialStudents.size());
            System.out.println("Первые 5 студентов:");
            for (int i = 0; i < Math.min(5, initialStudents.size()); i++) {
                System.out.println("  " + initialStudents.get(i));
            }
            System.out.println();
            System.out.println("=== Запись студентов в WriteTest.json (append) ===");
            fileManager.writeData(initialStudents);
            System.out.println("Запись завершена.\n");

            // Шаг 2: Создаём новых студентов
            System.out.println("=== Создание новых студентов ===");
            CustomArrayList<Student> newStudents = new CustomArrayList<>();

            newStudents.add(Student.builder()
                    .groupNumber("Z1")
                    .averageGrade(4.7)
                    .recordBookNumber(99991)
                    .build());

            newStudents.add(Student.builder()
                    .groupNumber("Z2")
                    .averageGrade(3.9)
                    .recordBookNumber(99992)
                    .build());

            newStudents.add(Student.builder()
                    .groupNumber("Z3")
                    .averageGrade(4.2)
                    .recordBookNumber(99993)
                    .build());

            System.out.println("Создано новых студентов: " + newStudents.size());
            for (Student s : newStudents) {
                System.out.println("  " + s);
            }
            System.out.println();

            // Шаг 3: Записываем новые данные в WriteTest.json (в режиме append)
            System.out.println("=== Запись новых студентов в WriteTest.json (append) ===");
            fileManager.writeData(newStudents);
            System.out.println("Запись завершена.\n");

            // Шаг 4: Проверяем результат — загружаем WriteTest.json
            System.out.println("=== Проверка: загрузка из WriteTest.json после добавления ===");
            String writeTestPath = "src/main/resources/data_files/WriteTest.json";
            CustomArrayList<Student> allInWriteTest = (CustomArrayList<Student>) fileManager.loadData(writeTestPath);

            System.out.println("Всего студентов в WriteTest.json: " + allInWriteTest.size());
            System.out.println("Последние 5 студентов (должны включать новых):");
            int start = Math.max(0, allInWriteTest.size() - 5);
            for (int i = start; i < allInWriteTest.size(); i++) {
                System.out.println("  " + allInWriteTest.get(i));
            }

            // Проверяем, что новые студенты действительно добавлены
            boolean foundNew = true;
            for (Student newStudent : newStudents) {
                if (!allInWriteTest.contains(newStudent)) {
                    foundNew = false;
                    break;
                }
            }

            System.out.println("\nПроверка: новые студенты успешно добавлены? " + (foundNew ? "ДА" : "НЕТ"));

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
            e.printStackTrace();
        }
    }
}