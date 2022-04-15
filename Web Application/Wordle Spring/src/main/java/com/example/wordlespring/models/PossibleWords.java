package com.example.wordlespring.models;

import javax.persistence.*;

@Entity
@Table(name = "wordle_words", schema = "public")
public class PossibleWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "words")
    private String text;

    @Column(name = "can_be_wordle")
    private boolean canBeWordle;

    @Column(name = "language")
    private String language;

    public boolean isCanBeWordle() {
        return canBeWordle;
    }

    public void setCanBeWordle(boolean canBeWordle) {
        this.canBeWordle = canBeWordle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public PossibleWords(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public PossibleWords() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
