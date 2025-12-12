package com.learn.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomArrayList<E> implements Iterable<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public CustomArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Ёмкость не может быть отрицательной: " + initialSize);
        }
        this.data = new Object[initialSize];
        this.size = 0;
    }

    public CustomArrayList() {
        this(DEFAULT_SIZE);
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = data.length * 2;
            if (newCapacity < DEFAULT_SIZE) {
                newCapacity = DEFAULT_SIZE;
            }
            Object[] newData = new Object[newCapacity];

            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index + ", размер: " + size
            );
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Индекс для вставки " + index + ", допустимый диапазон: [0, " + size + "]"
            );
        }
    }

    public void add(E element) {
        ensureCapacity();
        data[size++] = element;
    }

    public void add(int index, E element) {
        checkInsertIndex(index);
        ensureCapacity();

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, E element) {
        checkIndex(index);
        data[index] = element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) data[index];

        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null;

        return removedElement;
    }

    public void trimToSize() {
        if (data.length > size) {
            data = Arrays.copyOf(data, size);
        }
    }

    public boolean contains(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, data[i])) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
