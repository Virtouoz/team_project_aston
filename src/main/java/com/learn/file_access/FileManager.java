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

/**
 * Singleton class responsible for managing file operations related to {@link Student} data.
 * It provides methods to load students from files and save (append) collections of students to files.
 * The actual file handling is delegated to {@link FileHandler} implementations resolved by extension.
 *
 * <p>This class supports different file formats through the strategy pattern
 * (see {@link com.learn.file_access.handler.FileHandlerResolver}).</p>
 */
public class FileManager {

    private static final FileManager instance = new FileManager();
    private FileManager(){}

    public static FileManager getInstance(){
        return instance;
    }

    /**
     * Saves the given collection of students to the default output file
     * ({@code src/main/resources/data_files/WriteTest.json}) in append mode.
     *
     * @param studentsData the collection of students to save
     * @throws IOException if an I/O error occurs during writing or resolving the handler
     */
    public void writeData(CustomArrayList<Student> studentsData) throws IOException {
        final String DEFAULT_WR_FILE_PATH = "src/main/resources/data_files/WriteTest.json";
        writeData(studentsData, DEFAULT_WR_FILE_PATH);
    }

    /**
     * Saves the given collection of students to the specified file path in append mode.
     * The appropriate {@link FileHandler} is resolved based on the file extension.
     *
     * @param studentsData the collection of students to save
     * @param path         the path to the file (as a string)
     * @throws IOException if an I/O error occurs or the file type is not supported
     */
    public void writeData(CustomArrayList<Student> studentsData, String path) throws IOException {
        Path finalPath = Paths.get(path).toAbsolutePath();

        ExtensionBasedResolver resolver = new ExtensionBasedResolver();
        FileHandler<CustomArrayList<Student>> fileHandler = resolver.resolve(finalPath);
        fileHandler.save(studentsData, finalPath);
    }

    /**
     * Loads students from the default dataset file
     * ({@code src/main/resources/data_files/Students.json}).
     *
     * @return an iterable collection of loaded {@link Student} objects
     * @throws IOException if an I/O error occurs or parsing fails
     */
    public Iterable<Student> loadData() throws IOException {
        final String DEFAULT_DATA_SET_FILE_PATH = "src/main/resources/data_files/Students.json";
        return loadData(DEFAULT_DATA_SET_FILE_PATH);
    }

    /**
     * Loads students from the specified file path.
     * The appropriate {@link FileHandler} is resolved based on the file extension.
     *
     * @param pathAsString the path to the file (as a string)
     * @return an iterable collection of loaded {@link Student} objects
     * @throws IOException               if an I/O error occurs
     * @throws CustomFileAccessException if the path points to a directory instead of a file
     * @throws FileParseException        if the file is non-empty but cannot be parsed correctly
     */
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
