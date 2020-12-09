package com.company;

import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    private final T head;
    private final Supplier<MyList<T>> tail;
    private boolean empty;

    public static LazyList from(int n, int last) {
        LazyList ret = new LazyList(n, () -> from(n + 1, last), n == last);
        return ret;
    }

    public LazyList(T head, Supplier<MyList<T>> tail, boolean empty) {
        this.head = head;
        this.tail = tail;
        this.empty = empty;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return this.empty;
    }
}
