import java.util.*;

public class WordleBulgarian extends WordleBody {

    public static final String START_MESSAGE = "Напишете дума. Трябва да бъде %d символа.";
    public static final String DUPLICATE_WORD_MESSAGE = "Думата вече е използвана. Напишете нова дума.";
    public static final String CORRECT_WORD_MESSAGE = "Думата е позната";
    public static final String WORD_NOT_GUESSED_MESSAGE = "Думата е: ";
    public static final String NOT_EXISTING_WORD_MESSAGE = "Думата трябва да бъде част от игралния списък!";
    public static final String IN_POSITION_LETTERS_MESSAGE = "Букви, които са на правилната позиция: ";
    public static final String NOT_IN_POSITION_LETTERS_MESSAGE = "Букви, които не са на правилната позиция: ";
    public static final String NOT_USED_LETTERS_MESSAGE = "Букви, които не са използвани: ";

    public static final String PATH = "/Users/iliyanmarinov/Wordle/wordleBulgarian.txt";

    public WordleBulgarian() {
        super.Solver();
    }

    public SortedSet<Character> AlphabetGenerator() {
        SortedSet<Character> Alphabet = new TreeSet<>();
        for (char a = 'а'; a <= 'я'; ++a) {
            Alphabet.add(a);
        }
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

