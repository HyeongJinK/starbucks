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
}
