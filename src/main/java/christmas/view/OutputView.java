package christmas.view;

import java.util.Map;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s\n";
    private static final String PREVIEW_EVENT_BENEFITS_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDERED_MENU_ITEMS_MESSAGE_FORMAT = "<주문 메뉴>\n%s\n";
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_MESSAGE_FORMAT = "<할인 전 총주문 금액>\n%,d원\n";
    private static final String GIFTED_ITEMS_MESSAGE_FORMAT = "<증정 메뉴>\n%s\n";
    private static final String GIFTED_ITEM_FORMAT = "%s %d개\n";
    private static final String BENEFIT_DETAILS_MESSAGE_FORMAT = "<혜택 내역>\n%s\n";
    private static final String BENEFIT_DETAIL_FORMAT = "%s: -%,d원\n";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE_FORMAT = "<총혜택 금액>\n%,d원\n";
    private static final String EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE_FORMAT = "<할인 후 예상 결제 금액>\n%,d원\n";
    private static final String DECEMBER_EVENT_BADGE_MESSAGE_FORMAT = "<12월 이벤트 배지>\n%s\n";
    private static final String NO_BENEFIT = "없음";
    private static final int NEGATIVE_MULTIPLIER = -1;

    public void printBlank() {
        System.out.println();
    }

    public void printError(String message) {
        System.out.printf(ERROR_MESSAGE_FORMAT, message);
    }

    public void printPreviewEventBenefitsMessage(int day) {
        System.out.printf(PREVIEW_EVENT_BENEFITS_MESSAGE_FORMAT, day);
        printBlank();
    }

    public void printOrderedMenuItems(String orderedMenuItems) {
        System.out.printf(ORDERED_MENU_ITEMS_MESSAGE_FORMAT, orderedMenuItems);
        printBlank();
    }

    public void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        System.out.printf(TOTAL_AMOUNT_BEFORE_DISCOUNT_MESSAGE_FORMAT, totalAmountBeforeDiscount);
        printBlank();
    }

    public void printGiftedItems(Map<String, Integer> gifts) {
        String giftItems = getFormatted(gifts, GIFTED_ITEM_FORMAT);
        System.out.printf(GIFTED_ITEMS_MESSAGE_FORMAT, giftItems.trim());
        printBlank();
    }

    public void printBenefitDetails(Map<String, Integer> benefits) {
        String benefitDetails = getFormatted(benefits, BENEFIT_DETAIL_FORMAT);
        System.out.printf(BENEFIT_DETAILS_MESSAGE_FORMAT, benefitDetails.trim());
        printBlank();
    }

    private String getFormatted(Map<String, Integer> gifts, String format) {
        StringBuilder builder = new StringBuilder();
        gifts.forEach((key, value) ->
                builder.append(String.format(format, key, value))
        );
        if (builder.isEmpty()) {
            builder.append(NO_BENEFIT);
        }
        return builder.toString();
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.printf(TOTAL_BENEFIT_AMOUNT_MESSAGE_FORMAT, totalBenefitAmount * NEGATIVE_MULTIPLIER);
        printBlank();
    }

    public void printExpectedPaymentAfterDiscount(int expectedPaymentAfterDiscount) {
        System.out.printf(EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE_FORMAT, expectedPaymentAfterDiscount);
        printBlank();
    }

    public void printEventBadge(String EventBadge) {
        System.out.printf(DECEMBER_EVENT_BADGE_MESSAGE_FORMAT, EventBadge);
    }
}

