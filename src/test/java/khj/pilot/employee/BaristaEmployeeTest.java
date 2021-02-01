package khj.pilot.employee;

import khj.pilot.order.Order;
import khj.pilot.order.OrderType;
import khj.pilot.product.Menu;
import khj.pilot.store.Desk;
import khj.pilot.store.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BaristaEmployeeTest {
    BaristaEmployee be;
    Store store;
    Menu menu;

    @Before
    public void setUp() {
        be = new BaristaEmployee("마루타", 1);
        Desk desk = new Desk();
        store = new Store();
        menu = new Menu();

        store.addOrder(desk.newOrder()
                .addProduct(menu.getProduct(0))
                .completeOrder());
    }

    @Test
    public void working() {
        try {
            be.working(store, menu);
            Thread.sleep(1500);
            List<Order> orderList = store.getOrders();
            assertEquals(orderList.get(0).getOrderType(), OrderType.COMPLETE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
