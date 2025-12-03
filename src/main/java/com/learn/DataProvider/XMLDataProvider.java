package com.learn.DataProvider;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XMLDataProvider implements DataProvider<Student>{
    XmlMapper mapper = new XmlMapper();

    // TODO: remove/replace ?
    private List<Student> students = new ArrayList<>();
    private int internalIdx = -1;

    @Override
    public void loadFromFile(Path pathToFile) throws IOException {
//        students = List.of(mapper.readValue(Files.newBufferedReader(pathToFile), Student[].class));

        System.out.println(mapper.readValue(Files.newBufferedReader(pathToFile), Student.class));
    }

    @Override
    public Student getNextItem() throws IndexOutOfBoundsException {
        return students.get(internalIdx++);
    }

    @Override
    public boolean hasNext() {
        return internalIdx >= 0 && internalIdx < students.size();
    }
}
