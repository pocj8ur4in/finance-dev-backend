package finance.dev.api.ex12.controller;

import finance.dev.domain.ex03.entity.VendingMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class Ex12Controller {
    private final VendingMachine vendingMachine;

    @GetMapping("/ex12")
    public String ex12GET() {
        return "ex12/index";
    }

    @GetMapping("/ex12/add")
    public String ex12AddGET() {
        return "ex12/add";
    }

    @GetMapping("/ex12/update/{name}")
    public String ex12UpdateGET(@PathVariable("name") String name, Model model) {
        model.addAttribute(
                "product",
                vendingMachine.getProduct(name)
        );

        return "ex12/update";
    }
}