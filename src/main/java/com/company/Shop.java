package com.company;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Shop {
    private String product;

    public Shop() {

    }

    public Shop(String product) {
        this.product = product;
    }

    private static Random random = new Random();

    public String getPrice(String product) {

        double price = calculatePrice(product);
        System.out.println("name : " + this.product + " " + Thread.currentThread().getName());
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", this.product, price, code);
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble() * this.product.charAt(0) + this.product.charAt(1);
    }

    public CompletableFuture<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            // 계산이 오래 걸리는 price를 한번 실행하고
            double price = calculatePrice(product);
            // 오랜 시간이 걸리는 작업이 완료되면 price를 Future에 값을 설정한다.
            futurePrice.complete(price);
        }).start();
        // 일단 Future<>()를 return한다. ie 계산 결과를 기다리지 않는다.
        return futurePrice;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
