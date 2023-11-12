package christmas.domain.benefit;

import christmas.domain.Order;

public interface GiftPolicy {
    Gift offerGift(Order order);
}
