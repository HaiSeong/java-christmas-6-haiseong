package study.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.discount.DiscountEntry;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.SpecialDiscountPolicy;
import christmas.enums.MenuItem;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountPolicyTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(Map.of(MenuItem.T_BONE_STEAK, 1, MenuItem.CHOCOLATE_CAKE, 1, MenuItem.ICE_CREAM, 2));
    }

    @Test
    @DisplayName("스페셜 데이에 할인이 올바르게 적용되는지 테스트")
    void applyDiscountBeforeChristmas() {
        Date specialDay = new Date(25);
        DiscountPolicy policy = new SpecialDiscountPolicy(specialDay);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("스페셜 데이가 아닌 날에 할인이 적용되지 않는지 테스트")
    void applyDiscountAfterChristmas() {
        Date ordinalDay = new Date(26);
        DiscountPolicy policy = new SpecialDiscountPolicy(ordinalDay);

        DiscountEntry discount = policy.applyDiscount(order);

        assertThat(discount.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("스페셜 데이지만 최소금액을 넘지 못하면 할인 적용되는지 않아야함")
    void applyDiscount_최소_금액_미만() {
        Date specialDay = new Date(25);
        DiscountPolicy policy = new SpecialDiscountPolicy(specialDay);

        DiscountEntry discount = policy.applyDiscount(new Order(Map.of(MenuItem.MUSHROOM_SOUP, 1)));

        assertThat(discount.getValue()).isEqualTo(0);
    }
}
