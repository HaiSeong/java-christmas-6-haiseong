package christmas.domain.discount;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.enums.Category;

public class WeekendDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "주말 할인";
    private static final int MAIN_DISCOUNT_AMOUNT = 2023;

    private final Date date;

    public WeekendDiscountPolicy(Date date) {
        this.date = date;
    }

    @Override
    public DiscountEntry applyDiscount(Order order) {
        if (date.isWeekend()) {
            int discountAmount = order.countItemsByCategory(Category.MAIN) * MAIN_DISCOUNT_AMOUNT;
            return new DiscountEntry(POLICY_NAME, discountAmount);
        }
        return new DiscountEntry(POLICY_NAME, 0);
    }
}
