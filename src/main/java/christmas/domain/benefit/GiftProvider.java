package christmas.domain.benefit;

import christmas.domain.Order;
import java.util.List;

public class GiftProvider {
    private static final int NO_GIFT = 0;

    private final List<GiftPolicy> giftPolicies;

    private GiftProvider(List<GiftPolicy> giftPolicies) {
        this.giftPolicies = giftPolicies;
    }

    public static GiftProvider createGiftProvider() {
        List<GiftPolicy> giftPolicies = List.of(new ChampagneGiftPolicy());
        return new GiftProvider(giftPolicies);
    }

    public List<Gift> offerGifts(Order order) {
        return giftPolicies.stream()
                .map(giftPolicy -> giftPolicy.offerGift(order))
                .filter(gift -> gift.quantity() > NO_GIFT)
                .toList();
    }
}
