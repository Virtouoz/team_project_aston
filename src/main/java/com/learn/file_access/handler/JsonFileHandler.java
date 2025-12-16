package com.learn.file_access.handler;

import com.google.gson.*;
import com.learn.collection.CustomArrayList;
import com.learn.model.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * {@link FileHandler} implementation for JSON files containing arrays of {@link Student} objects.
 * Supports loading from JSON and saving (appending) new students while preserving existing data.
 *
 * <p>The file is expected to contain a top-level JSON array of student objects.
 * When saving, new students are appended as individual elements to this array.</p>
 */
public class JsonFileHandler implements FileHandler<CustomArrayList<Student>> {

    /**
     * Loads students from a JSON file.
     * The file must contain a JSON array of student objects.
     *
     * @param path the path to the JSON file
     * @return a {@link CustomArrayList} containing the parsed {@link Student} objects
     * @throws IOException if reading or parsing fails
     */
    @Override
    public CustomArrayList<Student> load(Path path) throws IOException {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(path.toAbsolutePath().toString())) {
            Student[] students = gson.fromJson(reader, Student[].class);

            CustomArrayList<Student> resData = new CustomArrayList<>();

            if (students != null) {
                for (Student student : students) {
                    resData.add(student);
                }
            }

            return resData;
        }
    }

    /**
     * Saves (appends) the given students to a JSON file.
     * If the file already exists and contains a valid JSON array, new students are added to it.
     * If the file is empty or does not exist, a new array is created.
     *
     * @param data the collection of students to append
     * @param path the path to the JSON file
     * @throws IOException if reading, parsing, or writing fails
     */
    @Override
    public void save(CustomArrayList<Student> data, Path path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray studentsArray = new JsonArray();

        if (path.toFile().exists() && path.toFile().length() > 0) {
            try (FileReader reader = new FileReader(path.toString())) {
                JsonElement jsonElement = JsonParser.parseReader(reader);

                if (jsonElement.isJsonArray()) {
                    studentsArray = jsonElement.getAsJsonArray();
                } else if (!jsonElement.isJsonNull()) {
                    throw new JsonParseException("Existing file does not contain a JSON array at root level");
                }
            } catch (JsonParseException e) {
                throw new IOException("Failed to parse existing JSON file: " + e.getMessage(), e);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            Student student = data.get(i);
            if (student != null) {
                studentsArray.add(gson.toJsonTree(student));
            }
        }

        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(studentsArray, writer);
        }
    }
}