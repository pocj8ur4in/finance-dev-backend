package finance.dev.api.ex06.dto;

import finance.dev.domain.ex03.entity.Product;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Ex06GetResponse {
    private ArrayList<Product> products;

    public Ex06GetResponse() {}

    public Ex06GetResponse(ArrayList<Product> products) {
        this.products = products;
    }
}
