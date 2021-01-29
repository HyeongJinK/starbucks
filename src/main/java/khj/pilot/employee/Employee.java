package khj.pilot.employee;

import khj.pilot.order.Order;
import khj.pilot.product.Menu;

import java.util.List;

public interface Employee {
    void working(List<Order> order, Menu menu) throws InterruptedException;
    void getLog();
    String getName();
    int getMillisProcessingTime();
    int getProcessingTime();
    EmployeeStatus getEmployeeStatus();
}
