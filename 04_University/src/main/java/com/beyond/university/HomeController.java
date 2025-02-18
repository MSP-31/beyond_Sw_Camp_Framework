package com.beyond.university;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        List<String> names = Arrays.asList("홍길동","이몽룡","성춘향","김영희","김철수");

        model.addAttribute("message","Hello World");
        model.addAttribute("names",names);

        return "home";
    }
}
