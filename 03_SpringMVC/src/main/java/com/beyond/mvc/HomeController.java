package com.beyond.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home() {

        log.info("home() 메소드 호출 - INFO");
        log.debug("home() 메소드 호출 - DEBUG");

        // 뷰의 이름 반환
        return "home";
    }
}
