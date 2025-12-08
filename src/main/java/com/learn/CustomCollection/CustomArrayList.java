package com.learn.CustomCollection;

import java.util.Objects;

public class CustomArrayList<E> {
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
            Object[] newData = new Object[newCapacity];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
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

    public void add(E element) {
        ensureCapacity();
        data[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
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

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        return removedElement;
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
