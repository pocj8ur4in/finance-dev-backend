package finance.dev.api.ex08.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Ex08Controller {
    @GetMapping("/ex08")
    public String ex08GET() {
        return "ex08/index";
    }

    @GetMapping("/ex08/register")
    public String ex08RegisterGET() {
        return "ex08/register";
    }

    @GetMapping("/ex08/login")
    public String ex08LoginGET() {
        return "ex08/login";
    }
}
