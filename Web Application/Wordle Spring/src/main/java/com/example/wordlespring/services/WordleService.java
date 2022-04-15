package com.example.wordlespring.services;

import com.example.wordlespring.models.Wordle;

public interface WordleService {
    Wordle getWordle();

    Wordle getWordleBulgarian();

    void startGame();

    void startGameBulgarian();


}
