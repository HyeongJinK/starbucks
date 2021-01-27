package khj.pilot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 * */
public class Order {
    List<Beverage> beverages = new ArrayList<>();
    LocalDateTime orderDate;
    OrderType orderType;

    /**
     * 주문 시작하기
     * */
    public Order() {
        orderType = OrderType.ON_ORDER;
    }
    /**
     *  음료 추가하기
     * */
    public void addOrder(Beverage beverage) {
        beverages.add(beverage);
    }
    /**
     * 주문 완료
     * */
    public void completeOrder() {
        orderDate = LocalDateTime.now();
        orderType = OrderType.COMPLETE;
    }
}
