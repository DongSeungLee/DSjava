package com.company;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
//    private static final String SENTENCE = "Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscrura ch la dritta via era smarrita ";
    private static final String SENTENCE = "hoho hoho ho ";

    public static void main(String[] args) throws Exception {
//        System.out.println("start benchmark...");
//        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        // stream.parallel()로 실행하면 올바르게 나오지 않는다. 왜냐하면 공백을 기준으로 자르는 것이 아니라 단어로 자를 수 있기 때문에 그러하다.
//        System.out.println(" Found :" + countWords(stream.parallel()));
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println(" Found :" + countWords(stream.parallel()));
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
