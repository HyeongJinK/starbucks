package khj.pilot.employee;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class CommonEmployee implements Employee{
    protected String name;
    protected EmployeeStatus employeeStatus;
    protected int processingTime;
    protected List<EmployeeLog> employeeLogs = new ArrayList<>();

    protected CommonEmployee(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
        this.employeeStatus = EmployeeStatus.Waiting;
    }

    @Override
    public int getMillisProcessingTime() {
        return processingTime * 1000;
    }
}
