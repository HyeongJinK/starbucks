package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문수정, 주문 삭제
 * */
@RequiredArgsConstructor
public class Desk {
    private List<Product> products = new ArrayList<>();

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

    public Desk addProduct(Product product) {
        products.add(product);

        return this;
    }
    /**
     * 주문완료
     * */
    public Order completeOrder() {
        return new Order(products);
    }
}
