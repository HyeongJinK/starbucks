package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Desk {
    private Store store = new Store();
    private List<Product> products = new ArrayList<>();
    /**
     * 신규주문
     * */
    public Desk newOrder () {
        return new Desk();
    }
    /**
     * 주문하기
     * */
    public Desk addProduct(int idx) {
        products.add(store.getProduct(idx));
        return this;
    }
    /**
     * 주문완료
     * */
    public Order completeOrder() {
        return new Order(products);
    }
}
