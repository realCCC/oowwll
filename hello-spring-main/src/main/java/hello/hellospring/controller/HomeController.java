package hello.hellospring.controller;

import hello.hellospring.dto.HelloResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController 빼니까 되었음 <<< 이거 원인 알아보기
@Controller
public class HomeController {
    //localhost:8080키면 home.html을 출력
    @GetMapping("/")
    public String home(){
        return "home";
    }

}
