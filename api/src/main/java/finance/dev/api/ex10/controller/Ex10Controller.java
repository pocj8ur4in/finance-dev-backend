package finance.dev.api.ex10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class Ex10Controller {
    @GetMapping("/ex10")
    public String ex08GET() {
        return "ex10/index";
    }

    @GetMapping("/ex10/register")
    public String ex08RegisterGET() {
        return "ex10/register";
    }

    @GetMapping("/ex10/login")
    public String ex08LoginGET() {
        return "ex10/login";
    }

    @GetMapping("/ex10/admin")
    public String ex08AdminGET() {
        return "ex10/admin";
    }
}
