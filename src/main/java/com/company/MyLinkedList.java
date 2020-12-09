package com.company;

public class MyLinkedList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;


    public MyLinkedList(
            T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public MyList<T> tail() {
        return this.tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
