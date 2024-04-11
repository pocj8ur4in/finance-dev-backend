package finance.dev.api.ex11.controller;

import finance.dev.domain.ex11.entity.Product;
import finance.dev.domain.ex11.entity.ProductService;
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
public class Ex11Controller {
    private final ProductService productService;

    @GetMapping("/ex11")
    public String ex11GET(Model model) {
        model.addAttribute(
                "products", productService.findAll()
        );

        return "ex11/index";
    }

    @GetMapping("/ex11/add")
    public String ex11GET() {
        return "ex11/add";
    }

    @PostMapping("/ex11/add")
    public String ex11POST(@RequestParam("name") String name,
                            @RequestParam("price") int price,
                            @RequestParam("date") LocalDate date) {
        productService.create(
                Product.builder()
                        .name(name)
                        .price(price)
                        .date(date)
                        .build());

        return "redirect:/ex11";
    }

    @GetMapping("/ex11/delete/{name}")
    public String ex11DeleteGET(@PathVariable("name") String name) {
        productService.delete(name);

        return "redirect:/ex11";
    }

    @GetMapping("/ex11/update/{name}")
    public String ex11UpdateGET(@PathVariable("name") String name, Model model) {
        model.addAttribute(
                "product",
                productService.find(name)
        );

        return "ex11/update";
    }

    @PostMapping("/ex11/update")
    public String ex11UpdatePOST(@RequestParam("target") String target,
                                    @RequestParam("name") String name,
                                    @RequestParam("price") int price,
                                    @RequestParam("date") LocalDate date) {
        productService.update(target, Product.builder()
                        .name(name)
                        .price(price)
                        .date(date)
                        .build());

        return "redirect:/ex11";
    }
}
