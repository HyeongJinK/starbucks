package khj.pilot.employee;

import khj.pilot.exception.EmptyProductException;
import khj.pilot.product.Menu;
import khj.pilot.store.Store;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ClerkEmployeeTest {
    ClerkEmployee ce;
    Store store;
    Menu menu;

    @Before
    public void setUp() {
        ce = new ClerkEmployee("마루타", 1);
        store = new Store();
        menu = new Menu();
    }

    @Test
    public void working() {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
            ce.setSc(in);
            ce.working(store, menu);

            assertEquals(store.getOrders().get(0).getProducts().get(0).getName(), "아이스 아메리카노");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyProductException() {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
            ce.setSc(in);
            ce.working(store, menu);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}