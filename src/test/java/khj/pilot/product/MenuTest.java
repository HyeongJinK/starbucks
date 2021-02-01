package khj.pilot.product;

import khj.pilot.exception.EmptyProductException;
import khj.pilot.exception.OutOfStockException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MenuTest {
    Menu menu;

    @Before
    public void setUp() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "아이스 아메리카노", BigDecimal.valueOf(1500l)));
        productList.add(new Product(1, "녹차", BigDecimal.valueOf(2000l), 2));
        menu = new Menu(productList);
    }

    @Test
    public void getProduct() {
        Product product = menu.getProduct(0);
        assertEquals(product.getName(), "아이스 아메리카노");
    }

    @Test(expected = EmptyProductException.class)
    public void emptyProductCheck() {
        menu.getProduct(2);
    }

    @Test(expected = OutOfStockException.class)
    public void outOfStockCheck() {
        menu.getProduct(1);
        menu.getProduct(1);
        menu.getProduct(1);
    }
}