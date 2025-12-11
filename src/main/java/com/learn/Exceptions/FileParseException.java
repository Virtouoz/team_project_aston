package com.learn.Exceptions;

import java.io.IOException;
import java.nio.file.Path;

public class FileParseException extends IOException {
    public FileParseException(Path path) {
        super(String.format(
                "File at %s can't be parsed, but it have some data in it.",
                path.toString()
        ));
    }
}
