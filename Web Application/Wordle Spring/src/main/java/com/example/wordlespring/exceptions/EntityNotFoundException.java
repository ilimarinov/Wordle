package com.example.wordlespring.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String type, int number) {
    this(type, "number", String.valueOf(number));
    }

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s %s not found.", type, attribute, value));
    }

}
