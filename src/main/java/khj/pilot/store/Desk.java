package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.product.Product;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Desk {
    private List<Product> products;

    /**
     * 신규주문
     * */
    public Desk newOrder () {
        products.clear();
        return this;
    }
    /**
     * 주문하기
     * */
    public Desk addProduct(int idx) {
        products.add(products.get(idx));
        return this;
    }
    /**
     * 주문완료
     * */
    public Order completeOrder() {
        return new Order(products);
    }
}
