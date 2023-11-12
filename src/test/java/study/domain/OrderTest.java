package study.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.enums.Category;
import christmas.enums.MenuItem;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderTest {

    private Map<MenuItem, Integer> items;

    @BeforeEach
    void setUp() {
        items = new HashMap<>();
    }

    @Test
    @DisplayName("정상적인 주문이 생성되는지 확인")
    void Order_생성_정상적인_경우() {
        items.put(MenuItem.MUSHROOM_SOUP, 1);
        items.put(MenuItem.T_BONE_STEAK, 1);

        Order order = new Order(items);

        assertThat(order).isInstanceOf(Order.class);
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외 발생을 확인")
    void Order_빈_주문() {
        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 주문입니다.");
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외 발생을 확인")
    void Order_1개_미만_수_주문() {
        assertThatThrownBy(() -> new Order(Map.of(MenuItem.MUSHROOM_SOUP, 0, MenuItem.SEAFOOD_PASTA, -1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 미만인 수량이 있습니다.");
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외 발생을 확인")
    void Order_생성_음료만_주문() {
        items.put(MenuItem.ZERO_COLA, 3);

        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문 항목의 최대 개수를 초과할 경우 예외 발생을 확인")
    void Order_생성_너무_많은_양의_주문_항목() {
        items.put(MenuItem.T_BONE_STEAK, 21);

        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    static Stream<Arguments> categoryItemCountProvider() {
        return Stream.of(
                Arguments.of(Category.APPETIZER, 0),
                Arguments.of(Category.MAIN, 2),
                Arguments.of(Category.DESSERT, 1),
                Arguments.of(Category.DRINK, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("categoryItemCountProvider")
    @DisplayName("각 카테고리 별 주문항목 수를 확인")
    void countItemsByCategory(Category category, int expectedCount) {
        items.put(MenuItem.T_BONE_STEAK, 1);
        items.put(MenuItem.BBQ_RIBS, 1);
        items.put(MenuItem.CHOCOLATE_CAKE, 1);
        items.put(MenuItem.ZERO_COLA, 2);

        Order order = new Order(items);

        assertThat(order.countItemsByCategory(category)).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("모든 주문항목 가격 총합 확인")
    void getTotalPrice() {
        items.put(MenuItem.TAPAS, 1);
        items.put(MenuItem.SEAFOOD_PASTA, 1);
        items.put(MenuItem.CHRISTMAS_PASTA, 1);

        Order order = new Order(items);

        assertThat(order.getTotalPrice()).isEqualTo(65_500);
    }
}
