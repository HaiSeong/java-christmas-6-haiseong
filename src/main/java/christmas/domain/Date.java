package christmas.domain;

import java.util.Set;

public class Date {
    private static final int MINIMUM_DAY = 1;
    private static final int MAXIMUM_DAY = 31;
    private static final int CHRISTMAS_DAY = 25;
    private static final Set<Integer> WEEKEND_DAYS = Set.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);
    private static final String INVALID_DAY_RANGE_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final int day;

    public Date(int day) {
        validate(day);
        this.day = day;
    }

    private void validate(int day) {
        if (day < MINIMUM_DAY || day > MAXIMUM_DAY) {
            throw new IllegalArgumentException(INVALID_DAY_RANGE_ERROR_MESSAGE);
        }
    }

    public int getDay() {
        return day;
    }

    public boolean isBeforeChristmas() {
        return day <= CHRISTMAS_DAY;
    }

    public boolean isWeekend() {
        return WEEKEND_DAYS.contains(day);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(day);
    }
}
