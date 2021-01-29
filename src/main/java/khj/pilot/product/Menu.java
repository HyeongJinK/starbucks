package khj.pilot.product;

import khj.pilot.exception.OutOfStockException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Menu {
    private List<Product> products = new ArrayList<>();

    public Menu() {
        initProduct(null);
    }

    public Menu(List<Product> products) {
        initProduct(products);
    }
    /**
     * 상품 초기화
     * */
    private void initProduct(List<Product> products) {
        if (products == null) {
            this.products.add(new Product(0, "아이스 아메리카노", BigDecimal.valueOf(1500l)));
        } else {
            this.products = products;
        }
    }

    private void stockCheck(int idx, Product product) {
        if (product.getStock() == 0) throw new OutOfStockException("재고 부족");
        if (product.getStock() != -1) products.get(idx).subStock();
    }
    /**
     * 상품정보 리턴
     * */
    public Product getProduct(int idx) {
        System.out.println("=====================");
        Product product = products.get(idx);

        stockCheck(idx, product);
        return products.get(idx);
    }
}
