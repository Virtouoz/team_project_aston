package com.learn.file_access.handler;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface for resolving a {@link FileHandler} based on a file path.
 * Used by {@link com.learn.file_access.FileManager} to select the appropriate handler
 * for a given file (typically based on its extension).
 *
 * @param <T> the type of data handled by the resolved handler
 */
public interface FileHandlerResolver<T> {
    /**
     * Resolves and returns the appropriate {@link FileHandler} for the given file path.
     *
     * @param path the path to the file
     * @return the resolved file handler
     * @throws IOException if no suitable handler is found (e.g., unsupported file type)
     */
    FileHandler<T> resolve(Path path) throws IOException;
}
