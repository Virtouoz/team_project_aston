package com.learn.file_access.handler;

import com.learn.collection.CustomArrayList;
import com.learn.exceptions.UnsupportedFileTypeException;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Implementation of {@link FileHandlerResolver} that selects a {@link FileHandler}
 * based on the file extension.
 *
 * <p>Currently supports only {@code .json} files via {@link JsonFileHandler}.</p>
 */
public class ExtensionBasedResolver implements FileHandlerResolver<CustomArrayList<Student>> {
    /**
     * Resolves the file handler based on the file extension.
     *
     * @param path the path to the file
     * @return the appropriate {@link FileHandler} instance
     * @throws UnsupportedFileTypeException if the file extension is not supported
     */
    @Override
    public FileHandler<CustomArrayList<Student>> resolve(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

        switch (fileExtension) {
            case ".json":
                return new JsonFileHandler();
            default:
                throw new UnsupportedFileTypeException(path);
        }
    }
}
