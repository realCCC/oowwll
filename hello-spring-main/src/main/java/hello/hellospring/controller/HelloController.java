package hello.hellospring.controller;

import hello.hellospring.dto.HelloResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Spring Boot!";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name")String name, @RequestParam("nickname")String nickname){
        return new HelloResponseDto(name, nickname);
    }
}
