package finance.dev.api.ex03.controller;

import finance.dev.domain.ex03.entity.Product;
import finance.dev.domain.ex03.entity.VendingMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class Ex03Controller {
    private final VendingMachine vendingMachine;

    @GetMapping("/ex03")
    public String ex02GET(Model model) {
        model.addAttribute(
                "products",
                vendingMachine.getProductArrayList()
        );

        return "ex03/index";
    }

    @GetMapping("/ex03/add")
    public String ex02GET() {
        return "ex03/add";
    }

    @PostMapping("/ex03/add")
    public String ex02POST(@RequestParam("name") String name,
                            @RequestParam("price") int price,
                            @RequestParam("date") LocalDate date) {
        vendingMachine.addProduct(
                Product.builder()
                        .name(name)
                        .price(price)
                        .date(date)
                        .build()
        );

        return "redirect:/ex03";
    }

    @GetMapping("/ex03/delete/{name}")
    public String ex02DeleteGET(@PathVariable("name") String name) {
        vendingMachine.removeProduct(name);

        return "redirect:/ex03";
    }

    @GetMapping("/ex03/update/{name}")
    public String ex02UpdateGET(@PathVariable("name") String name, Model model) {
        model.addAttribute(
                "product",
                vendingMachine.getProduct(name)
        );

        return "ex03/update";
    }

    @PostMapping("/ex03/update")
    public String ex02UpdatePOST(@RequestParam("target") String target,
                                    @RequestParam("name") String name,
                                    @RequestParam("price") int price,
                                    @RequestParam("date") LocalDate date) {
        vendingMachine.removeProduct(target);
        vendingMachine.addProduct(
                Product.builder()
                        .name(name)
                        .price(price)
                        .date(date)
                        .build());

        return "redirect:/ex03";
    }
}
