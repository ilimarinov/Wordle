package com.example.wordlespring.services;

import com.example.wordlespring.models.Wordle;
import com.example.wordlespring.repositories.WordleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WordleServiceImpl implements WordleService {
    private static final int NUMBER_OF_WORDLES_ENGLISH = 2307; //number of wordles in database -1
    private static final int NUMBER_OF_WORDLES_BULGARIAN = 2180; //2180 wordles +12972 -1

    private final WordleRepository repository;
    private static boolean start = true;
    private static boolean startBulgarian = true;
    private static int temp = 1;
    private static int tempBulgarian = 1;

    @Autowired
    public WordleServiceImpl(WordleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startGame() {
        start = true;
        getWordle();
    }

    @Override
    public Wordle getWordle() {

        int id = temp;
        if (start) {
            start = false;
            Random rand = new Random();
            id = rand.nextInt(NUMBER_OF_WORDLES_ENGLISH);
            temp = id;
            return repository.getById(id);
        }
        return repository.getById(id);
    }

    @Override
    public void startGameBulgarian() {
        startBulgarian = true;
        getWordleBulgarian();
    }

    @Override
    public Wordle getWordleBulgarian() {

        int id = tempBulgarian;
        if (startBulgarian) {
            startBulgarian = false;
            Random rand = new Random();
            id = rand.nextInt(NUMBER_OF_WORDLES_BULGARIAN);
            tempBulgarian = id;
            return repository.getById(id + 12971);
        }
        return repository.getById(id + 12971);
    }

}
