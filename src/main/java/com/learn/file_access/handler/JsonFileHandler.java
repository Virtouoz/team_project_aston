package com.learn.file_access.handler;

import com.google.gson.*;
import com.learn.collection.CustomArrayList;
import com.learn.model.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class JsonFileHandler implements FileHandler<CustomArrayList<Student>> {
    @Override
    public CustomArrayList<Student> load(Path path) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(path.toAbsolutePath().toString());

        Student[] students = gson.fromJson(reader, Student[].class);

        CustomArrayList<Student> resData = new CustomArrayList<>();

        for(Student student : students){
            resData.add(student);
        }

        reader.close();

        return resData;
    }

    @Override
    public void save(CustomArrayList<Student> data, Path path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = new FileReader(path.toString());
        JsonArray studentsArray = new JsonArray();

        JsonElement jsonElement = JsonParser.parseReader(reader);

        if (jsonElement.isJsonArray())
            studentsArray = jsonElement.getAsJsonArray();

        JsonElement newArrayData = gson.toJsonTree(data);
        studentsArray.add(newArrayData);

        reader.close();

        FileWriter writer = new FileWriter(path.toFile());
        gson.toJson(studentsArray, writer);
        writer.close();
    }
}
