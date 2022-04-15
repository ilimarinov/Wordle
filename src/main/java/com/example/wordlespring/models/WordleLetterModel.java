package com.example.wordlespring.models;

public class WordleLetterModel implements Comparable {
    public char letter;
    public boolean isUsed;

    public WordleLetterModel(char letter, boolean isUsed) {
        this.letter = letter;
        this.isUsed = isUsed;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public int compareTo(Object o) {
        WordleLetterModel l = (WordleLetterModel) o;

        return letter - l.letter;
    }
}
