package christmas.domain.benefit;

import christmas.domain.Date;
import christmas.domain.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "크리스마스 디데이 할인";
    private static final int DISCOUNT_START_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT_PER_DAY = 100;
    public static final int START_DAY = 1;

    private final Date date;

    public ChristmasDiscountPolicy(Date date) {
        this.date = date;
    }

    @Override
    public Discount applyDiscount(Order order) {
        if (date.isBeforeChristmas() && isAboveMinimumAmount(order)) {
            int discountAmount = DISCOUNT_START_AMOUNT + (date.getDay() - START_DAY) * DISCOUNT_INCREMENT_PER_DAY;
            return new Discount(POLICY_NAME, discountAmount);
        }
        return new Discount(POLICY_NAME, 0);
    }
}
