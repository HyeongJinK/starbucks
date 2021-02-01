package khj.pilot.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class EmployeeLog {
    LocalDateTime logDate;
    String message;
}
