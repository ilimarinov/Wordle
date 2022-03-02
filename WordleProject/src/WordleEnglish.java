
import java.util.*;

public class WordleEnglish extends WordleBody {

    public static final String START_MESSAGE = "Write a word. Must be %d characters.";
    public static final String DUPLICATE_WORD_MESSAGE = "The word has already been used! Write a new word.";
    public static final String CORRECT_WORD_MESSAGE = "The word is correct!";
    public static final String WORD_NOT_GUESSED_MESSAGE = "The word is: ";
    public static final String NOT_EXISTING_WORD_MESSAGE = "The word must be part of the wordle's list.";
    public static final String IN_POSITION_LETTERS_MESSAGE = "In position: ";
    public static final String NOT_IN_POSITION_LETTERS_MESSAGE = "Not in position letters: ";
    public static final String NOT_USED_LETTERS_MESSAGE = "Not Used letters so far: ";

    public static final String PATH = "src/wordleEnglish.txt";

    public WordleEnglish() {
        super.GameLoop();
    }

    public SortedSet<Character> AlphabetGenerator() {
        SortedSet<Character> Alphabet = new TreeSet<>();
        Collections.addAll(Alphabet, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        return Alphabet;
    }

    @Override
    protected String messageStart() {
        return START_MESSAGE;
    }

    @Override
    protected String messageDuplicateWord() {
        return DUPLICATE_WORD_MESSAGE;
    }

    @Override
    protected String messageCorrectWord() {
        return CORRECT_WORD_MESSAGE;
    }

    @Override
    protected String messageWordNotGuessed() {
        return WORD_NOT_GUESSED_MESSAGE;
    }

    @Override
    protected String messageNotExistingWord() {
        return NOT_EXISTING_WORD_MESSAGE;
    }

    @Override
    protected String messageInPositionLetters() {
        return IN_POSITION_LETTERS_MESSAGE;
    }

    @Override
    protected String messageNotInPositionLetters() {
        return NOT_IN_POSITION_LETTERS_MESSAGE;
    }

    @Override
    protected String messageNotUsedLetters() {
        return NOT_USED_LETTERS_MESSAGE;
    }

    @Override
    protected String pathToWordleList() {
        return PATH;
    }
}

