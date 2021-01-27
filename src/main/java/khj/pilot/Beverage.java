package khj.pilot;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Beverage {
    long idx;
    String name;
    BigDecimal price;
}
