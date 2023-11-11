package study.domain.benefit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Order;
import christmas.domain.benefit.ChampagneGiftPolicy;
import christmas.domain.benefit.GiftEntry;
import christmas.domain.benefit.GiftPolicy;
import christmas.enums.MenuItem;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ChampagneGiftPolicyTest {

    private Map<MenuItem, Integer> items;

    @BeforeEach
    void setUp() {
        items = new HashMap<>();
    }

    @Test
    @DisplayName("총 가격이 10만원이상이면 샴페인을 제공한다.")
    void offerChampagneGift_10만원이_넘는_경우() {
        items.put(MenuItem.T_BONE_STEAK, 5);

        Order order = new Order(items);
        GiftPolicy giftPolicy = new ChampagneGiftPolicy();

        GiftEntry gift = giftPolicy.offerGift(order);

        assertThat(gift.getKey()).isEqualTo(MenuItem.CHAMPAGNE);
        assertThat(gift.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("총 가격이 10만원 미만이면 샴페인을 제공하지 않는다..")
    void offerChampagneGift_10만원이_넘지_않는_경우() {
        items.put(MenuItem.T_BONE_STEAK, 1);

        Order order = new Order(items);
        GiftPolicy giftPolicy = new ChampagneGiftPolicy();

        GiftEntry gift = giftPolicy.offerGift(order);

        assertThat(gift.getValue()).isEqualTo(0);
    }
}
