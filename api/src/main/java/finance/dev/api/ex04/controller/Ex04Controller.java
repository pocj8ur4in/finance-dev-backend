package finance.dev.api.ex04.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Ex04Controller {

    @GetMapping("/ex04")
    public String ex01GET() {
        return "ex04/index";
    }
}