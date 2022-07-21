package study.hexagonalarchitecture.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void enable() {

        var products = List.of(
                new Product(1L, "product", 0D, Status.DISABLE),
                new Product(1L, "product", -1D, Status.DISABLE)
        );

        products.forEach( product -> {
            Exception exception = assertThrows(RuntimeException.class, product::enable, "Price = "+product.getPrice());
            assertEquals("The price must be greater than zero to enable the product", exception.getMessage());
        });

        var product = new Product(1L, "product", 100D, Status.DISABLE);
        product.enable();
        assertEquals(Status.ENABLE, product.getStatus());

    }

    @Test
    void disable(){

        var products = List.of(
                new Product(1L, "product", 1D, Status.ENABLE),
                new Product(1L, "product", -1D, Status.ENABLE)
        );

        products.forEach( product -> {
            Exception exception = assertThrows(RuntimeException.class, product::disable, "Price = "+product.getPrice());
            assertEquals("The price must be zero in order to have product disable", exception.getMessage());
        });

        var product = new Product(1L, "product", 0D, Status.DISABLE);
        product.disable();
        assertEquals(Status.DISABLE, product.getStatus());
    }

    @Test
    void isValid(){
        var expectedErroMessage = "The price must be greater or equal zero";
        var product1 = new Product(1L, "product", -1D, Status.DISABLE);
        Exception exception = assertThrows(
                RuntimeException.class,
                product1::isValid,
                "Price = "+product1.getPrice());
        assertEquals(expectedErroMessage, exception.getMessage());

        product1.isValid((isValid, erroMessage) -> {
            assertFalse(isValid);
            assertEquals(expectedErroMessage, erroMessage);
        });


        var product2 = new Product(1L, "product", 100D, Status.DISABLE);
        assertTrue(product2.isValid());

        product2.isValid((isValid, erroMessage) -> {
            assertTrue(isValid);
            assertEquals("", erroMessage);
        });


        var products = List.of(
                new Product(1L, null, 100D, Status.ENABLE),
                new Product(1L, "", 100D, Status.ENABLE)
        );
        products.forEach( product -> {
            Exception exceptionName = assertThrows(
                    RuntimeException.class,
                    product::isValid,
                    "Name = " + product.getName());
            assertEquals("The name not must be blank", exceptionName.getMessage());
        });
    }
}