package com.example.wordlespring.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asdasd")
public class HomeMvcController {

    @Autowired
    public HomeMvcController() {
    }

    @GetMapping
    public String showHomePage() {
        return "index";
    }


}
