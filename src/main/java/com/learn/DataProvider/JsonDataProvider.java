package com.learn.DataProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonDataProvider implements DataProvider<Student> {
    private final Gson gson = new Gson();

    // TODO: remove/replace
    private List<Student> students = new ArrayList<>();
    private int internalIdx = -1;

    @Override
    public void loadFromFile(Path pathToFile) {

        try(var reader = Files.newBufferedReader(pathToFile)){
            students = gson.fromJson(
                    reader,
                    new TypeToken<List<Student>>(){}.getType()
            );
            internalIdx = 0;
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
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
