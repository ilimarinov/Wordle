package com.example.wordlespring.repositories;

import com.example.wordlespring.exceptions.EntityNotFoundException;
import com.example.wordlespring.models.GuessedWord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GuessWordRepositoryListImpl implements GuessWordRepository {

    private List<GuessedWord> guessedWords;
    private List<String> guessedWordsOutputs;

    private List<GuessedWord> guessedWordsBulgarian;
    private List<String> guessedWordsOutputsBulgarian;

    public GuessWordRepositoryListImpl() {
        guessedWords = new ArrayList<>();
        guessedWordsOutputs = new ArrayList<>();
        guessedWordsBulgarian = new ArrayList<>();
        guessedWordsOutputsBulgarian = new ArrayList<>();
    }

    @Override
    public void create(GuessedWord word) {
        guessedWords.add(word);
    }

    @Override
    public List<GuessedWord> getAll() {
        return guessedWords;
    }

    @Override
    public void createOutput(String word) {
        guessedWordsOutputs.add(word);
    }

    @Override
    public List<String> getAllOutputs() {
        return guessedWordsOutputs;
    }

    @Override
    public GuessedWord getByText(String text) {
        return guessedWords.stream()
                .filter(guessedWord -> guessedWord.getText().equals(text))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Guessedword", "text", text));
    }

    @Override
    public void resetWordle() {
        guessedWords.clear();
        guessedWordsOutputs.clear();
    }

    @Override
    public GuessedWord getByTextBulgarian(String text) {
        return guessedWordsBulgarian.stream()
                .filter(guessedWord -> guessedWord.getText().equals(text))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Guessedword", "text", text));
    }

    @Override
    public void createBulgarian(GuessedWord word) {
        guessedWordsBulgarian.add(word);
    }

    @Override
    public List<GuessedWord> getAllBulgarian() {
        return guessedWordsBulgarian;
    }

    @Override
    public void createOutputBulgarian(String word) {
        guessedWordsOutputsBulgarian.add(word);
    }

    @Override
    public List<String> getAllOutputsBulgarian() {
        return guessedWordsOutputsBulgarian;
    }

    @Override
    public void resetWordleBulgarian() {
        guessedWordsBulgarian.clear();
        guessedWordsOutputsBulgarian.clear();
    }
}
