package christmas.enums;

import java.util.Arrays;

public enum EventBadge {
    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),
    NO(0, "없음");

    private final int benefitAmount;
    private final String badgeName;

    EventBadge(int benefitAmount, String badgeName) {
        this.benefitAmount = benefitAmount;
        this.badgeName = badgeName;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public static EventBadge getBadgeForAmount(int benefitAmount) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> benefitAmount >= eventBadge.benefitAmount)
                .findFirst()
                .orElse(NO);
    }
}
