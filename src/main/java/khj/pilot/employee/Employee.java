package khj.pilot.employee;

import khj.pilot.product.Menu;
import khj.pilot.store.Store;

import java.util.List;

public interface Employee {
    void working(Store store, Menu menu) throws InterruptedException;
    List<EmployeeLog> getLog();
    String getName();
    int getMillisProcessingTime();
    int getProcessingTime();
    EmployeeStatus getEmployeeStatus();
}
