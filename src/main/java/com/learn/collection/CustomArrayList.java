package com.learn.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomArrayList<E> implements Iterable<E> {
    private volatile Object[] data;
    private volatile int size;
    private static final int DEFAULT_SIZE = 10;

    public CustomArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialSize);
        }
        this.data = new Object[initialSize];
        this.size = 0;
    }

    public CustomArrayList() {
        this(DEFAULT_SIZE);
    }

    private Object[] ensureCapacity(Object[] currentData, int currentSize) {
        if (currentSize >= currentData.length) {
            int newCapacity = currentData.length * 2;
            if (newCapacity < DEFAULT_SIZE) {
                newCapacity = DEFAULT_SIZE;
            }
            return Arrays.copyOf(currentData, newCapacity);
        }
        return currentData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + ", size: " + size
            );
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Insertion index " + index + ", allowed range: [0, " + size + "]"
            );
        }
    }

    public void add(E element) {
        Object[] newData = ensureCapacity(data, size);
        int newSize = size + 1;

        newData[size] = element;

        data = Arrays.copyOf(newData, newSize);
        size = newSize;
    }

    public void add(int index, E element) {
        checkInsertIndex(index);

        Object[] newData = Arrays.copyOf(data, size + 1);
        System.arraycopy(data, index, newData, index + 1, size - index);
        newData[index] = element;

        data = newData;
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
        Object[] newData = Arrays.copyOf(data, data.length);
        newData[index] = element;
        data = newData;
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
        Object[] newData = new Object[data.length];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index + 1, newData, index, size - index - 1);

        data = Arrays.copyOf(newData, size - 1);
        size--;

        return removedElement;
    }

    public void trimToSize() {
        if (data.length > size) {
            Object[] trimmed = Arrays.copyOf(data, size);
            data = trimmed;
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
        data = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Object[] currentData = data;
        int currentDataSize = size;

        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentDataSize;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) currentData[currentIndex++];
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
