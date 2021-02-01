package khj.pilot.order;

import khj.pilot.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {
    Order order;
    @Before
    public void setUp() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "test", BigDecimal.valueOf(1000l)));
        productList.add(new Product(1, "test2", BigDecimal.valueOf(2000l)));
        order = new Order(productList);
    }

    @Test
    public void getTotalPrice() {
        assertEquals("주문금액이 맞지 않습니다.", order.getTotalPrice().longValue()
                , BigDecimal.valueOf(3000l).longValue());
    }

    @Test
    public void making() {
        assertEquals(order.getOrderType(), OrderType.ON_ORDER);
        order.making();
        assertEquals(order.getOrderType(), OrderType.MAKING);
    }

    @Test
    public void complete() {
        assertEquals(order.getOrderType(), OrderType.ON_ORDER);
        order.complete();
        assertEquals(order.getOrderType(), OrderType.COMPLETE);
    }
}