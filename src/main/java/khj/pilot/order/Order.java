package khj.pilot.order;

import khj.pilot.product.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 주문
 * */
@Getter
public class Order {
    List<Product> products;
    LocalDateTime orderDate;
    OrderType orderType;

    public Order(List<Product> products) {
        this.products = products;
        orderType = OrderType.ON_ORDER;
        orderDate = LocalDateTime.now();
    }
    /**
     * 주문금액 계산
     * */
    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
