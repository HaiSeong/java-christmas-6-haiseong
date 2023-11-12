package study.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.enums.MenuItem;
import christmas.service.ParserService;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserServiceTest {

    private final ParserService parserService = new ParserService();

    @Test
    @DisplayName("올바른 주문")
    void validOrderStringIsParsedCorrectly() {
        String orderLine = "해산물파스타-2,레드와인-1,초코케이크-1";
        Map<MenuItem, Integer> parsedOrder = parserService.parseOrder(orderLine);

        assertThat(parsedOrder).containsExactlyInAnyOrderEntriesOf(Map.of(
                MenuItem.SEAFOOD_PASTA, 2,
                MenuItem.RED_WINE, 1,
                MenuItem.CHOCOLATE_CAKE, 1
        ));
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 항목으로 파싱이 실패")
    void parsingFailsWithNonExistentMenuItem() {
        String orderLine = "없는메뉴-1";

        assertThatThrownBy(() -> parserService.parseOrder(orderLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("잘못된 주문 형식으로 인해 파싱이 실패")
    void parsingFailsWithInvalidOrderFormat() {
        String orderLine = ",해산물파스타-2,레드와인-1,,";

        assertThatThrownBy(() -> parserService.parseOrder(orderLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복된 메뉴 항목으로 인해 파싱이 실패")
    void parsingFailsWithDuplicateMenuItems() {
        String orderLine = "해산물파스타-1,해산물파스타-2";

        assertThatThrownBy(() -> parserService.parseOrder(orderLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
