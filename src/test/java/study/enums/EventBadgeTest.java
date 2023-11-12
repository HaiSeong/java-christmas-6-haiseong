package study.enums;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventBadgeTest {
    @ParameterizedTest
    @DisplayName("주어진 금액에 따른 배지 테스트")
    @CsvSource({
            "0, NO",
            "4999, NO",
            "5000, STAR",
            "9999, STAR",
            "10000, TREE",
            "19999, TREE",
            "20000, SANTA",
            "25000, SANTA"
    })
    void getBadgeForAmount(int amount, EventBadge expectedBadge) {
        assertThat(EventBadge.getBadgeForAmount(amount)).isEqualTo(expectedBadge);
    }

}
