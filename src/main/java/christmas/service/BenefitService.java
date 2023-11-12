package christmas.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.DiscountApplier;
import christmas.domain.benefit.Gift;
import christmas.domain.benefit.GiftProvider;
import java.util.List;

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

    public void processOrder(Order order) {
        List<Discount> discounts = discountApplier.applyDiscounts(order);
        List<Gift> gifts = giftProvider.offerGifts(order);
    }
}
