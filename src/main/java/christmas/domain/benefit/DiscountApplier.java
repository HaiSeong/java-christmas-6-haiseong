package christmas.domain.benefit;

import christmas.domain.Date;
import christmas.domain.Order;
import java.util.List;

public class DiscountApplier {
    private static final int NO_DISCOUNT = 0;

    private final List<DiscountPolicy> discountPolicies;

    private DiscountApplier(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public static DiscountApplier createDiscountApplier(Date date) {
        return new DiscountApplier(List.of(
                new ChristmasDiscountPolicy(date),
                new WeekdayDiscountPolicy(date),
                new WeekendDiscountPolicy(date),
                new SpecialDiscountPolicy(date)
        ));
    }

    public List<Discount> applyDiscounts(Order order) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.applyDiscount(order))
                .filter(discount -> discount.discountAmount() > NO_DISCOUNT)
                .toList();
    }
}
