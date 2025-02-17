package com.beyond.mvc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {
    // @Slf4j 어노테이션으로인해서아래의 선언문은 생략이 가능하다.
    // private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home() {

        log.info("home() 메소드 호출 - INFO");
        log.debug("home() 메소드 호출 - DEBUG");

        // 뷰의 이름 반환
        return "home";
    }
}
