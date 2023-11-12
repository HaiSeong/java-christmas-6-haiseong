package study.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.benefit.ChristmasDiscountPolicy;
import christmas.domain.benefit.DiscountApplier;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.SpecialDiscountPolicy;
import christmas.domain.benefit.WeekdayDiscountPolicy;
import christmas.domain.benefit.WeekendDiscountPolicy;
import christmas.enums.MenuItem;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountApplierTest {

    private DiscountApplier discountApplier;
    private Order order;


    @BeforeEach
    void setUp() {
        order = new Order(Map.of(MenuItem.MUSHROOM_SOUP, 1, MenuItem.T_BONE_STEAK, 2,
                MenuItem.CHOCOLATE_CAKE, 1, MenuItem.ZERO_COLA, 2));
    }

    @Test
    @DisplayName("12월 4일 : 크리스마스 디데이 할인, 평일 할인 제공")
    void applyDiscounts_12월_4일() {
        setDate(4);

        List<Discount> discounts = discountApplier.applyDiscounts(order);

        assertThat(discounts).containsExactly(
                new Discount("크리스마스 디데이 할인", 1300),
                new Discount("평일 할인", 2023)
        );
    }

    @Test
    @DisplayName("12월 8일 : 크리스마스 디데이 할인, 주말 할인 제공")
    void applyDiscounts_12월_8일() {
        setDate(8);

        List<Discount> discounts = discountApplier.applyDiscounts(order);

        assertThat(discounts).containsExactly(
                new Discount("크리스마스 디데이 할인", 1700),
                new Discount("주말 할인", 2023 * 2)
        );
    }

    @Test
    @DisplayName("12월 25일 : 크리스마스 디데이 할인, 평일 할인 제공, 특별 할인")
    void applyDiscounts_12월_25일() {
        setDate(25);

        List<Discount> discounts = discountApplier.applyDiscounts(order);

        assertThat(discounts).containsExactly(
                new Discount("크리스마스 디데이 할인", 3400),
                new Discount("평일 할인", 2023),
                new Discount("특별 할인", 1000)
        );
    }

    @Test
    @DisplayName("12월 26일 : 평일 할인 제공")
    void applyDiscounts_12월_26일() {
        setDate(26);

        List<Discount> discounts = discountApplier.applyDiscounts(order);

        assertThat(discounts).containsExactly(
                new Discount("평일 할인", 2023)
        );
    }

    @Test
    @DisplayName("12월 25일 : 10,000원 미만 주문")
    void applyDiscounts_10000원_미만_주문() {
        setDate(26);
        order = new Order(Map.of(MenuItem.MUSHROOM_SOUP, 1));

        List<Discount> discounts = discountApplier.applyDiscounts(order);

        assertThat(discounts).isEmpty();
    }

    void setDate(int day) {
        Date date = new Date(day);
        discountApplier = DiscountApplier.createDiscountApplier(date);
    }
}
