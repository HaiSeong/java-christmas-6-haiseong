package christmas.domain.discount;

import christmas.domain.Order;

public interface GiftPolicy {
    GiftEntry offerGift(Order order);
}
