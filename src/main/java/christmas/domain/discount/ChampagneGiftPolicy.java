package christmas.domain.discount;

import christmas.domain.Order;
import christmas.enums.MenuItem;

public class ChampagneGiftPolicy implements GiftPolicy {
    private static final int MINIMUM_PURCHASE_FOR_GIFT = 100_000;
    private static final int GIFT_QUANTITY = 1;
    private static final int NO_GIFT_QUANTITY = 0;

    @Override
    public GiftEntry offerGift(Order order) {
        if (order.getTotalPrice() >= MINIMUM_PURCHASE_FOR_GIFT) {
            return new GiftEntry(MenuItem.CHAMPAGNE, GIFT_QUANTITY);
        }
        return new GiftEntry(MenuItem.CHAMPAGNE, NO_GIFT_QUANTITY);
    }
}
