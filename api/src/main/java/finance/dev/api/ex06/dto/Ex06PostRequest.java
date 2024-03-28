package finance.dev.api.ex06.dto;

import finance.dev.domain.ex03.entity.Product;
import lombok.Data;

@Data
public class Ex06PostRequest {
    private Product product;

    public Ex06PostRequest() {}

    public Ex06PostRequest(Product product) {
        this.product = product;
    }
}
