package com.example.wordlespring.repositories;

import com.example.wordlespring.models.PossibleWords;

import java.util.List;

public interface PossibleWordsRepository {
    List<PossibleWords> getAll();

    PossibleWords getById(int id);

    PossibleWords getByText(String text);

    PossibleWords getByTextBulgarian(String text);
}
