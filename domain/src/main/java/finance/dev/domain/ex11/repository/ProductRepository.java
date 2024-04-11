package finance.dev.domain.ex11.repository;

import finance.dev.domain.ex11.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    void deleteByName(String name);
    ArrayList<Product> findAll();
}
