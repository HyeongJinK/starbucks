package khj.pilot;

import khj.pilot.employee.BaristaEmployee;
import khj.pilot.employee.ClerkEmployee;
import khj.pilot.employee.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 매장
 * */

public class Store {
    Logger log = LoggerFactory.getLogger(Store.class);
    private List<Employee> employees = new ArrayList<>();      // 직원
    private List<Order> orders = new ArrayList<>();
    private final long OPERATING_TIME = 5;      // 운영시간
    private List<Future> employeeFutures = new ArrayList();

    /**
     * 초기화
     */
    public void businessPreparation() {
        employees.add(new ClerkEmployee("Eric", 2));
        employees.add(new BaristaEmployee("Tom", 3));
        employees.add(new BaristaEmployee("Chicol", 3));
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
     * TODO 영업 종료 후 판매 내역 조회
     * */
    public void closingBusiness() {
    }
    /**
     * TODO 정산
     * */
    public void calculate() {
    }
}
