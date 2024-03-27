package finance.dev.api.ex02.controller;

import finance.dev.domain.ex02.entity.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class Ex02Controller {
    private final Calculator calculator;

    @GetMapping("/ex02")
    public String ex02GET() {
        return "ex02/index";
    }

    @PostMapping("/ex02/add")
    public String ex02AddPOST(@RequestParam("number1") int number1,
                                @RequestParam("number2") int number2,
                                Model model) {
        calculator.setNum1(number1);
        calculator.setNum2(number2);
        model.addAttribute("result", calculator.add());

        return "ex02/index";
    }

    @PostMapping("/ex02/subtract")
    public String ex02SubtractPOST(@RequestParam("number1") int number1,
                                @RequestParam("number2") int number2,
                                Model model) {
        calculator.setNum1(number1);
        calculator.setNum2(number2);

        model.addAttribute("result", calculator.subtract());

        return "ex02/index";
    }

    @PostMapping("/ex02/multiply")
    public String ex02MultiplyPOST(@RequestParam("number1") int number1,
                                @RequestParam("number2") int number2,
                                Model model) {
        calculator.setNum1(number1);
        calculator.setNum2(number2);
        model.addAttribute("result", calculator.multiply());

        return "ex02/index";
    }

    @PostMapping("/ex02/divide")
    public String ex02DividePOST(@RequestParam("number1") int number1,
                                @RequestParam("number2") int number2,
                                Model model) {
        calculator.setNum1(number1);
        calculator.setNum2(number2);
        model.addAttribute("result", calculator.divide());

        return "ex02/index";
    }
}
