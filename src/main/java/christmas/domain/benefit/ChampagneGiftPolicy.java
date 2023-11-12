package christmas.domain.benefit;

import christmas.domain.Order;
import christmas.enums.MenuItem;

public class ChampagneGiftPolicy implements GiftPolicy {
    private static final String POLICY_NAME = "증정 이벤트";
    private static final int MINIMUM_PURCHASE_FOR_GIFT = 100_000;
    private static final int GIFT_QUANTITY = 1;
    private static final int NO_GIFT_QUANTITY = 0;

    @Override
    public Gift offerGift(Order order) {
        if (order.getTotalPrice() >= MINIMUM_PURCHASE_FOR_GIFT) {
            return new Gift(POLICY_NAME, MenuItem.CHAMPAGNE, GIFT_QUANTITY);
        }
        return new Gift(POLICY_NAME, MenuItem.CHAMPAGNE, NO_GIFT_QUANTITY);
    }
}
