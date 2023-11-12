package christmas.domain.benefit;

import christmas.domain.Order;
import java.util.List;

public class GiftProvider {
    private static final int NO_GIFT = 0;

    private final List<GiftPolicy> giftPolicies;

    public GiftProvider(List<GiftPolicy> giftPolicies) {
        this.giftPolicies = giftPolicies;
    }

    public List<Gift> offerGifts(Order order) {
        return giftPolicies.stream()
                .map(giftPolicy -> giftPolicy.offerGift(order))
                .filter(gift -> gift.quantity() > NO_GIFT)
                .toList();
    }
}
