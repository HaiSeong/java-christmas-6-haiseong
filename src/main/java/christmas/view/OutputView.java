package christmas.view;


import java.util.Map;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s\n";
    private static final String PREVIEW_EVENT_BENEFITS_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
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

    public void println() {
        System.out.println();
    }

    public void printError(String message) {
        System.out.printf(ERROR_MESSAGE_FORMAT, message);
    }

    public void printPreviewEventBenefitsMessage() {
        System.out.println(PREVIEW_EVENT_BENEFITS_MESSAGE);
    }

    public void printOrderedMenuItems(String orderedMenuItems) {
        System.out.printf(ORDERED_MENU_ITEMS_MESSAGE_FORMAT, orderedMenuItems);
    }

    public void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        System.out.printf(TOTAL_AMOUNT_BEFORE_DISCOUNT_MESSAGE_FORMAT, totalAmountBeforeDiscount);
    }

    public void printGiftedItems(Map<String, Integer> gifts) {
        StringBuilder giftedItems = new StringBuilder();
        gifts.forEach((giftName, quantity) ->
                giftedItems.append(String.format(GIFTED_ITEM_FORMAT, giftName, quantity))
        );
        if (giftedItems.isEmpty()) {
            giftedItems.append(NO_BENEFIT);
        }
        String result = giftedItems.toString();
        System.out.printf(GIFTED_ITEMS_MESSAGE_FORMAT, result.trim());
    }

    public void printBenefitDetails(Map<String, Integer> benefits) {
        StringBuilder benefitDetails = new StringBuilder();
        benefits.forEach((name, amount) ->
                benefitDetails.append(String.format(BENEFIT_DETAIL_FORMAT, name, amount))
        );
        if (benefitDetails.isEmpty()) {
            benefitDetails.append(NO_BENEFIT);
        }
        String result = benefitDetails.toString();
        System.out.printf(BENEFIT_DETAILS_MESSAGE_FORMAT, result.trim());
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.printf(TOTAL_BENEFIT_AMOUNT_MESSAGE_FORMAT, totalBenefitAmount * NEGATIVE_MULTIPLIER);
    }

    public void printExpectedPaymentAfterDiscount(int expectedPaymentAfterDiscount) {
        System.out.printf(EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE_FORMAT, expectedPaymentAfterDiscount);
    }

    public void printDecemberEventBadge(String decemberEventBadge) {
        System.out.printf(DECEMBER_EVENT_BADGE_MESSAGE_FORMAT, decemberEventBadge);
    }
}

