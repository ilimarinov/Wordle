import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;

public class Main {
    private static final int WORDLE_CHARACTERS = 5;
    private static final int ATTEMPTS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        BufferedReader reader = null;

        try {
            String currentLine;
            reader = new BufferedReader(new FileReader("/Users/iliyanmarinov/Desktop/Wordle/wordlewords.txt"));
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

        Set<String> charactersInWordle = new HashSet<>();
        SortedSet<String> notInPositionLetters = new TreeSet<>();
        SortedSet<String> inPositionLetters = new TreeSet<>();
        SortedSet<String> notUsedLetters = new TreeSet<>();

        Map<Character, Integer> guessWordLettersCount = new HashMap<>(); // to find occurrences of a letter in the guessWord
        Map<Character, Integer> wordleLettersCount = new HashMap<>(); // to find occurrences of a letter in the wordle

        boolean completed = true;
        String guessWord = "";
        Character[] currentGuess = new Character[WORDLE_CHARACTERS];

        for (char a = 'a'; a <= 'z'; ++a) { // filling the set with the alphabet letters
            notUsedLetters.add(valueOf(a));
        }


        for (char x : wordle.toCharArray()) { //char or string
            if (wordleLettersCount.containsKey(x)) {
                int count = wordleLettersCount.get(x);
                wordleLettersCount.put(x, count + 1);
            } else {
                wordleLettersCount.put(x, 1);
            }
        }


        for (int g = 0; g < WORDLE_CHARACTERS; g++) { // filling set with wordle letters
            charactersInWordle.add(valueOf(wordle.charAt(g)));
        }

        for (int i = 0; i < ATTEMPTS; i++) {  // attempts and guesses
            do {
                do {
                    if (guessWord.length() != 5) {
                        System.out.printf("Write a %d characters word%n", WORDLE_CHARACTERS);
                    }
                    guessWord = scanner.nextLine();
                } while (guessWord.length() != 5);
                if (!(words.contains(guessWord))) {
                    System.out.println("Word must be in the wordle words");
                }
            } while (!(words.contains(guessWord)));

            guessWordLettersCount.clear();
            for (char x : guessWord.toCharArray()) {
                if (guessWordLettersCount.containsKey(x)) {
                    int count = guessWordLettersCount.get(x);
                    guessWordLettersCount.put(x, count + 1);
                } else {
                    guessWordLettersCount.put(x, 1);
                }
            }

            for (int j = 0; j < WORDLE_CHARACTERS; j++) {
                if (guessWord.equals(wordle)) { // the word is correct
                    System.out.print("The word is correct!");
                    completed = false;

                    return;
                }

                notUsedLetters.remove(valueOf(guessWord.charAt(j))); // removing used letters from the alphabet


                if (charactersInWordle.contains(valueOf(guessWord.charAt(j))) && wordle.charAt(j) == guessWord.charAt(j)) {
                    currentGuess[j] = '*';
                    inPositionLetters.add(valueOf(guessWord.charAt(j)));
                    guessWordLettersCount.put(guessWord.charAt(j), guessWordLettersCount.get(guessWord.charAt(j)) - 2);
                    //deleting a count from the word
                } else if (charactersInWordle.contains(valueOf(guessWord.charAt(j)))
                        && guessWordLettersCount.get(guessWord.charAt(j)).equals(wordleLettersCount.get(wordle.charAt(j)))) { // compare counts of the letter
                    currentGuess[j] = '+';
                    notInPositionLetters.add(valueOf(guessWord.charAt(j)));
                } else {
                    currentGuess[j] = '-';
                }

                System.out.print("[" + currentGuess[j] + "]");
            }
            System.out.println();
            System.out.println("In position letters: " + inPositionLetters);
            System.out.println("Not in position letters: " + notInPositionLetters);
            System.out.println("Not Used letters so far: " + notUsedLetters);
            inPositionLetters.clear();
            notInPositionLetters.clear();

        }
        if (completed) {
            System.out.printf("%nThe word is : %s", wordle);
        }

    }

}

