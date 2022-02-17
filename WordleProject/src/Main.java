import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    private static final char IN_POSITION_GUESS = '*';
    private static final char NOT_IN_POSITION_GUESS = '+';
    private static final char NOT_EXISTING_LETTER = '-';

    private static final int WORDLE_CHARACTERS = 5;
    private static final int ATTEMPTS = 5;

    private static final String DUPLICATE_WORD_MESSAGE = "The word has already been used! Write a new word.";
    private static final String CORRECT_WORD_MESSAGE = "The word is correct";
    private static final String WORD_NOT_GUESSED_MESSAGE = "The word is: ";
    private static final String NOT_EXISTING_WORD_MESSAGE = "The word must be part of the wordle's list";

    private static final String PATH = "/Users/iliyanmarinov/Desktop/Wordle/wordlewords.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        BufferedReader reader = null;

        try {
            String currentLine;
            reader = new BufferedReader(new FileReader(PATH));
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
        String wordle = words.get(rand.nextInt(words.size()));

        Set<Character> charactersInWordle = new HashSet<>();
        SortedSet<Character> notInPositionLetters = new TreeSet<>();
        SortedSet<Character> inPositionLetters = new TreeSet<>();
        SortedSet<Character> notUsedLetters;

        List<String> duplicateWords = new ArrayList<>();

        Map<Character, Integer> guessWordLettersCount = new HashMap<>(); // to find occurrences of a letter in the guessWord
        Map<Character, Integer> wordleLettersCount; // to find occurrences of a letter in the wordle

        String guessWord = "";
        Character[] currentGuess = new Character[WORDLE_CHARACTERS];
        char currentLetterGuessed;

        notUsedLetters = AlphabetGenerator(); //using to generate the alphabet

        wordleLettersCount = LettersCounter(wordle);

        for (int g = 0; g < WORDLE_CHARACTERS; g++) { // filling set with wordle letters
            charactersInWordle.add(wordle.charAt(g));
        }

        for (int i = 0; i < ATTEMPTS; i++) {  // attempts and guesses
            do {
                do {
                    do {
                        if (guessWord.length() != WORDLE_CHARACTERS) {
                            System.out.printf("Write a %d characters word%n", WORDLE_CHARACTERS);
                        }
                        guessWord = scanner.nextLine();
                    } while (guessWord.length() != WORDLE_CHARACTERS);
                    if (!(words.contains(guessWord))) {
                        System.out.printf(NOT_EXISTING_WORD_MESSAGE + "%n");
                    }
                } while (!(words.contains(guessWord)));
                if ((duplicateWords.contains(guessWord))) {
                    System.out.printf(DUPLICATE_WORD_MESSAGE + "%n");
                }
            } while ((duplicateWords.contains(guessWord)));

            duplicateWords.add(guessWord);

            if (guessWord.equals(wordle)) { // the word is correct
                System.out.print(CORRECT_WORD_MESSAGE);

                return;
            }

            inPositionLetters.clear();
            notInPositionLetters.clear();
            guessWordLettersCount.clear();
            guessWordLettersCount = LettersCounter(guessWord);

            for (int j = 0; j < WORDLE_CHARACTERS; j++) {


                notUsedLetters.remove(guessWord.charAt(j)); // removing used letters from the alphabet
                currentLetterGuessed = guessWord.charAt(j);


                if (charactersInWordle.contains(currentLetterGuessed) && wordle.charAt(j) == currentLetterGuessed) {
                    currentGuess[j] = IN_POSITION_GUESS;
                    inPositionLetters.add(currentLetterGuessed);
                    guessWordLettersCount.put(currentLetterGuessed, guessWordLettersCount.get(currentLetterGuessed) - 2);
                    //deleting a count from the word
                } else if (charactersInWordle.contains(currentLetterGuessed)
                        && guessWordLettersCount.get(currentLetterGuessed).equals(wordleLettersCount.get(wordle.charAt(j)))) { // compare counts of the letter
                    currentGuess[j] = NOT_IN_POSITION_GUESS;
                    notInPositionLetters.add(currentLetterGuessed);
                } else {
                    currentGuess[j] = NOT_EXISTING_LETTER;
                }
                System.out.print("[" + currentGuess[j] + "]");
            }
            System.out.printf("%n");
            System.out.printf("In position letters: " + inPositionLetters + "%n");
            System.out.printf("Not in position letters: " + notInPositionLetters + "%n");
            System.out.printf("Not Used letters so far: " + notUsedLetters + "%n");

        }
        System.out.printf("%n" + WORD_NOT_GUESSED_MESSAGE + "%s", wordle);
    }

    private static Map<Character, Integer> LettersCounter(String word) {
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

    private static SortedSet<Character> AlphabetGenerator() {
        SortedSet<Character> Alphabet = new TreeSet<>();
        for (char a = 'a'; a <= 'z'; ++a) {
            Alphabet.add(a);
        }
        return Alphabet;
    }
}

