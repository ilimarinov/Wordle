package com.example.wordlespring.repositories;

import com.example.wordlespring.models.Wordle;

public interface WordleRepository {
    Wordle getById(int id);
}
