package finance.dev.domain.ex03.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@RequiredArgsConstructor
public class VendingMachine {
    private final ArrayList<Product> productArrayList = new ArrayList<>();

    public Product getProduct(String name) {
        return productArrayList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        productArrayList.add(product);
    }

    public void removeProduct(String name) {
        productArrayList.remove(
                productArrayList.stream()
                        .filter(product -> product.getName().equals(name))
                        .findFirst()
                        .orElse(null)
        );
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
}
