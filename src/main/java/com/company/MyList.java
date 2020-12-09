package com.company;

import java.util.function.Predicate;

public interface MyList<T> {
    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default MyList<T> filter(Predicate<T> p) {
        System.out.println("filter is called");
        if (isEmpty()) return this;
        return p.test(head()) ? new LazyList<T>(head(), () -> tail().filter(p), false) :
                tail().filter(p);
    }
}
