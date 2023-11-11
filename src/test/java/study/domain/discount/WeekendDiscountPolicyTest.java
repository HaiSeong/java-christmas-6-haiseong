package study.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.discount.DiscountEntry;
import christmas.domain.discount.WeekendDiscountPolicy;
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
        WeekendDiscountPolicy policy = new WeekendDiscountPolicy(weekend);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(2023 * 3);
    }

    @Test
    @DisplayName("평일에 할인이 적용되지 않는지 테스트")
    void applyDiscountAfterChristmas() {
        Date dateAfterChristmas = new Date(3);
        WeekendDiscountPolicy policy = new WeekendDiscountPolicy(dateAfterChristmas);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(0);
    }
}
