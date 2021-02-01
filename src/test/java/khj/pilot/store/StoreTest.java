package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.order.OrderType;
import khj.pilot.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreTest {
    Store store;

    @Before
    public void setUp() {
        store = new Store();

        List<Product> products = new ArrayList<>();
        products.add(new Product(0, "test1-1", BigDecimal.valueOf(1000l)));
        products.add(new Product(1, "test1-2", BigDecimal.valueOf(3000l)));

        store.addOrder(new Order(products));

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product(0, "test2", BigDecimal.valueOf(1000l)));

        store.addOrder(new Order(products2));
    }

    @Test
    public void calculateTest() {
        assertEquals(store.calculate().longValue(), BigDecimal.valueOf(5000l).longValue());
    }

    @Test
    public void checkEnterCustomer() {
        assertTrue(store.checkEnterCustomer());
        assertTrue(store.checkEnterCustomer());
        assertTrue(store.checkEnterCustomer());
        assertTrue(store.checkEnterCustomer());
        assertTrue(store.checkEnterCustomer());
        assertFalse(store.checkEnterCustomer());
    }

    @Test
    public void checkEnterCustomerThreadSafe() {
        Thread thread1, thread2;
        thread1 = new Thread(() -> {for (int i= 0; i < 2; i++) store.checkEnterCustomer();});
        thread2 = new Thread(() -> {for (int i= 0; i < 2; i++) store.checkEnterCustomer();});
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(100);
            assertTrue(store.checkEnterCustomer());
            assertFalse(store.checkEnterCustomer());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getOnOrderAny() {
        Order order = store.getOnOrderAny();
        assertEquals(order.getProducts().get(0).getName(), "test1-1");
        assertEquals(order.getOrderType(), OrderType.MAKING);
        Order order2 = store.getOnOrderAny();
        assertEquals(order2.getProducts().get(0).getName(), "test2");
        Order order3 = store.getOnOrderAny();
        assertNull(order3);
    }
}