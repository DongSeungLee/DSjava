//package com.company;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.BenchmarkMode;
//import org.openjdk.jmh.annotations.Fork;
//import org.openjdk.jmh.annotations.Level;
//import org.openjdk.jmh.annotations.Mode;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.TearDown;
//
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.ForkJoinTask;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.LongStream;
//
////TearDown을 사용하기 위해서 @State가 필요하다.
//@State(Scope.Thread)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
///// 4G까지 할당 못하고 512MB정도를 heap memory에 할당한다.
//@Fork(value = 2, jvmArgs = {"-Xms512M", "-Xmx512M"})
//public class ParallelStreamBenchmark {
//    private static final long N = 10_000_000L;
//
////    @Benchmark
////    public long forkJoinSum() {
////        long[] numbers = LongStream.rangeClosed(1, N).toArray();
////        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
////        return new ForkJoinPool().invoke(task);
////    }
//    //    @Benchmark
////    public long sequentialSum() {
////        return Stream.iterate(1L, i -> i + 1L).limit(N)
////                .reduce(0L, Long::sum);
////    }
////    @Benchmark
////    public long parallelRangedSum() {
////        return LongStream.rangeClosed(1, N)
////                .parallel()
////                .reduce(0L, Long::sum);
////    }
//
////    @Benchmark
////    public long iterativeSum() {
////        long ret = 0;
////        for (long i = 1L; i <= N; i++) {
////            ret += i;
////        }
////        return ret;
////    }
//
//    // benchmark가 끝날 때마다 garbage collector를 강제한다.
//    @TearDown(Level.Invocation)
//    public void tearDown() {
//        System.gc();
//    }
//}
