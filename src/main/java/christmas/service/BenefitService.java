package christmas.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.DiscountApplier;
import christmas.domain.benefit.Gift;
import christmas.domain.benefit.GiftProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Gift> getGifts(Order order) {
        return giftProvider.offerGifts(order);
    }

    public Map<String, Integer> getBenefits(Order order) {
        Map<String, Integer> benefits = new HashMap<>();

        List<Discount> discounts = discountApplier.applyDiscounts(order);
        discounts.forEach(discount -> benefits.put(discount.policyName(), discount.discountAmount()));

        List<Gift> gifts = getGifts(order);
        gifts.forEach(gift -> benefits.put(gift.policyName(), gift.calculateTotalPrice()));

        return benefits;
    }

    public int getTotalBenefitAmount(Order order) {
        return getBenefits(order).values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
