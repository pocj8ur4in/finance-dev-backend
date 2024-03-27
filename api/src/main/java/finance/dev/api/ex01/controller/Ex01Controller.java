package finance.dev.api.ex01.controller;

import finance.dev.domain.ex01.entity.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Ex01Controller {
    private final Count count;

    @GetMapping("/ex01")
    public String ex01GET(Model model) {
        model.addAttribute("message", count.getCnt());
        return "ex01/index";
    }

    @GetMapping("/ex01/plus")
    public String ex01PlusGET(Model model) {
        model.addAttribute("message", count.plusCnt());
        return "ex01/index";
    }

    @GetMapping("/ex01/minus")
    public String ex01MinusGET(Model model) {
        model.addAttribute("message", count.minusCnt());
        return "ex01/index";
    }
}
