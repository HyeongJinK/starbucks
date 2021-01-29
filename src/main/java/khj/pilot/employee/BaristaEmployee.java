package khj.pilot.employee;

import khj.pilot.order.Order;
import khj.pilot.order.OrderType;
import khj.pilot.product.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class BaristaEmployee extends CommonEmployee {
    Logger log = LoggerFactory.getLogger(BaristaEmployee.class);
    public BaristaEmployee(String name, int processingTime) {
        super(name, processingTime);
    }

    @Override
    public synchronized void working(List<Order> orders, Menu menu) throws InterruptedException {
        Optional<Order> order = orders.stream()
                .filter(o -> o.getOrderType().equals(OrderType.ON_ORDER))
                .findFirst();

        employeeStatus = EmployeeStatus.Working;

        if (order.isPresent()) {
            order.get().making();
            log.info("[" + this.name+ "]주문이 들어옴");
            Thread.sleep(getMillisProcessingTime());    // 작업시간
            order.get().complete();
            log.info("[" + this.name+ "]주문 처리 완료");
        }

        employeeStatus = EmployeeStatus.Waiting;
    }

    @Override
    public void getLog() {

    }
}
