package khj.pilot.store;

import khj.pilot.employee.BaristaEmployee;
import khj.pilot.employee.ClerkEmployee;
import khj.pilot.employee.Employee;
import khj.pilot.order.Order;
import khj.pilot.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 매장
 * */

public class Store {
    Logger log = LoggerFactory.getLogger(Store.class);
    private List<Employee> employees = new ArrayList<>();      // 직원
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private final long OPERATING_TIME = 5;      // 운영시간
    private List<Future> employeeFutures = new ArrayList();
    private Desk desk;

    public Store () {
        initEmployees();
        initProduct();
        desk = new Desk(products);
    }

    public Desk getDesk() {
        return this.desk;
    }
    /**
     * 직원 초기화
     * */
    private void initEmployees() {
        employees.add(new ClerkEmployee("Eric", 2));
        employees.add(new BaristaEmployee("Tom", 3));
        employees.add(new BaristaEmployee("Chicol", 3));
    }
    /**
     * 상품 초기화
     * */
    private void initProduct() {
        products.add(new Product(0, "아이스 아메리카노", BigDecimal.valueOf(1500l)));
    }
    /**
     *  TODO 영업 시작
     * */
    public void start() {
        ExecutorService storeExecutor = Executors.newFixedThreadPool(1);

        Future storeFuture = storeExecutor.submit(() -> {
            working();

            while(true) {
                log.info("ing...");
                Thread.sleep(1000);
            }
        });

        try {
            storeFuture.get(OPERATING_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            log.info("영업종료");
            storeFuture.cancel(true);
            employeeFutures.forEach(ef -> ef.cancel(true));
        }
    }
    /**
     * 직원 작업 수행
     * */
    public void working() {
        employees.parallelStream()
            .forEach(employee -> {
                ExecutorService executor = Executors.newFixedThreadPool(1);

                employeeFutures.add(executor.submit(() -> {
                    while (true) {
                        log.info(employee.getName());
                        Thread.sleep(employee.getMillisProcessingTime());
                    }
                }));
            });
    }
    /**
     * 주문 추
     * */

    public void addOrder(Order order) {
        orders.add(order);
    }
    /**
     * TODO 영업 종료 후 판매 내역 조회
     * */
    public void closingBusiness() {
    }
    /**
     * 정산액 계산
     * */
    public BigDecimal calculate() {
        return orders.stream()
                .map(o -> o.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
