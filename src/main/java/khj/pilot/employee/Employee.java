package khj.pilot.employee;

import khj.pilot.order.Order;

import java.util.List;

public interface Employee {
    void working(List<Order> order);
    void getLog();
    String getName();
    int getMillisProcessingTime();
    int getProcessingTime();
}
