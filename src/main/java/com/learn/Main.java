package com.learn;

import com.learn.DataProvider.JsonDataProvider;
import com.learn.DataProvider.XMLDataProvider;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        // NOTE: This should throw error invalid data -> empty Student
        System.out.println(Student.builder().build());


        System.out.println("json test");
        jsonTest();
        System.out.println("xml test");
        xmlTest();
    }

    private static void jsonTest() {
        var jsonReader = new JsonDataProvider();
        Path relativePath = Paths.get("src", "main", "TestData", "StudentsData.json");
        jsonReader.loadFromFile(relativePath);

        while(jsonReader.hasNext()){
            System.out.println(jsonReader.getNextItem());
        }
    }

    private static void xmlTest() {
        var jsonReader = new XMLDataProvider();
        Path relativePath = Paths.get("src", "main", "TestData", "StudentsData.xml");

        try {
            jsonReader.loadFromFile(relativePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while(jsonReader.hasNext()){
            System.out.println(jsonReader.getNextItem());
        }
    }
}