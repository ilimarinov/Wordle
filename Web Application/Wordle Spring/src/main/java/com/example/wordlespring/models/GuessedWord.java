package com.example.wordlespring.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GuessedWord {

    @NotNull(message = "Name can't be empty")
    @Size(min = 5, max = 5, message = "Name should be 5 characters.")
    private String text;

    public GuessedWord() {
    }

    public GuessedWord(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.toLowerCase();
    }

}


