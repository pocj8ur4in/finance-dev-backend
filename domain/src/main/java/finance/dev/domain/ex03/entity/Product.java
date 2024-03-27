package finance.dev.domain.ex03.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@RequiredArgsConstructor
public class Product {
    private String name;
    private int price;
    private LocalDate date;

    @Builder
    public Product(String name, int price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
