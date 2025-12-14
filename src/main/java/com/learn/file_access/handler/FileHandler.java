package com.learn.file_access.handler;

import java.io.IOException;
import java.nio.file.Path;

public interface FileHandler<T> {
    T load(Path path) throws IOException;
    void save(T data, Path path) throws IOException;
}
