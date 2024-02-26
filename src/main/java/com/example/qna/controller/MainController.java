package com.example.qna.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/sbb")
    public String index() {
        return "안녕하세요.";
    }
}
