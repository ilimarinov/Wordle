package com.example.wordlespring.controllers.rest;

import com.example.wordlespring.exceptions.DuplicateEntityException;
import com.example.wordlespring.models.GuessedWord;
import com.example.wordlespring.services.GuessWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class GuessedWordController {

    private GuessWordService service;

    @Autowired
    public GuessedWordController(GuessWordService service) {
        this.service = service;
    }

    @GetMapping
    public List<GuessedWord> getAll(){
        return service.getAll();
    }


    @PostMapping
    public GuessedWord create(@Valid @RequestBody GuessedWord word){
        try {
            service.nextGuessWord(word);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return word;
    }
}
