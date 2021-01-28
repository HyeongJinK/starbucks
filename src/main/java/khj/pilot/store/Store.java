package khj.pilot.store;

import khj.pilot.employee.BaristaEmployee;
import khj.pilot.employee.ClerkEmployee;
import khj.pilot.employee.Employee;
import khj.pilot.exception.OutOfStockException;
import khj.pilot.order.Order;
import khj.pilot.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * 매장
 * */
public class Store {
    Logger log = LoggerFactory.getLogger(Store.class);
    private final long OPERATING_TIME = 10;                     // 운영시간
    private List<Employee> employees = new ArrayList<>();      // 직원
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Future> employeeFutures = new ArrayList();

    public Store () {
        initEmployees(null);
        initProduct(null);
    }
    public Store (List<Employee> employees, List<Product> products) {
        initEmployees(employees);
        initProduct(products);
    }

    /**
     * 상품정보 리턴
     * */
    public Product getProduct(int idx) {
        Product product = products.get(idx);

        stockCheck(idx, product);
        return products.get(idx);
    }

    private void stockCheck(int idx, Product product) {
        if (product.getStock() == 0) throw new OutOfStockException("재고 부족");
        if (product.getStock() != -1) products.get(idx).subStock();
    }

    /**
     * 직원 초기화
     * */
    private void initEmployees(List<Employee> employees) {
        if (employees == null) {
            this.employees.add(new ClerkEmployee("Eric", 2));
            this.employees.add(new BaristaEmployee("Tom", 3));
            this.employees.add(new BaristaEmployee("Chicol", 3));
        } else {
            this.employees = employees;
        }
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
    /**
     *  TODO 영업 시작
     * */
    public void start() {
        ExecutorService storeExecutor = Executors.newFixedThreadPool(1);
        Scanner sc = new Scanner(System.in);
        Future storeFuture = storeExecutor.submit(() -> {
            String orderNum = "";
            log.info("영업시작");
            working();

            while(true) {
                Thread.sleep(1000);
                log.info("...");

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
            storeFuture.cancel(true);                           // 상점 종료
            employeeFutures.forEach(ef -> ef.cancel(true));     // 직원 종료
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
     * 주문 추가
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
