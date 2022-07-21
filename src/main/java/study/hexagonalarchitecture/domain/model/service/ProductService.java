package study.hexagonalarchitecture.domain.model.service;

import study.hexagonalarchitecture.domain.model.Product;

public interface ProductService {
    Product get(Long id);
    Product create(Product product);
    Product enable(Product product);
    Product disable(Product product);

}
