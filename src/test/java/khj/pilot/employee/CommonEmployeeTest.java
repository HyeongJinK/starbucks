package khj.pilot.employee;

import khj.pilot.product.Menu;
import khj.pilot.store.Store;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonEmployeeTest {

    @Test
    public void commonEmployee() {
        CommonEmployee ce = new CommonEmployee("test", 3) {
            @Override
            public void working(Store store, Menu menu) throws InterruptedException {

            }
        };
        assertEquals(ce.getName(), "test");
        assertEquals(ce.getEmployeeStatus(), EmployeeStatus.Waiting);
        assertEquals(ce.getProcessingTime(), 3);
        assertEquals(ce.getMillisProcessingTime(), 3000);
    }
}