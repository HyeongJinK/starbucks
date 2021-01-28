package khj.pilot.employee;

import khj.pilot.order.Order;
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
    public void working(List<Order> order) throws InterruptedException {
        while(true) {
            Thread.sleep(getMillisProcessingTime());
            log.info("test2");
        }
    }

    @Override
    public void getLog() {

    }
}
