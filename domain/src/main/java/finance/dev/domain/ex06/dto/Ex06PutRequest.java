package finance.dev.domain.ex06.dto;

import finance.dev.domain.ex03.entity.Product;
import lombok.Data;

@Data
public class Ex06PutRequest {
    private String target;
    private Product product;
}
