package study.hexagonalarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;

import static study.hexagonalarchitecture.domain.model.Status.*;

@Getter
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private Status status = DISABLE;

    public boolean isValid(){
        if (status == null)
            status = DISABLE;

        if (price < 0)
            throw new RuntimeException("The price must be greater or equal zero");

        if (name == null || name.isBlank())
            throw new RuntimeException("The name not must be blank");

        return true;

    }

    public void isValid(BiConsumer<Boolean, String> product){
        try{
            isValid();
        } catch (Exception e) {
            product.accept(Boolean.FALSE, e.getMessage());
            return;
        }

        product.accept(Boolean.TRUE, "");
    }

    public void enable(){
        if (price <= 0)
            throw new RuntimeException("The price must be greater than zero to enable the product");

        this.status = ENABLE;
    }

    public void disable(){
        if (price != 0)
            throw new RuntimeException("The price must be zero in order to have product disable");

        this.status = DISABLE;
    }

}
