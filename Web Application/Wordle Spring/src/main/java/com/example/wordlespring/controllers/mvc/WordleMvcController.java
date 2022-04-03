package com.example.wordlespring.controllers.mvc;

import com.example.wordlespring.exceptions.DuplicateEntityException;
import com.example.wordlespring.exceptions.EntityNotFoundException;
import com.example.wordlespring.models.GuessedWord;
import com.example.wordlespring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class WordleMvcController {
    private final GuessWordService guessWordService;
    private final WordleBodyServiceImpl wordleBody;
    private final PossibleWordsService possibleWordsService;
    private final WordleService wordleService;
    private final AlphabetService alphabetService;

    @Autowired
    public WordleMvcController(GuessWordService guessWordService,
                               WordleBodyServiceImpl wordleBody,
                               PossibleWordsService possibleWordsService,
                               WordleService wordleService,
                               AlphabetService alphabetService) {
        this.guessWordService = guessWordService;
        this.wordleBody = wordleBody;
        this.possibleWordsService = possibleWordsService;
        this.wordleService = wordleService;
        this.alphabetService = alphabetService;
    }

    @GetMapping
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/english")
    public String showInputPage(Model model) {
        getAllLists(model);
        model.addAttribute("word", new GuessedWord());
        return "wordleEnglish";
    }

    @PostMapping("/english")
    public String createGuessedWord(@Valid @ModelAttribute("word") GuessedWord word, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            getAllLists(model);
            return "wordleEnglish";
        }
        try {
            if (word.getText().length() == 5 && possibleWordsService.getByText(word.getText()) != null) {
                guessWordService.nextGuessWord(wordleBody.GameLoop(word, wordleService.getWordle().getText()).get(0));
                guessWordService.addOutput((wordleBody.GameLoop(word, wordleService.getWordle().getText()).get(1)).getText());
            }
            return "redirect:/english";

        } catch (DuplicateEntityException e) {
            errors.rejectValue("text", "words.exists", "This word has already been used!");
            getAllLists(model);
            return "wordleEnglish";

        } catch (EntityNotFoundException e) {
            errors.rejectValue("text", "words.not.in.list", "The word is not in the list!");
            getAllLists(model);
            return "wordleEnglish";
        }
    }

    @GetMapping("/getwordle")
    public String showWordle(Model model) {
        model.addAttribute("wordle", wordleService.getWordle());
        return "whatIsWordleEnglish";
    }

    @GetMapping("resetWordle")
    public String resetWordleEnglishControl() {
        wordleBody.ResetWordleEnglish();
        guessWordService.resetWordle();
        wordleService.startGame();
        return "redirect:/english";
    }

    public void getAllLists(Model model) {
        model.addAttribute("allWords", guessWordService.getAll());
        model.addAttribute("allOutputs", guessWordService.getAllOutputs()); //reset all three and start game
        model.addAttribute("wordleLetters", wordleBody.getAllLetters(alphabetService.getEnglishAlphabet(), WordleBodyServiceImpl.getUsedEnglishLetters()));

    }

    @GetMapping("/bulgarian")
    public String showInputPageBulgarian(Model model) {
        getAllListsBulgarian(model);
        model.addAttribute("wordBulgarian", new GuessedWord());
        return "wordleBulgarian";
    }

    @PostMapping("/bulgarian")
    public String createGuessedWordBulgarian(@Valid @ModelAttribute("wordBulgarian") GuessedWord word, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            getAllListsBulgarian(model);
            return "wordleBulgarian";
        }
        try {
            if (word.getText().length() == 5 && possibleWordsService.getByTextBulgarian(word.getText()) != null) {
                guessWordService.nextGuessWordBulgarian(wordleBody.GameLoop(word, wordleService.getWordleBulgarian().getText()).get(0));
                guessWordService.addOutputBulgarian((wordleBody.GameLoop(word, wordleService.getWordleBulgarian().getText()).get(1)).getText());
            }
            return "redirect:/bulgarian";

        } catch (DuplicateEntityException e) {
            errors.rejectValue("text", "words.exists", "Думата вече е използвана!");
            getAllListsBulgarian(model);
            return "wordleBulgarian";

        } catch (EntityNotFoundException e) {
            errors.rejectValue("text", "words.not.in.list", "Думата не е в листа с думи!");
            getAllListsBulgarian(model);

            return "wordleBulgarian";
        }
    }

    @GetMapping("/getwordlebg")
    public String showWordleBulgarian(Model model) {
        model.addAttribute("wordleBulgarian", wordleService.getWordleBulgarian());
        return "whatIsWordleBulgarian";
    }

    @GetMapping("/resetBulgarianWordle")
    public String getResetWordleBulgarianControl() {
        wordleBody.ResetWordleBulgarian();
        guessWordService.resetWordleBulgarian();
        wordleService.startGameBulgarian();
        return "redirect:/bulgarian";
    }

    public void getAllListsBulgarian(Model model) {
        model.addAttribute("allWordsBulgarian", guessWordService.getAllBulgarian());
        model.addAttribute("allOutputsBulgarian", guessWordService.getAllOutputsBulgarian()); //reset all three and start game
        model.addAttribute("wordleLettersBulgarian", wordleBody.getAllLetters(alphabetService.getBulgarianAlphabet(), WordleBodyServiceImpl.getUsedBulgarianLetters()));
    }
}
