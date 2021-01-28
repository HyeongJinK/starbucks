package khj.pilot.employee;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EmployeeLog {
    LocalDateTime logDate;
    String message;
}
