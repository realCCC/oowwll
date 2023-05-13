package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UseController {
    @GetMapping
    public String userSave(){
        return "layout/user/user-save";
    }
}
