package khj.pilot.employee;

import khj.pilot.order.Order;
import khj.pilot.product.Menu;
import khj.pilot.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaristaEmployee extends CommonEmployee {
    Logger log = LoggerFactory.getLogger(BaristaEmployee.class);
    public BaristaEmployee(String name, int processingTime) {
        super(name, processingTime);
    }

    @Override
    public synchronized void working(Store store, Menu menu) throws InterruptedException {
        employeeStatus = EmployeeStatus.Working;

        Order order = store.getOnOrderAny();

        if (order != null) {
            log.info("[" + this.name+ "]주문이 들어옴");
            Thread.sleep(getMillisProcessingTime());    // 작업시간
            order.complete();
            log.info("[" + this.name+ "]주문 처리 완료");
        }

        employeeStatus = EmployeeStatus.Waiting;
    }


}
