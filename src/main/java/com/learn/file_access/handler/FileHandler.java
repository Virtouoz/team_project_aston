package com.learn.file_access.handler;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Strategy interface for handling file operations on a specific type {@code T}.
 * Implementations are responsible for loading data from a file and saving data to a file.
 *
 * <p>This interface is part of the Strategy pattern used in {@link com.learn.file_access.FileManager}
 * to support different file formats (e.g., JSON, CSV).</p>
 *
 * @param <T> the type of data handled by this handler (e.g., {@code CustomArrayList<Student>})
 */
public interface FileHandler<T> {
    /**
     * Loads data from the specified file path.
     *
     * @param path the path to the file to read
     * @return the loaded data object
     * @throws IOException if an I/O error occurs or parsing fails
     */
    T load(Path path) throws IOException;

    /**
     * Saves the given data to the specified file path.
     *
     * @param data the data object to save
     * @param path the path to the file to write
     * @throws IOException if an I/O error occurs during writing
     */
    void save(T data, Path path) throws IOException;
}
