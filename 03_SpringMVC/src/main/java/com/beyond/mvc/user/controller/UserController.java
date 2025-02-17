package com.beyond.mvc.user.controller;

import com.beyond.mvc.user.model.dto.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
// @RestController
public class UserController {
    /*
    // 컨트롤러가 처리할 요청을 정의한다. (URL, Method 등)
    // RequestMapping 에서 따로 요청을 정의하지 않으면 get/post 둘다 된다.
    // @RequestMapping("/login")
    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // @GetMapping("/login")
    @PostMapping("/login")
    public String login() {

        // return "home";
        return "redirect:/";
    }
     */

    // 요청 시 사용자의 파라미터를 전송받는 방법
    // 1. HttpServletRequest 객체를 통해서 전송받기 (Servlet/JSP 방식)
    /*
    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("username:{}, password:{}", username, password);
        log.info("session:{}", session.getId());

        // [INFO ] 2025-02-17 UserController login - username:admin, password:1234 -
        return "redirect:/";
    }
     */

    // 2.1 @RequestParam 어노테이션으로 전송하는 방법
    // - 스프링에서는 간편하게 파라미터를 받아올 수 있는 방법을 제공한다.
    // - 매개변수의 이름과 파라미터의 이름이 동일하면 @RequestPram 어노테이션을 생략할 수 있다.
    /*
    @PostMapping("/login")
    // public String login(@RequestParam("username") String username, @RequestParam("password") String password){
    // public String login(@RequestParam String username, @RequestParam String password){
    public String login (String username,String password){

        log.info("username : {}, password : {}", username, password);

        return "redirect:/";
    }
     */

    // 2.2 @RequestParam 어노테이션의 required 속성과 defaultValue 속성
    //  - 존재하지 않는 파라미터를 받으려고 하면 에러가 발생한다. (required = ture)
    //  - required 속성의 값을 false로 지정하면 에러가 아닌 null 값을 넘겨준다.
    //  - defaultValue 속성을 사용하면 파라미터에 값이 없을 경우 기본값을 지정할 수 있다.
    /*
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        // @RequestParam String address) {
                        // @RequestParam(required = false) String address) {
                        @RequestParam(defaultValue = "서울특별시") String address) {

        log.info("username: {}, password: {}, address: {}", username, password, address);
        // .UserController login - username: 고구마, password: 1234, address: null -
        // UserController login - username: 고구마, password: 1234, address: 서울특별시 -

        return "redirect:/";
    }
     */

    // 2.3 동일한 이름의 파라미터가 여러 개 전달되는 경우
    //  - 동일한 이름의 파라미터가 여러 개 전달되는 경우 문자열이나 배열, 리스트로 값을 전달받을 수 있다.
    /*
    @GetMapping("/hobbies")
    // public String hobbies(@RequestParam String hobby){
    // public String hobbies(@RequestParam("hobby") String[] hobbies){
    public String hobbies(@RequestParam("hobby") List<String> hobbies){

        // log.info("hobbies {}" hobby);
        // log.info("hobbies: {}", Arrays.toString(hobbies));
        log.info("hobbies {}", hobbies);

        //localhost:8080/mvc/hobbies?hobby=축구&hobby=농구&hobby=야구
        return "redirect:/";
    }
     */

    // 3. 객체 타입으로 파라미터를 전송받는 방법
    //  - 요청 파라미터가 많은 경우 객체 타입으로 파라미터를 넘겨받는 방법이다.
    //  - 단, Setter가 존재해야 한다.
    //  - 기본적으로 기본생성자를 통해서 객체를 생성하고 Setter 메소드를 통해서 값을 주입한다.
    //  - 기본 생성자가 없고 매개변수가 있는 생성자만 존재하는 경우에는 해당하는 생성자를 통해서 값을 주입한다.
    /*
    @PostMapping("/login")
    public String lgoin(LoginRequestDto requestDto) {

        log.info("Login request: {}", requestDto);

        // .UserController lgoin - Login request: LoginRequestDto(username=admin, password=1234) -
        return "redirect:/";
    }
     */

    // 4. @PathVariable 어노테이션을 통해서 전송받는 방법
    //  - URL 경로상에 있는 특정 값을 가져오기 위해 사용하는 방법이다.
    //  - REST API를 사용할 때, 요청 URL 상에서 필요한 값을 가져오는 경우에 주로 사용한다.
    //  - 매핑 URL에 {}를 이용해서  변수명을 지정하고 @PathVariable 을 이용해서 변수의 값을 가져온다.
    //  - Path Variable명과 매개 변수명이 동일하다면 @PathVariable 의 괄호는 생략이 가능하다.
    //  - @PathVariable 까지 생략해버리면 @RequestParam 으로 인식해서 null값을 반환한다.
    /*
    @GetMapping("/users/{no}")
    // public String users(@PathVariable("no") String no) {
    public String users(@PathVariable String no) {

        log.info("User No: {}", no);

        return "redirect:/";
    }
     */

    // --------------------------------------

    // 뷰에 데이터를 전달하는 방법
    // 1. Model 객체 사용
    //  - Model이라는 객체는 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체이다.
    //  - 전달하고자 하는 데이터를 맵 형식으로 (key , value)으로 담을 수 있다.
    //  - Model 객체의 Scope는 Request이다.
    /*
    @GetMapping("/users/{no}")
    public String users(Model model, @PathVariable int no) {
        model.addAttribute("user", new UserResiobsDto(no, "hong123", "종로구"));

        return "users/info";
    }
     */
    // 2. ModelAndView 객체 사용
    //  - 컨트롤러에서 뷰로 전달할 데이터와 뷰에 대한 정보를 담는 객체이다.
    /*
    @GetMapping("/users/{no}")
    public ModelAndView users(ModelAndView modelAndView, @PathVariable int no) {
        modelAndView.addObject("user", new UserResiobsDto(no, "hong123", "종로구"));
        modelAndView.setViewName("users/info");

        return modelAndView;
    }
     */

    // 클라이언트로 뷰가 아닌 데이터를 전송하기
    // 1. ResponseBody 어노테이션 사용
    //  - 일반적으로 컨트롤러 메소드의 반환형이 String 일 경우 뷰의 이름을 반환한다.
    //  - @ResponseBody 붙은 메소드는 클라이언트로 직접  데이터를 반환할 수 있다.
    /*
    @GetMapping("/data")
    @ResponseBody
    public String data(){
        return "Hello World";
    }
     */
    
    // * JSON (Javascript Object Notation, 자바스크립트 객체 표현법
    //  - 자바스크립트 문법을 따르는 문자 기반의 데이터 포맷이다
    //    - {}안에  key: value로 구성
    //    - key는 반드시 문자열 사용
    //    - value String, Number, Boolean, Array ,Object, null 저장 가능
    //  - 대부분의 프로그래밍 언어에서 JSO 데이터를 핸들링 할 수있는 라이브러리를 제공하고 있다.
    //  - (시스템 간 객체 교환이 용이)

    // * jackson 라이브러리
    //  - 자바 객체를 Json 형태의 데이터로 변환하기 위한 라이브러리이다.
    //  - 스프링에서는 jackson 라이브러리를 추가하고 @ResponseBody를 사용하면
    //    컨트롤러의 메소드에서 리턴된 객체를 자동으로 JSON 문자열로 변경해서 응답한다.
    /*
    @GetMapping("/users/{no}")
    @ResponseBody
    public Object users(@PathVariable int no){
        Map<String, Object> map = new HashMap<>();
        map.put("no", no);
        map.put("name","홍길동");
        map.put("isTrue",true);
        map.put("user",new UserResiobsDto(no,"hong123","서울특별시 종로구"));
        map.put("number",new int[] {4, 5, 6, 7});

        List<Object> list = new ArrayList<>();
        list.add(null);
        list.add("hong123");
        list.add(10);
        list.add(false);
        list.add(new UserResiobsDto(no,"hong123","서울 특별시 종로구"));

        return list;
        // return new UserResiobsDto(no,"hong123","서울 특별시 종로구");
    }
     */

    // 2. @RestController 어노테이션 사용 - 자바 4.0부터 지원
    //  - 해당 어노테이션이 붙은 클래스의 모든 메소드에서 데이터를 반환하는 경우 사용한다.
    //  - @RestController 어노테이션을 선언하면 클래스의 모든 메소드는
    //    @ResponseBody 어노테이션이 기본으로 적용된다.
    /*
    @GetMapping("/users/{no}")
    public Object user(@PathVariable("no") int no){
        return new UserResponseDto(no,"hong123","서울특별시 동작구");
    }
     */

    // 3. ResponseEntity 객체 사용
    //   - HttpEntity를 상속하는 클래스로 HTTP 응답 데이터를 포함하는 클래스이다.
    //   - 개발자가 직접 HTTP Body, Header, Status Code를 세팅해서 반환할 수 있다.

    // * HttpEntity
    //   - Spring에서 제공하는 클래스로 HTTP 요청 또는 응답에 해당하는
    //     HTTP Header와 HTTP Body를 포함하는 클래스이다.
    @GetMapping("/users/{no}")
    public ResponseEntity<UserResponseDto> users(@PathVariable int no) {
        UserResponseDto responseDto = new UserResponseDto(no,"lee123", "경기도 파주시");

        // return ResponseEntity.ok(responseDto);
        // return new ResponseEntity<>(responseDto, HttpStatus.OK);
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 상태 반환
        return ResponseEntity.ok() // 헤더, 바디 모두 설정가능
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(responseDto);
    }
}
