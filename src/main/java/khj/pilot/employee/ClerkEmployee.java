package khj.pilot.employee;

import khj.pilot.exception.EmptyProductException;
import khj.pilot.exception.OutOfStockException;
import khj.pilot.product.Menu;
import khj.pilot.product.Product;
import khj.pilot.store.Desk;
import khj.pilot.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ClerkEmployee extends CommonEmployee {
    Logger log = LoggerFactory.getLogger(ClerkEmployee.class);
    Scanner sc = new Scanner(System.in);

    public ClerkEmployee(String name, int processingTime) {
        super(name, processingTime);
    }

    @Override
    public synchronized void working(Store store, Menu menu) throws InterruptedException {
        if (store.checkEnterCustomer()) {
            Desk desk = new Desk();
            this.employeeStatus = EmployeeStatus.Working;
            while (true) {
                Product product = inputOrder(menu);
                if (workOrderComplete(store, desk, product)) break;
            }
        }
    }

    private boolean workOrderComplete(Store store, Desk desk, Product product) throws InterruptedException {
        if (product != null) {
            log.info(product.getName() + "주문하셨습니다.");
            Thread.sleep(getMillisProcessingTime());    // 작업시간
            log.info("접수완료");
            store.addOrder(desk.newOrder()
                    .addProduct(product)
                    .completeOrder()
            );
            setLog(new EmployeeLog(LocalDateTime.now(), product.getName()));
            this.employeeStatus = EmployeeStatus.Waiting;
            return true;
        }
        return false;
    }

    private Product inputOrder(Menu menu) {
        String orderNum;
        System.out.println("[" + this.name + "]상품 번호를 입력하세요  :");
        orderNum = sc.nextLine();

        try {
            Product product = menu.getProduct(Integer.parseInt(orderNum));
            return product;
        } catch (EmptyProductException | OutOfStockException e) {
            log.info(e.getMessage());
            return null;
        }
    }
}