package khj.pilot.employee;

import khj.pilot.order.Order;
import khj.pilot.product.Menu;
import khj.pilot.product.Product;
import khj.pilot.store.Desk;
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
    public synchronized void working(List<Order> order, Menu menu) throws InterruptedException {
        Desk desk = new Desk();
        String orderNum = "";
        this.employeeStatus = EmployeeStatus.Working;
        while (true) {
            System.out.println("[" + this.name+ "]상품 번호를 입력하세요  :");
            orderNum = sc.nextLine();
            Product product = menu.getProduct(Integer.parseInt(orderNum));
            log.info(product.getName());
            if (product != null) {
                Thread.sleep(getMillisProcessingTime());    // 작업시간
                log.info("접수완료");
                order.add(
                        desk.newOrder()
                        .addProduct(product)
                        .completeOrder()
                );
                this.employeeStatus = EmployeeStatus.Waiting;
                break;
            }
        }
    }

    @Override
    public void getLog() {

    }
}
