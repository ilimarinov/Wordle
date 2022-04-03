package com.example.wordlespring.services;

import com.example.wordlespring.models.PossibleWords;

import java.util.List;

public interface PossibleWordsService {
    List<PossibleWords> getAll();

    PossibleWords getById(int id);

    PossibleWords getByText(String text);

    PossibleWords getByTextBulgarian(String text);
}
