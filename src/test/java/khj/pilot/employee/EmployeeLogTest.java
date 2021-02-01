package khj.pilot.employee;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EmployeeLogTest {
    @Test
    public void employeeLog() {
        LocalDateTime now = LocalDateTime.now();
        EmployeeLog employeeLog = new EmployeeLog(now, "testMessage");

        assertEquals(employeeLog.getMessage(), "testMessage");
        assertEquals(employeeLog.getLogDate(), now);
    }

}