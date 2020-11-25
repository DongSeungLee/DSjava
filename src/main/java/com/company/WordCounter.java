package com.company;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            // String에 무조건 하나의 단어는 있다고 가정한다. 공백인 String은 무시한다.
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(this.counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return this.counter;
    }
}
