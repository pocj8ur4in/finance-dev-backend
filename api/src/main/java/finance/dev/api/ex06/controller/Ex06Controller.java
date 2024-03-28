package finance.dev.api.ex06.controller;

import finance.dev.domain.ex03.entity.VendingMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class Ex06Controller {
    private final VendingMachine vendingMachine;

    @GetMapping("/ex06")
    public String ex06GET() {
        return "ex06/index";
    }

    @GetMapping("/ex06/add")
    public String ex06AddGET() {
        return "ex06/add";
    }

    @GetMapping("/ex06/update/{name}")
    public String ex06UpdateGET(@PathVariable("name") String name, Model model) {
        model.addAttribute(
                "product",
                vendingMachine.getProduct(name)
        );

        return "ex06/update";
    }
}