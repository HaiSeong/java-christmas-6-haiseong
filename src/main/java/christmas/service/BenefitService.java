package christmas.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.DiscountApplier;
import christmas.domain.benefit.Gift;
import christmas.domain.benefit.GiftProvider;
import christmas.enums.EventBadge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitService {
    private final DiscountApplier discountApplier;
    private final GiftProvider giftProvider;

    private BenefitService(DiscountApplier discountApplier, GiftProvider giftProvider) {
        this.discountApplier = discountApplier;
        this.giftProvider = giftProvider;
    }

    public static BenefitService createBenefitService(Date date) {
        DiscountApplier discountApplier = DiscountApplier.createDiscountApplier(date);
        GiftProvider giftProvider = GiftProvider.createGiftProvider();
        return new BenefitService(discountApplier, giftProvider);
    }

    public Map<String, Integer> getGifts(Order order) {
        List<Gift> gifts = giftProvider.offerGifts(order);

        return gifts.stream()
                .collect(Collectors.toMap(Gift::getGiftName, Gift::quantity));
    }

    public Map<String, Integer> getBenefits(Order order) {
        Map<String, Integer> benefits = new HashMap<>();

        List<Discount> discounts = discountApplier.applyDiscounts(order);
        discounts.forEach(discount -> benefits.put(discount.policyName(), discount.amount()));

        List<Gift> gifts = giftProvider.offerGifts(order);
        gifts.forEach(gift -> benefits.put(gift.policyName(), gift.calculateTotalPrice()));

        return benefits;
    }

    public int getTotalBenefitAmount(Order order) {
        return getBenefits(order).values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getExpectedPaymentAfterDiscount(Order order) {
        int totalGiftPrice = giftProvider.offerGifts(order).stream().mapToInt(Gift::calculateTotalPrice).sum();
        return order.getTotalPrice() - (getTotalBenefitAmount(order) - totalGiftPrice);
    }

    public EventBadge getEventBadge(Order order) {
        return EventBadge.getBadgeForAmount(getTotalBenefitAmount(order));
    }
}
