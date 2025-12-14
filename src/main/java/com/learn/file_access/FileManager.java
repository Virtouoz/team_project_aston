package com.learn.file_access;

import com.learn.collection.CustomArrayList;
import com.learn.exceptions.CustomFileAccessException;
import com.learn.exceptions.FileParseException;
import com.learn.file_access.handler.ExtensionBasedResolver;
import com.learn.file_access.handler.FileHandler;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private static final FileManager instance = new FileManager();
    private FileManager(){}

    public static FileManager getInstance(){
        return instance;
    }

    public void writeData(CustomArrayList<Student> studentsData) throws IOException {
        final String DEFAULT_WR_FILE_PATH = "src/main/resources/data_files/WriteTest.json";
        writeData(studentsData, DEFAULT_WR_FILE_PATH);
    }

    public void writeData(CustomArrayList<Student> studentsData, String path) throws IOException {
        Path finalPath = Paths.get(path).toAbsolutePath();

        ExtensionBasedResolver resolver = new ExtensionBasedResolver();
        FileHandler<CustomArrayList<Student>> fileHandler = resolver.resolve(finalPath);
        fileHandler.save(studentsData, finalPath);
    }

    public Iterable<Student> loadData() throws IOException {
        final String DEFAULT_DATA_SET_FILE_PATH = "src/main/resources/data_files/Students.json";
        return loadData(DEFAULT_DATA_SET_FILE_PATH);
    }

    public Iterable<Student> loadData(String pathAsString) throws IOException {
        Path finalPath = Paths.get(pathAsString).toAbsolutePath();

        if (finalPath.toFile().isDirectory())
            throw new CustomFileAccessException("Requested target is not a File!");

        ExtensionBasedResolver resolver = new ExtensionBasedResolver();
        FileHandler<CustomArrayList<Student>> fileHandler = resolver.resolve(finalPath);
        CustomArrayList<Student> parsedStudentsData = fileHandler.load(finalPath);

        if (finalPath.toFile().length() > 0 && parsedStudentsData.isEmpty())
            throw new FileParseException(finalPath);

        return parsedStudentsData;
    }
}
