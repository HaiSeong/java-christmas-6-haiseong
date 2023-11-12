package christmas.domain.benefit;

import christmas.domain.Order;
import java.util.List;

public class DiscountApplier {
    private static final int NO_DISCOUNT = 0;

    private final List<DiscountPolicy> discountPolicies;

    public DiscountApplier(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public List<Discount> applyDiscounts(Order order) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.applyDiscount(order))
                .filter(discount -> discount.discountAmount() > NO_DISCOUNT)
                .toList();
    }
}
