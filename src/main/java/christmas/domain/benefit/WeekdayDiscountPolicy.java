package christmas.domain.benefit;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.enums.Category;

public class WeekdayDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "평일 할인";
    private static final int DESERT_DISCOUNT_AMOUNT = 2023;

    private final Date date;

    public WeekdayDiscountPolicy(Date date) {
        this.date = date;
    }

    @Override
    public Discount applyDiscount(Order order) {
        if (!date.isWeekend() && isAboveMinimumAmount(order)) {
            int discountAmount = order.countItemsByCategory(Category.DESSERT) * DESERT_DISCOUNT_AMOUNT;
            return new Discount(POLICY_NAME, discountAmount);
        }
        return new Discount(POLICY_NAME, 0);
    }
}
