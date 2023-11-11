package christmas.domain.discount;

import christmas.domain.Order;
import java.util.Map.Entry;

public interface DiscountPolicy {
    Entry<String, Integer> applyDiscount(Order order);
}
