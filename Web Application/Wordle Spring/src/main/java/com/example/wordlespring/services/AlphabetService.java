package com.example.wordlespring.services;

import com.example.wordlespring.repositories.AlphabetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Service
public class AlphabetService {
    private AlphabetRepository alphabetRepository;

    @Autowired
    public AlphabetService(AlphabetRepository alphabetRepository) {
        this.alphabetRepository = alphabetRepository;
    }

    public SortedSet<Character> getEnglishAlphabet() {
        return alphabetRepository.AlphabetGenerator();
    }

    public SortedSet<Character> getBulgarianAlphabet() {
        return alphabetRepository.BulgarianAlphabetGenerator();
    }
}
