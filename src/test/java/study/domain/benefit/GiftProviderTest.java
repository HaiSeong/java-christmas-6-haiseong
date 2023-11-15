package study.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.benefit.Gift;
import christmas.domain.benefit.GiftProvider;
import christmas.enums.MenuItem;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class GiftProviderTest {

    private GiftProvider giftProvider;
    private Order order;

    @BeforeEach
    void setUp() {
        giftProvider = GiftProvider.createGiftProvider();
    }

    @Test
    @DisplayName("주문이 선물 조건을 충족할 경우 적절한 선물을 제공")
    void offerGifts_10만원_이상_주문() {
        order = new Order(Map.of(MenuItem.T_BONE_STEAK, 3));

        List<Gift> gifts = giftProvider.offerGifts(order);

        assertThat(gifts).contains(new Gift("증정 이벤트", MenuItem.CHAMPAGNE, 1));
    }

    @Test
    @DisplayName("주문이 선물 조건을 충족하지 않을 경우 선물을 제공하지 않음")
    void offerGifts_10만원_미만_주문() {
        order = new Order(Map.of(MenuItem.SEAFOOD_PASTA, 1));

        List<Gift> gifts = giftProvider.offerGifts(order);

        assertThat(gifts).isEmpty();
    }
}
