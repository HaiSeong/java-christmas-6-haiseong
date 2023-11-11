package study.domain.discount;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.discount.ChristmasDiscountPolicy;
import christmas.domain.discount.DiscountEntry;
import christmas.domain.discount.DiscountPolicy;
import christmas.enums.MenuItem;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountPolicyTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(Map.of(MenuItem.T_BONE_STEAK, 1, MenuItem.CHRISTMAS_PASTA, 1));
    }

    @Test
    @DisplayName("크리스마스 이전에 할인이 올바르게 적용되는지 테스트")
    void applyDiscountBeforeChristmas() {
        Date dateBeforeChristmas = new Date(25);
        DiscountPolicy policy = new ChristmasDiscountPolicy(dateBeforeChristmas);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(3400);
    }

    @Test
    @DisplayName("크리스마스 이후에 할인이 적용되지 않는지 테스트")
    void applyDiscountAfterChristmas() {
        Date dateAfterChristmas = new Date(26);
        DiscountPolicy policy = new ChristmasDiscountPolicy(dateAfterChristmas);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(0);
    }
}
