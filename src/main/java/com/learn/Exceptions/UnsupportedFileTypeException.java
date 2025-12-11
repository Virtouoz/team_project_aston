package com.learn.Exceptions;

import java.io.IOException;
import java.nio.file.Path;

public class UnsupportedFileTypeException extends IOException {
    public UnsupportedFileTypeException(Path path) {
        super(String.format(
                "File at %s is not supported by any parser!",
                path.toAbsolutePath()
        ));
    }
}
