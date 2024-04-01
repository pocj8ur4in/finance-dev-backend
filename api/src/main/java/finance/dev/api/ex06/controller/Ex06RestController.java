package finance.dev.api.ex06.controller;

import finance.dev.domain.ex03.entity.Product;
import finance.dev.domain.ex06.dto.Ex06DeleteRequest;
import finance.dev.domain.ex06.dto.Ex06PostRequest;
import finance.dev.domain.ex06.dto.Ex06PutRequest;
import finance.dev.domain.ex03.entity.VendingMachine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/api/ex06")
@RequiredArgsConstructor
@Tag(name = "ex06", description = "자판기를 관리합니다.")
public class Ex06RestController {
    private final VendingMachine vendingMachine;

    @GetMapping
    @Operation(summary = "상품 목록 조회", description = "상품 목록을 조회합니다.")
    public ResponseEntity<ArrayList<Product>> get() {
        ArrayList<Product> products = vendingMachine.getProductArrayList();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    @Operation(summary = "상품 추가", description = "상품을 추가합니다.")
    @Parameter(name = "product", description = "상품", required = true)
    public ResponseEntity<Void> add(@RequestBody Ex06PostRequest ex06PostRequest) {
        vendingMachine.addProduct(ex06PostRequest.getProduct());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
    @Parameter(name = "name", description = "상품명", required = true)
    public ResponseEntity<Void> delete(@RequestBody Ex06DeleteRequest ex06DeleteRequest) {
        vendingMachine.removeProduct(ex06DeleteRequest.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    @Operation(summary = "상품 수정", description = "상품을 수정합니다.")
    @Parameter(name = "target", description = "수정 대상 상품명", required = true)
    @Parameter(name = "product", description = "수정될 상품 내용", required = true)
    public ResponseEntity<Void> update(@RequestBody Ex06PutRequest ex06PutRequest) {
        vendingMachine.removeProduct(ex06PutRequest.getTarget());
        vendingMachine.addProduct(ex06PutRequest.getProduct());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
