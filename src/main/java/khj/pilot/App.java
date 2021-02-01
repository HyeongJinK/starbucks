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
        while (!isQuit(status)) {
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
}


/**
 * - 주문 받기
 * - 정산 (총 매출 계산)
 * - 음료 제조 및 전달
 * - 영업 종료 후 판매 내역 조회(출력)
 * - 멀티스레딩(직원)
 *     - 스레드 이름에 직원 이름을 넣으면 로그를 통해 어느 직원이 무슨 작업을 했는지 쉽게 알 수 있습니다.
 *     - 이 매장에는 3명의 직원이 있습니다.
 *         - 주문 받는 직원 1명과 (주문 받는 시간 2초 소요, 직원명: Eric)
 *         - 커피를 만드는 직원 2명 (커피 만드는 시간 3초 소요, 직원명: Tom, Chicol)
 *             - 커피를 만들고 나서 손님의 이름을 불러주면 끝입니다.
 * - 손님이 커피를 받아가는 부분은 구현하지 않습니다.
 * - 출력은 `System.out.println` 대신 `Slf4j` 로그를 사용하면 편리합니다.
 *
 * 스타벅스 매장 운영 조건
 *
 * - 이 매장은 5명의 손님을 받고 문을 닫습니다.
 *     - 코로나때문인지 다행히 단체 손님은 없는 것 같습니다.
 *     - 고객은 매우 바쁘기 때문에 하나의 음료만 주문 합니다.
 *     - 매우 단순한 남성 고객이라 토핑의 개념을 몰라 오리지널 메뉴만 주문 합니다.
 * - 매장은 10초만 운영 합니다.
 */

