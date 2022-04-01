import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class WordleBody {

    private static final char IN_POSITION_GUESS = '*';
    private static final char NOT_IN_POSITION_GUESS = '+';
    private static final char NOT_EXISTING_LETTER = '-';

    private static final int WORDLE_CHARACTERS = 5;
    private static final int ATTEMPTS = 5;

    private static List<String> words = new ArrayList<>();

    private static String wordle;
    private static String guessWord = "";
    private static Character[] currentGuess = new Character[WORDLE_CHARACTERS];
    private static char currentLetterGuessed;

    private static Set<Character> charactersInWordle = new HashSet<>();
    private static SortedSet<Character> notInPositionLetters = new TreeSet<>();
    private static SortedSet<Character> inPositionLetters = new TreeSet<>();
    private static SortedSet<Character> notUsedLetters = new TreeSet<>();

    private static List<String> duplicateWords = new ArrayList<>();

    private static Map<Character, Integer> guessWordLettersCount = new HashMap<>();
    private static Map<Character, Integer> wordleLettersCount = new HashMap<>();

    protected void GameLoop() {
        pickNextWord();

        notUsedLetters = AlphabetGenerator();

        for (int g = 0; g < WORDLE_CHARACTERS; g++) {
            charactersInWordle.add(wordle.charAt(g));
        }
        for (int i = 0; i < ATTEMPTS; i++) {

            readGuessedWord();

            duplicateWords.add(guessWord);

            if (guessWord.equals(wordle)) {
                System.out.print(messageCorrectWord());
                return;
            }

            inPositionLetters.clear();
            notInPositionLetters.clear();

            wordleLettersCount.clear();
            wordleLettersCount = LettersCounter(wordle);

            guessWordLettersCount.clear();
            guessWordLettersCount = LettersCounter(guessWord);

            for (int j = 0; j < WORDLE_CHARACTERS; j++) {
                currentLetterGuessed = guessWord.charAt(j);
                notUsedLetters.remove(currentLetterGuessed);

                if (charactersInWordle.contains(currentLetterGuessed) && wordle.charAt(j) == currentLetterGuessed) {
                    currentGuess[j] = IN_POSITION_GUESS;
                    inPositionLetters.add(currentLetterGuessed);
                    guessWordLettersCount.put(currentLetterGuessed, guessWordLettersCount.get(currentLetterGuessed) - 1);
                } else if (charactersInWordle.contains(currentLetterGuessed)
                        &&
                        guessWordLettersCount.get(currentLetterGuessed) <= (wordleLettersCount.get(currentLetterGuessed))) {
                    currentGuess[j] = NOT_IN_POSITION_GUESS;
                    guessWordLettersCount.put(currentLetterGuessed, guessWordLettersCount.get(currentLetterGuessed) - 1);
                    notInPositionLetters.add(currentLetterGuessed);
                } else {
                    currentGuess[j] = NOT_EXISTING_LETTER;
                }

                System.out.print("[" + currentGuess[j] + "]");

                if (charactersInWordle.contains(currentLetterGuessed)) {
                    wordleLettersCount.put(currentLetterGuessed, wordleLettersCount.get(currentLetterGuessed) + 1);
                }
            }
            System.out.printf("%n");
            System.out.printf(messageInPositionLetters() + inPositionLetters + "%n");
            System.out.printf(messageNotInPositionLetters() + notInPositionLetters + "%n");
            System.out.printf(messageNotUsedLetters() + notUsedLetters + "%n");
        }
        System.out.printf("%n" + messageWordNotGuessed() + "%s%n", wordle);
    }

    protected String getWord() {
        return wordle;
    }

    private void pickNextWord() {
        BufferedReader reader = null;

        try {
            String currentLine;
            reader = new BufferedReader(new FileReader(pathToWordleList()));
            while ((currentLine = reader.readLine()) != null) {
                words.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }

        Random rand = new Random(System.currentTimeMillis());
        wordle = words.get(rand.nextInt(words.size()));
    }

    private String getGuessedWord() {
        return guessWord;
    }

    private void readGuessedWord() {
        Scanner scanner = new Scanner(System.in);
        do {
            do {
                do {
                    if (guessWord.length() != WORDLE_CHARACTERS) {
                        System.out.printf(messageStart() + "%n", WORDLE_CHARACTERS);
                    }
                    guessWord = scanner.nextLine();
                } while (guessWord.length() != WORDLE_CHARACTERS);
                if (!(words.contains(guessWord))) {
                    System.out.printf(messageNotExistingWord() + "%n");
                }
            } while (!(words.contains(guessWord)));
            if ((duplicateWords.contains(guessWord))) {
                System.out.printf(messageDuplicateWord() + "%n");
            }
        } while ((duplicateWords.contains(guessWord)));
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

    protected abstract SortedSet<Character> AlphabetGenerator();

    protected abstract String messageStart();

    protected abstract String messageDuplicateWord();

    protected abstract String messageCorrectWord();

    protected abstract String messageWordNotGuessed();

    protected abstract String messageNotExistingWord();

    protected abstract String messageInPositionLetters();

    protected abstract String messageNotInPositionLetters();

    protected abstract String messageNotUsedLetters();

    protected abstract String pathToWordleList();
}
