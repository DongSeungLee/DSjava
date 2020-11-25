package com.company;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final int MIN_NUMBERS_COUNT_FOR_SEQUENTIAL_COMPUTATION = 100000;
    private final long[] items;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] items) {
        this(items, 0, items.length);
    }

    private ForkJoinSumCalculator(long[] items, int start, int end) {
        this.items = items;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= MIN_NUMBERS_COUNT_FOR_SEQUENTIAL_COMPUTATION) {
            return sequentialSum();
        } else {
            ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(items, start, start + length / 2);
            // 비동기로 leftFork실행
            leftFork.fork();

            ForkJoinSumCalculator rightJoin = new ForkJoinSumCalculator(items, start + length / 2, end);
            // 동기로 rightJoin 실행
            Long rightSum = rightJoin.compute();
            Long leftSum = leftFork.join();

            return leftSum + rightSum;
        }
    }

    private Long sequentialSum() {
        long ret = 0;
        for (int i = start; i < end; i++) {
            ret += items[i];
        }
        return ret;
    }
}
