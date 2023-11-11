package christmas.domain.discount;

import christmas.domain.Order;

public interface DiscountPolicy {
    int MINIMUM_AMOUNT_FOR_EVENT_PARTICIPATION = 10_000;

    DiscountEntry applyDiscount(Order order);

    default boolean isAboveMinimumAmount(Order order) {
        return order.getTotalPrice() >= MINIMUM_AMOUNT_FOR_EVENT_PARTICIPATION;
    }
}
