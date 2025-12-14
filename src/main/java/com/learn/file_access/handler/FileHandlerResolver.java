package com.learn.file_access.handler;

import java.io.IOException;
import java.nio.file.Path;

public interface FileHandlerResolver<T> {
    FileHandler<T> resolve(Path path) throws IOException;
}
