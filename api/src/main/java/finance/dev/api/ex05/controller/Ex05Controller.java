package finance.dev.api.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Ex05Controller {

    @GetMapping("/ex05")
    public String ex05GET() {
        return "ex05/index";
    }
}