package com.company;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static com.company.LazyList.from;

public class PrimeMain {
    public static void main(String[] args) {
//        IntStream numbers = IntStream.iterate(2, n -> n + 1)
//                .limit(1000);
//        IntStream prime = primes(numbers);
//        prime.forEach(p -> System.out.print(p));
        //   LazyList<Integer> list = from(2,3);
        // [start, end)까지 출력한다.
       // printAll(lazyPrimes(from(2, 15)));
        System.out.println(BigDecimal.valueOf(2,1).toString());
        System.out.println(BigDecimal.ONE.toString());
        System.out.println(BigDecimal.ZERO.toString());

    }

    private static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    private static MyList<Integer> lazyPrimes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
                () -> lazyPrimes(numbers.tail().filter(n -> (n % numbers.head()) != 0)), numbers.isEmpty());
    }

    private static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    private static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    private static IntStream primes(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(IntStream.of(head),
                tail(numbers).filter(a -> a % head != 0));
    }
}
