package finance.dev.domain.ex06.dto;

import finance.dev.domain.ex03.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Ex06GetResponse {
    private ArrayList<Product> products;

    public Ex06GetResponse(ArrayList<Product> products) {
        this.products = products;
    }
}
