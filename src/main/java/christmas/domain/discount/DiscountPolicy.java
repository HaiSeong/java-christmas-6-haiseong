package christmas.domain.discount;

import christmas.domain.Order;

public interface DiscountPolicy {
    DiscountEntry applyDiscount(Order order);
}
