package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    //localhost 들어가면 바로 뜨는 화면을 의미("/")
    public String home(){
        return"home";

    }


}
