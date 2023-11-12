package study.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.DiscountPolicy;
import christmas.domain.benefit.WeekendDiscountPolicy;
import christmas.enums.MenuItem;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountPolicyTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(Map.of(MenuItem.T_BONE_STEAK, 1, MenuItem.CHOCOLATE_CAKE, 1, MenuItem.ICE_CREAM, 2));
    }

    @Test
    @DisplayName("주말에 할인이 올바르게 적용되는지 테스트")
    void applyDiscountBeforeChristmas() {
        Date weekend = new Date(2);
        DiscountPolicy policy = new WeekendDiscountPolicy(weekend);

        Discount discount = policy.applyDiscount(order);

        assertThat(discount.discountAmount()).isEqualTo(2023);
    }

    @Test
    @DisplayName("평일에 할인이 적용되지 않는지 테스트")
    void applyDiscountAfterChristmas() {
        Date weekday = new Date(3);
        DiscountPolicy policy = new WeekendDiscountPolicy(weekday);

        Discount discount = policy.applyDiscount(order);

        assertThat(discount.discountAmount()).isEqualTo(0);
    }
}
