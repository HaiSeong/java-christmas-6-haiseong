package study.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.enums.MenuItem;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    @DisplayName("정상적인 주문이 생성되는지 확인")
    void Order_생성_정상적인_경우() {
        Map<MenuItem, Integer> items = new HashMap<>();
        items.put(MenuItem.MUSHROOM_SOUP, 1);
        items.put(MenuItem.T_BONE_STEAK, 1);

        Order order = new Order(items);

        assertThat(order).isInstanceOf(Order.class);
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외 발생을 확인")
    void Order_빈_주문() {
        Map<MenuItem, Integer> items = new HashMap<>();

        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외 발생을 확인")
    void Order_생성_음료만_주문() {
        Map<MenuItem, Integer> items = new HashMap<>();
        items.put(MenuItem.ZERO_COLA, 3);

        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문 항목의 최대 개수를 초과할 경우 예외 발생을 확인")
    void Order_생성_너무_많은_양의_주문_항목() {
        Map<MenuItem, Integer> items = new HashMap<>();
        items.put(MenuItem.T_BONE_STEAK, 21);

        assertThatThrownBy(() -> new Order(items)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 최대 20개까지만 주문할 수 있습니다.");
    }
}
