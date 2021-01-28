package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.product.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StoreTest {

    @Test
    public void calculateTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(0, "test", BigDecimal.valueOf(1000l)));
        products.add(new Product(1, "test2", BigDecimal.valueOf(3000l)));

        Store store = new Store();
        store.addOrder(new Order(products));

        assertEquals(store.calculate().longValue(), BigDecimal.valueOf(4000l).longValue());
    }
}