package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.order.OrderType;
import khj.pilot.product.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DeskTest {
    @Test
    public void orderTest() {
        Desk desk = new Desk();
        Product product = new Product(0, "test", BigDecimal.valueOf(1000l));

        Order order = desk.newOrder()
            .addProduct(product)
            .completeOrder();

        assertEquals(order.getProducts().size(), 1);
        assertEquals(order.getOrderType(), OrderType.ON_ORDER);
    }
}