package finance.dev.api.ex06.dto;

import finance.dev.domain.ex03.entity.Product;
import lombok.Data;

@Data
public class Ex06PutRequest {
    private String target;
    private Product product;

    public Ex06PutRequest() {}

    public Ex06PutRequest(String target, Product product) {
        this.target = target;
        this.product = product;
    }
}
