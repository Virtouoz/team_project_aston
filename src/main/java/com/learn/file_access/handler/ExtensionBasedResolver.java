package com.learn.file_access.handler;

import com.learn.collection.CustomArrayList;
import com.learn.exceptions.UnsupportedFileTypeException;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Path;

public class ExtensionBasedResolver implements FileHandlerResolver<CustomArrayList<Student>> {
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
