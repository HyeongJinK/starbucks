package khj.pilot;

import com.sun.tools.corba.se.idl.constExpr.Or;
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
    Logger log = LoggerFactory.getLogger(this.getClass());
    private List<Employee> employees = new ArrayList<>();      // 직원
    private List<Order> orders = new ArrayList<>();
    private final long OPERATING_TIME = 10;      // 운영시간

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
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future future = executor.submit(() -> {
            while(true) {
                Thread.sleep(1000);
                log.info("ing...");
            }
        });

        try {
            future.get(OPERATING_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
            e.printStackTrace();
        }
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
