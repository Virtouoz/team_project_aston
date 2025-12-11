package com.learn;

import com.learn.FileAccess.FileManager;
import com.learn.model.Student;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(Student.builder().build());

        try {
            Iterable<Student> students = FileManager.getInstance().loadData();

            for (Student student : students) {
                System.out.println(student);
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}