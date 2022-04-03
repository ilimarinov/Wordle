package com.example.wordlespring.services;

import com.example.wordlespring.models.PossibleWords;
import com.example.wordlespring.repositories.PossibleWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PossibleWordsServiceImpl implements PossibleWordsService {

    private final PossibleWordsRepository repository;

    @Autowired
    public PossibleWordsServiceImpl(PossibleWordsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PossibleWords> getAll() {
        return repository.getAll();
    }

    @Override
    public PossibleWords getById(int id) {
        return repository.getById(id);
    }

    @Override
    public PossibleWords getByText(String text) {
        return repository.getByText(text);
    }

    @Override
    public PossibleWords getByTextBulgarian(String text) {
        return repository.getByTextBulgarian(text);
    }
}
