package com.example.wordlespring.services;

import com.example.wordlespring.models.GuessedWord;
import com.example.wordlespring.models.WordleLetterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordleBodyServiceImpl {


    private static final char IN_POSITION_GUESS = '*';
    private static final char NOT_IN_POSITION_GUESS = '+';
    private static final char NOT_EXISTING_LETTER = '-';

    private static final int WORDLE_CHARACTERS = 5;

    private static Character[] currentGuess = new Character[WORDLE_CHARACTERS];
    private static char currentLetterGuessed;

    private static Set<Character> charactersInWordle = new HashSet<>();

    private static SortedSet<Character> UsedEnglishLetters = new TreeSet<>();
    private static SortedSet<Character> UsedBulgarianLetters = new TreeSet<>();

    private static Map<Character, Integer> guessWordLettersCount = new HashMap<>();
    private static Map<Character, Integer> wordleLettersCount = new HashMap<>();

    @Autowired
    protected WordleBodyServiceImpl() {
    }

    public SortedSet<WordleLetterModel> getAllLetters(SortedSet<Character> alphabet, SortedSet<Character> usedLetters) { //reset
        SortedSet<WordleLetterModel> wordleLetters = new TreeSet<>();
        for (char letter : alphabet) {
            wordleLetters.add(new WordleLetterModel(letter, usedLetters.contains(letter)));
        }
        return wordleLetters;
    }

    public ArrayList<GuessedWord> GameLoop(GuessedWord guessWordObject, String wordleRaw) {
        String wordle = wordleRaw;
        String guessWord = guessWordObject.getText();

        StringBuilder wordToShow = new StringBuilder();

        for (int g = 0; g < WORDLE_CHARACTERS; g++) {
            charactersInWordle.add(wordle.charAt(g));
        }

        wordleLettersCount.clear();
        wordleLettersCount = LettersCounter(wordle);

        guessWordLettersCount.clear();
        guessWordLettersCount = LettersCounter(guessWord);


        for (int j = 0; j < WORDLE_CHARACTERS; j++) {
            currentLetterGuessed = guessWord.charAt(j);

            if (currentLetterGuessed >= 'a' && currentLetterGuessed <= 'z') {
                UsedEnglishLetters.add(currentLetterGuessed);
            }

            if (currentLetterGuessed >= 'а' && currentLetterGuessed <= 'я') {
                UsedBulgarianLetters.add(currentLetterGuessed);
            }

            if (charactersInWordle.contains(currentLetterGuessed) && wordle.charAt(j) == currentLetterGuessed) {
                currentGuess[j] = IN_POSITION_GUESS;
                guessWordLettersCount.put(currentLetterGuessed, guessWordLettersCount.get(currentLetterGuessed) - 1);
            } else if (charactersInWordle.contains(currentLetterGuessed) // work with a map to follow if the getLetter is more than 0
                    &&
                    guessWordLettersCount.get(currentLetterGuessed) <= (wordleLettersCount.get(currentLetterGuessed))) {
                currentGuess[j] = NOT_IN_POSITION_GUESS;
                guessWordLettersCount.put(currentLetterGuessed, guessWordLettersCount.get(currentLetterGuessed) - 1);
            } else {
                currentGuess[j] = NOT_EXISTING_LETTER;
            }
            wordToShow.append(currentGuess[j]);

            if (charactersInWordle.contains(currentLetterGuessed)) {
                wordleLettersCount.put(currentLetterGuessed, wordleLettersCount.get(currentLetterGuessed) + 1);
            }

        }

        String str = wordToShow.toString();

        GuessedWord wordToReturn = new GuessedWord(str);
        ArrayList<GuessedWord> guessWordOutput = new ArrayList<>();

        guessWordOutput.add(guessWordObject);
        guessWordOutput.add(wordToReturn);

        return guessWordOutput;
    }

    private Map<Character, Integer> LettersCounter(String word) {
        Map<Character, Integer> lettersCounter = new HashMap<>();
        for (char x : word.toCharArray()) {
            if (lettersCounter.containsKey(x)) {
                int count = lettersCounter.get(x);
                lettersCounter.put(x, count + 1);
            } else {
                lettersCounter.put(x, 1);
            }
        }
        return lettersCounter;
    }

    public void ResetWordleEnglish() {
        UsedEnglishLetters.clear();
        charactersInWordle.clear();
    }

    public void ResetWordleBulgarian() {
        UsedBulgarianLetters.clear();
        charactersInWordle.clear();
    }

    public static SortedSet<Character> getUsedBulgarianLetters() {
        return UsedBulgarianLetters;
    }

    public static SortedSet<Character> getUsedEnglishLetters() {
        return UsedEnglishLetters;
    }
}
