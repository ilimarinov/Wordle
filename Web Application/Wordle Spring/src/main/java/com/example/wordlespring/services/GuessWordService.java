package com.example.wordlespring.services;

import com.example.wordlespring.models.GuessedWord;

import java.util.List;

public interface GuessWordService {

    void nextGuessWord(GuessedWord word);

    List<GuessedWord> getAll();

    void addOutput(String word);

    List<String> getAllOutputs();

    void resetWordle();

    void nextGuessWordBulgarian(GuessedWord word);

    List<GuessedWord> getAllBulgarian();

    void addOutputBulgarian(String word);

    List<String> getAllOutputsBulgarian();

    void resetWordleBulgarian();
}
