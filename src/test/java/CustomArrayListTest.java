package com.learn.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomArrayListTest {

    @Test
    void testAddAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(2));
    }

    @Test
    void testSetAndIndexOf() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);

        assertEquals(2, list.set(1, 5));
        assertEquals(5, list.get(1));
        assertEquals(0, list.indexOf(1));
    }

    @Test
    void testRemove() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("X");
        list.add("Y");
        list.remove(0);

        assertEquals(1, list.size());
        assertEquals("Y", list.get(0));
    }

    @Test
    void testGrowth() {
        CustomArrayList<Integer> list = new CustomArrayList<>(2);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(100, list.size());
        assertEquals(99, list.get(99));
    }
}