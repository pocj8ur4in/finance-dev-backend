package finance.dev.domain.ex11.entity;

import finance.dev.domain.ex11.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void create(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Product find(String name) {
        return productRepository.findByName(name);
    }

    @Transactional
    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void update(String target, Product product) {
        productRepository.deleteByName(target);
        productRepository.save(product);
    }

    @Transactional
    public void delete(String name) {
        productRepository.deleteByName(name);
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
