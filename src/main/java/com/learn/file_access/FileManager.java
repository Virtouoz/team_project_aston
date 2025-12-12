package com.learn.file_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.learn.exceptions.CustomFileAccessException;
import com.learn.exceptions.FileParseException;
import com.learn.exceptions.UnsupportedFileTypeException;
import com.learn.model.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileManager {

    private static final FileManager instance = new FileManager();
    private FileManager(){}

    public static FileManager getInstance(){
        return instance;
    }

    public void writeData(Iterable<Student> studentsData) throws IOException {
        final String DEFAULT_WR_FILE_PATH = "src/main/resources/DataFiles/WriteTest.json";
        writeData(studentsData, DEFAULT_WR_FILE_PATH);
    }

    // TODO: type dep
    public void writeData(Iterable<Student> studentsData, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path finalPath = Paths.get(path).toAbsolutePath();
        FileReader reader = new FileReader(finalPath.toString());
        JsonArray studentsArray = new JsonArray();

        JsonElement jsonElement = JsonParser.parseReader(reader);

        if (jsonElement.isJsonArray())
            studentsArray = jsonElement.getAsJsonArray();

        JsonElement newArrayData = gson.toJsonTree(studentsData);
        studentsArray.add(newArrayData);

        reader.close();

        FileWriter writer = new FileWriter(finalPath.toFile());
        gson.toJson(studentsArray, writer);
        writer.close();
    }

    public Iterable<Student> loadData() throws IOException {
        final String DEFAULT_DATA_SET_FILE_PATH = "src/main/resources/DataFiles/Students.json";
        return loadData(DEFAULT_DATA_SET_FILE_PATH);
    }

    public Iterable<Student> loadData(String pathAsString) throws IOException {
        Path finalPath = Paths.get(pathAsString).toAbsolutePath();

        if (finalPath.toFile().isDirectory())
            throw new CustomFileAccessException("Requested target is not a File!");

        Student[] parsedStudentsData = null;

        String fileExtension = getFileExtension(finalPath.getFileName().toString());
        switch (fileExtension) {
            case ".json":
                Gson gson = new Gson();
                FileReader reader = new FileReader(finalPath.toString());

                parsedStudentsData = gson.fromJson(reader, Student[].class);

                reader.close();
                break;
            default:
                throw new UnsupportedFileTypeException(finalPath);
        }

        if (parsedStudentsData == null)
            return null;

        if (finalPath.toFile().length() > 0 && parsedStudentsData.length == 0)
            throw new FileParseException(finalPath);

        return Arrays.asList(parsedStudentsData);
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
