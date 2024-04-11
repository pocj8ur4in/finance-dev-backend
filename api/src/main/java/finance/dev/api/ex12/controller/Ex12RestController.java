package finance.dev.api.ex12.controller;

import finance.dev.domain.ex11.entity.Product;
import finance.dev.domain.ex06.dto.Ex06DeleteRequest;
import finance.dev.domain.ex06.dto.Ex06PostRequest;
import finance.dev.domain.ex06.dto.Ex06PutRequest;
import finance.dev.domain.ex11.entity.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/api/ex12")
@RequiredArgsConstructor
@Tag(name = "ex12", description = "자판기를 DB와 함께 관리합니다.")
public class Ex12RestController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "상품 목록 조회", description = "상품 목록을 조회합니다.")
    public ResponseEntity<ArrayList<Product>> get() {
        ArrayList<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    @Operation(summary = "상품 추가", description = "상품을 추가합니다.")
    @Parameter(name = "product", description = "상품", required = true)
    public ResponseEntity<Void> add(@RequestBody Ex06PostRequest ex06PostRequest) {
        productService.create(
                Product.builder()
                        .name(ex06PostRequest.getProduct().getName())
                        .price(ex06PostRequest.getProduct().getPrice())
                        .date(ex06PostRequest.getProduct().getDate())
                        .build()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
    @Parameter(name = "name", description = "상품명", required = true)
    public ResponseEntity<Void> delete(@RequestBody Ex06DeleteRequest ex06DeleteRequest) {
        productService.delete(ex06DeleteRequest.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    @Operation(summary = "상품 수정", description = "상품을 수정합니다.")
    public ResponseEntity<Void> update(@RequestBody Ex06PutRequest ex06PutRequest) {
        productService.update(
                ex06PutRequest.getTarget(),
                Product.builder()
                        .name(ex06PutRequest.getProduct().getName())
                        .price(ex06PutRequest.getProduct().getPrice())
                        .date(ex06PutRequest.getProduct().getDate())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
