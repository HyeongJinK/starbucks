package khj.pilot.product;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void subStock() {
        Product product = new Product(0, "test", BigDecimal.valueOf(3000l), 2);
        assertEquals(product.getStock(), 2);
        product.subStock();
        assertEquals(product.getStock(), 1);
    }
}