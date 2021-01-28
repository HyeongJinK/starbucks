package khj.pilot.store;

import khj.pilot.order.Order;
import khj.pilot.product.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문수정, 주문 삭제
 * */
@RequiredArgsConstructor
public class Desk {
    Logger log = LoggerFactory.getLogger(Desk.class);
    private List<Product> products = new ArrayList<>();
    private final Store store;

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
        try {
            products.add(store.getProduct(idx));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return this;
    }
    /**
     * 주문완료
     * */
    public Order completeOrder() {
        return new Order(products);
    }
}
