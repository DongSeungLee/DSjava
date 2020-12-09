package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    //    private static final String SENTENCE = "Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscrura ch la dritta via era smarrita ";
    private static final String SENTENCE = "hoho hoho ho ";
    private static List<List<Integer>> ans;

    //public static void main(String[] args) throws Exception {
//        System.out.println("start benchmark...");
//        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        // stream.parallel()로 실행하면 올바르게 나오지 않는다. 왜냐하면 공백을 기준으로 자르는 것이 아니라 단어로 자를 수 있기 때문에 그러하다.
//        System.out.println(" Found :" + countWords(stream.parallel()));
//        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
//        Stream<Character> stream = StreamSupport.stream(spliterator, true);
//        System.out.println(" Found :" + countWords(stream.parallel()));

        ////////////////////////////////////////
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
//        int x = 1377;
//        CompletableFuture<Integer> a = new CompletableFuture<>();
//        CompletableFuture<Integer> b = new CompletableFuture<>();
//        CompletableFuture<Integer> c = a.thenCombine(b, (y, z) -> {
//            System.out.println("Sum Current Thread : " + Thread.currentThread().getName());
//            return y + z;
//        });
//        executorService.submit(() -> a.complete(f(x)));
//        executorService.submit(() -> b.complete(g(x)));
//        System.out.println(c.get());
//        CompletableFuture<Integer> d = b.thenCombine(c, (y, z) -> {
//            System.out.println("Sum Current Thread : " + Thread.currentThread().getName());
//            return y + z;
//        });
//        System.out.println("d: " + d.get());
//        executorService.shutdown();
        ////////////////////////////////////
//        Shop shop = new Shop("Bestshop");
//        long start = System.currentTimeMillis();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite productd");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation time is : " + invocationTime + Thread.currentThread().getName());
//        // 제품이 가격을 계산하는 동안
//        doSomeThingElse();
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f thread name : %s\n", price, Thread.currentThread().getName());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("retrieval time : " + retrievalTime + Thread.currentThread().getName());
//        List<Integer> arg = Arrays.asList(1, 2, 3);
//        List<List<Integer>> ret = subsets(arg);
//        for (List<Integer> list : ret) {
//            list.forEach(a -> System.out.print(a + " "));
//            System.out.println();
//        }
//    }

    private static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subset1 = subsets(rest);
        List<List<Integer>> subset2 = insertAll(first, subset1);
        return concat(subset1, subset2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> sub1, List<List<Integer>> sub2) {
        List<List<Integer>> ret = new ArrayList<>(sub1);
        ret.addAll(sub2);
        return ret;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> rest) {
        List<List<Integer>> ret = new ArrayList<>();
        for (List<Integer> list : rest) {
            List<Integer> elem = new ArrayList<>(list);
            elem.add(0, first);
            ret.add(elem);
        }
        return ret;
    }

    private static void doSomeThingElse() throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("do something else!");
    }

    public static int f(int x) {
        System.out.println("F current Threrad : " + Thread.currentThread().getName());
        return x + 1;
    }

    public static int g(int x) {
        System.out.println("G current Threrad : " + Thread.currentThread().getName());
        return x + 2;
    }

    public static void work1() {
        System.out.println("Work1");
    }

    public static void work2() {
        System.out.println("Work2");
    }

    private static int countWords(Stream<Character> stream) {
        // 분할 한 뒤 supplier ,accumulater가 작동한다. 분할이 끝나고 combine할 때는 WordCounter::combine이 사용된다.
        // combine은 두개의 WodrCounter의 counter를 합치고 뒤의 것의 lastSpace로 값을 할당한다. 순차적으로 뒤에 오는 것이 공백이었다면
        // 만들어진 wordCounter라 accumulate를 할 때 공백이 아닌 단어를 만나면 +1을 해줄 수 있다.
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    private void requestFavorite() {
        //        URL url = new URL("https://dv30lu3a9suxj.cloudfront.net/fgweek202008/looks-we-love.json");
//        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//        httpConnection.setDoOutput(true);
//
//        httpConnection.setRequestMethod("GET");
//        httpConnection.setRequestProperty("Content-Type", "application/json");
//        httpConnection.setRequestProperty("Accept", "application/json");
//        if(httpConnection.getResponseCode() >299){
//            throw new Exception("error");
//        }
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
//        StringBuilder content = new StringBuilder();
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            content.append(line).append("\n");
//        }
//        bufferedReader.close();
//        ObjectMapper mapper = new ObjectMapper();
//        List<fgweekData> list = mapper.readValue(content.toString(), mapper.getTypeFactory().constructCollectionType(List.class, fgweekData.class));
//        System.out.println(list);
    }
}
