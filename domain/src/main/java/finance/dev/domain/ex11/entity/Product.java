package finance.dev.domain.ex11.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "date")
    private LocalDate date;

    @Builder
    public Product(String name, int price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
