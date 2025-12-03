package com.learn.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface DataProvider<T> {
    public void loadFromFile(Path pathToFile) throws IOException;
    public T getNextItem();
    public boolean hasNext();
}

