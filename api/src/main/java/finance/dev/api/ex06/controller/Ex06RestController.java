package finance.dev.api.ex06.controller;

import finance.dev.api.ex06.dto.Ex06DeleteRequest;
import finance.dev.api.ex06.dto.Ex06GetResponse;
import finance.dev.api.ex06.dto.Ex06PostRequest;
import finance.dev.api.ex06.dto.Ex06PutRequest;
import finance.dev.domain.ex03.entity.VendingMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/ex06")
@RequiredArgsConstructor
public class Ex06RestController {
    private final VendingMachine vendingMachine;

    @GetMapping
    public ResponseEntity<Ex06GetResponse> get() {
        return ResponseEntity.ok(new Ex06GetResponse(vendingMachine.getProductArrayList()));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody Ex06PostRequest ex06PostRequest) {
        vendingMachine.addProduct(ex06PostRequest.getProduct());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Ex06DeleteRequest ex06DeleteRequest) {
        vendingMachine.removeProduct(ex06DeleteRequest.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Ex06PutRequest ex06PutRequest) {
        vendingMachine.removeProduct(ex06PutRequest.getTarget());
        vendingMachine.addProduct(ex06PutRequest.getProduct());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
