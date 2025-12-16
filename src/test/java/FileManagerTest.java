package com.learn.file_access;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileManagerTest {

    @TempDir
    Path tempDir;

    @Test
    void testWriteAndLoadJson() throws IOException {
        Path tempFile = tempDir.resolve("test.json");

        CustomArrayList<Student> batch1 = new CustomArrayList<>();
        batch1.add(Student.builder().groupNumber("T1").averageGrade(4.0).recordBookNumber(90001).build());
        batch1.add(Student.builder().groupNumber("T2").averageGrade(3.5).recordBookNumber(90002).build());

        CustomArrayList<Student> batch2 = new CustomArrayList<>();
        batch2.add(Student.builder().groupNumber("T3").averageGrade(5.0).recordBookNumber(90003).build());

        FileManager fm = FileManager.getInstance();

        fm.writeData(batch1, tempFile.toString());

        fm.writeData(batch2, tempFile.toString());

        Iterable<Student> loaded = fm.loadData(tempFile.toString());
        CustomArrayList<Student> loadedList = new CustomArrayList<>();
        loaded.forEach(loadedList::add);

        assertEquals(3, loadedList.size()); // Теперь ожидается 3
        assertEquals("T1", loadedList.get(0).getGroupNumber());
        assertEquals("T3", loadedList.get(2).getGroupNumber());
    }

    @Test
    void testLoadNonExistentFile() {
        FileManager fm = FileManager.getInstance();
        assertThrows(IOException.class, () -> fm.loadData(tempDir.resolve("nonexistent.json").toString()));
    }
}