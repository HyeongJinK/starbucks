package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.order.OrderType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeskTest {
    @Test
    public void orderTest() {
        Desk desk = new Desk(new Store());

        Order order = desk.newOrder()
            .addProduct(0)
            .completeOrder();

        assertEquals(order.getProducts().size(), 1);
        assertEquals(order.getOrderType(), OrderType.ON_ORDER);
    }
}