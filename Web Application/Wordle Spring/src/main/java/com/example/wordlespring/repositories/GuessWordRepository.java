package com.example.wordlespring.repositories;

import com.example.wordlespring.models.GuessedWord;

import java.util.List;

public interface GuessWordRepository {
    List<GuessedWord> getAll();

    List<GuessedWord> getAllBulgarian();

    GuessedWord getByText(String text);

    GuessedWord getByTextBulgarian(String text);

    void create(GuessedWord word);

    void createBulgarian(GuessedWord word);

    void createOutput(String word);

    void createOutputBulgarian(String word);

    List<String> getAllOutputs();

    List<String> getAllOutputsBulgarian();

    void resetWordle();

    void resetWordleBulgarian();

}
