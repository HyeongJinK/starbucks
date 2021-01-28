package khj.pilot.order;

public enum OrderType {
    ON_ORDER("주문 중"), MAKING("만드는 중"), COMPLETE("완료");

    private String name;

    OrderType(String name) {
        this.name = name;
    }
}
