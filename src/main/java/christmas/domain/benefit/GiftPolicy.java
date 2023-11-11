package christmas.domain.benefit;

import christmas.domain.Order;

public interface GiftPolicy {
    GiftEntry offerGift(Order order);
}
