package com.example.qna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "CI/CD 테스트입니다!";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
