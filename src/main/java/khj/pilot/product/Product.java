package khj.pilot.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Product {
    long idx;
    String name;
    BigDecimal price;
    long Stock = -1;

    public Product(long idx, String name, BigDecimal price) {
        this.idx = idx;
        this.name = name;
        this.price = price;
    }
}
