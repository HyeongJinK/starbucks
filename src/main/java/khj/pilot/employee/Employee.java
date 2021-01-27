package khj.pilot.employee;

import khj.pilot.Order;

import java.util.List;

public interface Employee {
    void working(List<Order> order);
    void getLog();
}
