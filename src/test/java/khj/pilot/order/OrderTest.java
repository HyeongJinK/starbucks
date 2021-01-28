package khj.pilot.order;

import khj.pilot.product.Product;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void totalPriceTest() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "test", BigDecimal.valueOf(1000l)));
        productList.add(new Product(1, "test2", BigDecimal.valueOf(2000l)));
        Order order = new Order(productList);

        assertEquals("주문금액이 맞지 않습니다.", order.getTotalPrice().longValue()
                , BigDecimal.valueOf(3000l).longValue());
    }
}