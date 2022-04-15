package com.example.wordlespring.services;

import com.example.wordlespring.exceptions.DuplicateEntityException;
import com.example.wordlespring.exceptions.EntityNotFoundException;
import com.example.wordlespring.models.GuessedWord;
import com.example.wordlespring.repositories.GuessWordRepository;
import com.example.wordlespring.repositories.PossibleWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessWordServiceImpl implements GuessWordService {
    private GuessWordRepository repository;
    private PossibleWordsRepository possibleWordsRepo;

    @Autowired
    public GuessWordServiceImpl(GuessWordRepository repository, PossibleWordsRepository possibleWordsRepo) {
        this.repository = repository;
        this.possibleWordsRepo = possibleWordsRepo;
    }

    @Override
    public List<GuessedWord> getAll() {
        return repository.getAll();
    }

    @Override
    public void nextGuessWord(GuessedWord word) {
        boolean duplicateExists = true;

        try {
            repository.getByText(word.getText());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Guessedword", "text", word.getText());
        }

        if (possibleWordsRepo.getByText(word.getText()) == null) {
            throw new EntityNotFoundException("text", 2);
        }

        repository.create(word);
    }

    public void nextGuessWordBulgarian(GuessedWord word) {
        boolean duplicateExists = true;

        try {
            repository.getByTextBulgarian(word.getText());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Guessedword", "text", word.getText());
        }

        if (possibleWordsRepo.getByTextBulgarian(word.getText()) == null) {
            throw new EntityNotFoundException("text", 2);
        }

        repository.createBulgarian(word);
    }

    @Override
    public void addOutput(String word) {
        repository.createOutput(word);
    }

    @Override
    public List<String> getAllOutputs() {
        return repository.getAllOutputs();
    }

    @Override
    public void resetWordle() {
        repository.resetWordle();
    }

    @Override
    public void addOutputBulgarian(String word) {
        repository.createOutputBulgarian(word);
    }

    @Override
    public List<String> getAllOutputsBulgarian() {
        return repository.getAllOutputsBulgarian();
    }

    @Override
    public List<GuessedWord> getAllBulgarian() {
        return repository.getAllBulgarian();
    }

    @Override
    public void resetWordleBulgarian() {
        repository.resetWordleBulgarian();
    }
}
