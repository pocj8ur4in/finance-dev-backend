package finance.dev.api.ex05.controller;

import finance.dev.domain.ex05.dto.Ex05Request;
import finance.dev.domain.ex05.dto.Ex05Response;
import finance.dev.domain.ex02.entity.Calculator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/ex05")
@RequiredArgsConstructor
@Tag(name = "ex05", description = "계산기를 이용하여 더하기, 빼기, 곱하기, 나누기를 수행합니다.")
public class Ex05RestController {
    private final Calculator calculator;

    @PostMapping("/add")
    @Operation(summary = "더하기", description = "두 수를 더합니다.")
    @Parameter(name = "number1", description = "첫 번째 수", required = true)
    @Parameter(name = "number2", description = "두 번째 수", required = true)
    public ResponseEntity<Ex05Response> add(@RequestBody Ex05Request request) {
        calculator.setNum1(request.getNumber1());
        calculator.setNum2(request.getNumber2());
        return ResponseEntity.ok(
                Ex05Response.builder()
                        .result(calculator.add())
                        .build()
        );
    }

    @PostMapping("/subtract")
    @Operation(summary = "빼기", description = "두 수를 뺍니다.")
    @Parameter(name = "number1", description = "첫 번째 수", required = true)
    @Parameter(name = "number2", description = "두 번째 수", required = true)
    public ResponseEntity<Ex05Response> subtract(@RequestBody Ex05Request request) {
        calculator.setNum1(request.getNumber1());
        calculator.setNum2(request.getNumber2());
        return ResponseEntity.ok(
                Ex05Response.builder()
                        .result(calculator.subtract())
                        .build()
        );
    }

    @PostMapping("/multiply")
    @Operation(summary = "곱하기", description = "두 수를 곱합니다.")
    @Parameter(name = "number1", description = "첫 번째 수", required = true)
    @Parameter(name = "number2", description = "두 번째 수", required = true)
    public ResponseEntity<Ex05Response> multiply(@RequestBody Ex05Request request) {
        calculator.setNum1(request.getNumber1());
        calculator.setNum2(request.getNumber2());
        return ResponseEntity.ok(
                Ex05Response.builder()
                        .result(calculator.multiply())
                        .build()
        );
    }

    @PostMapping("/divide")
    @Operation(summary = "나누기", description = "두 수를 나눕니다.")
    @Parameter(name = "number1", description = "첫 번째 수", required = true)
    @Parameter(name = "number2", description = "두 번째 수", required = true)
    public ResponseEntity<Ex05Response> divide(@RequestBody Ex05Request request) {
        calculator.setNum1(request.getNumber1());
        calculator.setNum2(request.getNumber2());
        return ResponseEntity.ok(
                Ex05Response.builder()
                        .result(calculator.divide())
                        .build()
        );
    }
}
