package com.learn.sort;

import com.learn.collection.CustomArrayList;
import com.learn.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortingAlgorithmsTest {

    private final List<SortStrategy> normalStrategies = List.of(
            new BubbleSort(),
            new InsertionSort(),
            new QuickSort(),
            new MergeSort()
    );

    private final List<SortStrategy> specialStrategies = List.of(
            new EvenOnlySortStrategy(new BubbleSort()),
            new EvenOnlySortStrategy(new InsertionSort()),
            new EvenOnlySortStrategy(new QuickSort()),
            new EvenOnlySortStrategy(new MergeSort())
    );

    private CustomArrayList<Student> createTestList() {
        CustomArrayList<Student> list = new CustomArrayList<>();
        list.add(Student.builder().groupNumber("C").averageGrade(3.0).recordBookNumber(10001).build()); // нечёт
        list.add(Student.builder().groupNumber("A").averageGrade(5.0).recordBookNumber(10002).build()); // чёт
        list.add(Student.builder().groupNumber("D").averageGrade(2.0).recordBookNumber(10003).build()); // нечёт
        list.add(Student.builder().groupNumber("B").averageGrade(4.0).recordBookNumber(10004).build()); // чёт
        list.add(Student.builder().groupNumber("E").averageGrade(1.0).recordBookNumber(10005).build()); // нечёт
        return list;
    }

    @Test
    void testNormalSortingAlgorithms() {
        Comparator<Student> comp = StudentComparators.byAverageGrade();

        for (SortStrategy strategy : normalStrategies) {
            CustomArrayList<Student> list = createTestList();
            CustomArrayList<Student> copy = copyList(list);

            strategy.sort(copy, comp);

            assertTrue(isSorted(copy, comp), strategy.getClass().getSimpleName() + " не отсортировал список");
        }
    }

    @Test
    void testSpecialSortingPreservesOddPositions() {
        Comparator<Student> comp = StudentComparators.byAverageGrade();

        CustomArrayList<Student> original = createTestList();
        Student odd1 = original.get(0);
        Student odd2 = original.get(2);
        Student odd3 = original.get(4);

        for (SortStrategy strategy : specialStrategies) {
            CustomArrayList<Student> list = copyList(original);
            strategy.sort(list, comp);

            assertEquals(odd1, list.get(0), "Нечётная позиция 0 изменилась в " + strategy.getClass().getSimpleName());
            assertEquals(odd2, list.get(2), "Нечётная позиция 2 изменилась");
            assertEquals(odd3, list.get(4), "Нечётная позиция 4 изменилась");

            // Проверим, что чётные отсортированы
            assertTrue(list.get(1).getAverageGrade() <= list.get(3).getAverageGrade());
        }
    }

    private boolean isSorted(CustomArrayList<Student> list, Comparator<Student> comp) {
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i - 1), list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private CustomArrayList<Student> copyList(CustomArrayList<Student> source) {
        CustomArrayList<Student> copy = new CustomArrayList<>();
        for (Student s : source) copy.add(s);
        return copy;
    }
}