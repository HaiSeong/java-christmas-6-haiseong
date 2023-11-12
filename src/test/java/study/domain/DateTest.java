package study.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    @DisplayName("유효한 날짜가 올바르게 처리되는지 확인")
    void validDayTest() {
        Date date = new Date(15);
        assertThat(date.getDay()).isEqualTo(15);
    }

    @Test
    @DisplayName("유효하지 않은 날짜가 예외를 발생시키는지 확인")
    void invalidDayTest() {
        assertThatThrownBy(() -> new Date(32))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주말 여부가 올바르게 확인되는지 테스트")
    void isWeekendTest() {
        Date weekendDate = new Date(1);

        assertThat(weekendDate.isWeekend()).isTrue();
    }

    @Test
    @DisplayName("주말이 아닌 날짜가 올바르게 처리되는지 확인")
    void isNotWeekendTest() {
        Date notWeekendDate = new Date(3);

        assertThat(notWeekendDate.isWeekend()).isFalse();
    }

    @Test
    @DisplayName("특별한 날 여부가 올바르게 확인되는지 테스트")
    void isSpecialDayTest() {
        Date specialDate = new Date(25);

        assertThat(specialDate.isSpecialDay()).isTrue();
    }

    @Test
    @DisplayName("특별하지 않은 날짜가 올바르게 처리되는지 확인")
    void isNotSpecialDayTest() {
        Date notSpecialDate = new Date(4);

        assertThat(notSpecialDate.isSpecialDay()).isFalse();
    }

    @Test
    @DisplayName("크리스마스 이전 날짜가 올바르게 처리되는지 확인")
    void isChristmasEveDiscountTest() {
        Date notSpecialDate = new Date(25);

        assertThat(notSpecialDate.isBeforeChristmas()).isTrue();
    }

    @Test
    @DisplayName("크리스마스 이후 날짜가 올바르게 처리되는지 확인")
    void isNotChristmasEveDiscountTest() {
        Date notSpecialDate = new Date(26);

        assertThat(notSpecialDate.isBeforeChristmas()).isFalse();
    }
}

