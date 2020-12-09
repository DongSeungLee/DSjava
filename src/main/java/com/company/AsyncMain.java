package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AsyncMain {
    private static List<Shop> shops = Arrays.asList(new Shop("shop1"), new Shop("shop2"),
            new Shop("shop3"), new Shop("shop4"), new Shop("shop5"));
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            r -> {
                Thread t = new Thread(r);
                // setDaemon = true는 프로그램이 종료되면 Thread도 당연히 종료되게끔
                t.setDaemon(true);
                return t;
            });

    public static void main(String args[]) {
        long start = System.nanoTime();
        List<String> ret1;
        //  ret1 = findPrices("shop1");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        // System.out.println("Invocation time is : " + invocationTime + "msecs  " + Thread.currentThread().getName());
        // System.out.println(ret1);

        /////////////////////////////////////
        start = System.nanoTime();
        findPricesAsync("shop1");
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time is : " + invocationTime + "msecs  " + Thread.currentThread().getName());
    }

    public static List<String> findPrices(String product) {
        return shops.stream().map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static void findPricesAsync(String product) {
        CompletableFuture[] priceFutures =
                shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor).orTimeout(2, TimeUnit.SECONDS))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(
                                //2로 설정하면 timeout이 떨어짐.
                                quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)).completeOnTimeout("timeout", 3, TimeUnit.SECONDS))
                        .map(f -> f.thenAccept(System.out::print))
                        .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(priceFutures).join();
//        return priceFutures.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
    }
}
