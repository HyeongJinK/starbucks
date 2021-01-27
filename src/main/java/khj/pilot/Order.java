package khj.pilot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 * */
public class Order {
    List<Beverage> orders = new ArrayList<>();
    LocalDateTime orderDate;

    /**
     *  음료 추가하기
     * */
    public void addOrder(Beverage beverage) {
        orders.add(beverage);
    }
    /**
     * 주문 완료
     * */
    public void completeOrder() {
        orderDate = LocalDateTime.now();
    }
}
