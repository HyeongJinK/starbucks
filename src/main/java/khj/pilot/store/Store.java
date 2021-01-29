package khj.pilot.store;

import khj.pilot.employee.BaristaEmployee;
import khj.pilot.employee.ClerkEmployee;
import khj.pilot.employee.Employee;
import khj.pilot.employee.EmployeeStatus;
import khj.pilot.order.Order;
import khj.pilot.product.Menu;
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
    private final long OPERATING_TIME = 10;                     // 운영시간
    private final long MAX_CUSTOMER = 5;                        // 최대 입장 손님
    private List<Employee> employees = new ArrayList<>();       // 직원
    private List<Order> orders = new ArrayList<>();
    private List<Future> employeeFutures = new ArrayList();
    private Menu menu;

    public Store () {
        menu = new Menu();
        initEmployees(null);
    }
    public Store (List<Employee> employees, List<Product> products) {
        menu = new Menu(products);
        initEmployees(employees);
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
     *  TODO 영업 시작
     * */
    public void start() {
        ExecutorService storeExecutor = Executors.newFixedThreadPool(1);

        Future storeFuture = storeExecutor.submit(() -> {
            log.info("영업시작");
            working();

            while (true) {
                Thread.sleep(1000);
            }
        });

        try {
            storeFuture.get(OPERATING_TIME, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            log.info("영업종료");
            storeFuture.cancel(true);                           // 상점 종료
            employeeFutures.forEach(ef -> ef.cancel(true));     // 직원 종료
        }
    }
    /**
     * 직원 작업 수행
     * */
    public void working() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(employees.size());
        employees.forEach(employee -> {
            employeeFutures.add(executor.submit(() -> {
                while (true) {
                    if (employee.getEmployeeStatus().equals(EmployeeStatus.Waiting)) {
                        employee.working(orders, menu);
                    }
                }
            }));
        });
        executor.shutdown();
        executor.awaitTermination(1l, TimeUnit.MINUTES);
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
        orders.stream()
            .forEach(o -> {
                log.info("판매시각 : " + o.getOrderDate().toString());
                printProductName(o);
                log.info("판매금액 : " + o.getTotalPrice().toString());
            });
    }

    private void printProductName(Order o) {
        o.getProducts().stream()
            .forEach((p) -> {
                log.info(p.getName());
            });
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
