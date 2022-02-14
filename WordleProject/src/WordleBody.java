public class WordleBody {

    private static final int MAX_GUESSES = 5;
    private static final int WORD_LENGTH = 5;
    private static int currentGuess = 1;

    private String word;
    private String guessedWord;


    public WordleBody(String word, String guessesWord) {
        setGuessedWord(guessesWord);
        setWord(word);

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        if (word.length() != WORD_LENGTH) {
            throw new IllegalArgumentException(String.format("The word's length must be %d characters long", WORD_LENGTH));
        }
        this.word = word;
    }

    public String getGuessedWord() {
        return guessedWord;
    }

    public void setGuessedWord(String guessedWord) {
        if (guessedWord.length() != WORD_LENGTH) {
            throw new IllegalArgumentException(String.format("The word's length must be %d characters long", WORD_LENGTH));
        }
        this.guessedWord = guessedWord;
    }
}

