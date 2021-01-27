package khj.pilot.employee;

import khj.pilot.Order;

import java.util.List;

public class BaristaEmployee extends CommonEmployee {
    public BaristaEmployee(String name, int processingTime) {
        super(name, processingTime);
    }

    @Override
    public void working(List<Order> order) {

    }

    @Override
    public void getLog() {

    }
}
