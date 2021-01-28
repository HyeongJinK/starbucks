package khj.pilot.employee;

import khj.pilot.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ClerkEmployee extends CommonEmployee {
    Logger log = LoggerFactory.getLogger(ClerkEmployee.class);
    Scanner sc = new Scanner(System.in);

    public ClerkEmployee(String name, int processingTime) {
        super(name, processingTime);
    }

    @Override
    public void working(List<Order> order) throws InterruptedException {
        String orderNum = "";
        this.employeeStatus = EmployeeStatus.Working;
        System.out.print("[" + this.name+ "]상품 번호를 입력하세요  :");
        orderNum = sc.nextLine();

        Thread.sleep(getMillisProcessingTime());
        this.employeeStatus = EmployeeStatus.Waiting;
    }

    @Override
    public void getLog() {

    }
}
