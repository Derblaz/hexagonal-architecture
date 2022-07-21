package study.hexagonalarchitecture.domain.model.service;

import study.hexagonalarchitecture.domain.model.Product;
import study.hexagonalarchitecture.domain.model.repository.ProductRepository;

public class ProductServiceDefault implements ProductService {

    ProductRepository productRepository;

    public ProductServiceDefault(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product enable(Product product) {
        return null;
    }

    @Override
    public Product disable(Product product) {
        return null;
    }
}
