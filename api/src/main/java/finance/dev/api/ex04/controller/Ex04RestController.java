package finance.dev.api.ex04.controller;

import finance.dev.api.ex04.dto.Ex04Reponse;
import finance.dev.domain.ex01.entity.Count;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/api/ex04")
@RequiredArgsConstructor
@Tag(name = "ex04", description = "카운터를 증가시키거나 감소시킵니다.")
public class Ex04RestController {
    private final Count count;
    @GetMapping("/plus")
    @Operation(summary = "카운터 증가", description = "카운터 값을 1 증가시킵니다.")
    @Parameter(name = "count", description = "카운터 값", required = true)
    public ResponseEntity<Ex04Reponse> incrementCount() {
        return ResponseEntity.ok(
                Ex04Reponse.builder()
                        .number(count.plusCnt())
                        .build()
        );
    }

    @GetMapping("/minus")
    @Operation(summary = "카운터 감소", description = "카운터 값을 1 감소시킵니다.")
    @Parameter(name = "count", description = "카운터 값", required = true)
    public ResponseEntity<Ex04Reponse> decrementCount() {
        return ResponseEntity.ok(
                Ex04Reponse.builder()
                        .number(count.minusCnt())
                        .build()
        );
    }
}
