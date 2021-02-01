package khj.pilot;

import khj.pilot.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App 
{
    static Logger log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        Store store = new Store();

        Scanner sc = new Scanner(System.in);
        String status = "";
        while (!isQuit(status)) {   // l[log]: 직원로그,
            log.info("입력(e[enter]: 영업시작, p[print]: 매출조회, q[quit]: 종료  :" );
            status = sc.nextLine();
            inputProcess(store, status);
        }
        System.exit(0);
    }

    private static void inputProcess(Store store, String status) {
        if (isEnter(status)) {
            store.start();
        } else if (isPrint(status)) {
            store.printCalculate();
        } else if (isLog(status)) {
            // TODO 이름 입력
            store.drawEmployeeLogByName("");
        } else {
            log.info("잘못된 입력입니다.");
        }
    }

    public static boolean isStatusCheck(String status, String ...checkStr) {
        status = status.toLowerCase();
        for (String str: checkStr) {
            if (status.equals(str)) return true;
        }
        return false;
    }

    public static boolean isEnter(String status) {
        return isStatusCheck(status, "e", "enter");
    }

    public static boolean isQuit(String status) {
        return isStatusCheck(status, "q", "quit");
    }

    public static boolean isPrint(String status) {
        return isStatusCheck(status, "p", "print");
    }

    public static boolean isLog(String status) {
        return isStatusCheck(status, "l", "log");
    }
}