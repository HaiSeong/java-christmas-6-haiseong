package christmas.domain.benefit;

import christmas.domain.Date;
import christmas.domain.Order;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "특별 할인";
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    private final Date date;

    public SpecialDiscountPolicy(Date date) {
        this.date = date;
    }

    @Override
    public Discount applyDiscount(Order order) {
        if (date.isSpecialDay() && isAboveMinimumAmount(order)) {
            return new Discount(POLICY_NAME, SPECIAL_DISCOUNT_AMOUNT);
        }
        return new Discount(POLICY_NAME, 0);
    }
}
