package study.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.MenuItems;
import christmas.enums.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuItemsTest {

    @Test
    @DisplayName("정상적인 메뉴 이름으로 MenuItem을 찾을 수 있어야 한다.")
    void whenValidName_thenMenuItemShouldBeFound() {
        MenuItem menuItem = MenuItems.MENU_ITEMS.getMenuItem("양송이수프");

        assertThat(menuItem).isEqualTo(MenuItem.MUSHROOM_SOUP);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 이름으로 요청하면 예외가 발생해야 한다.")
    void whenInvalidName_thenExceptionShouldBeThrown() {
        String invalidMenuName = "마라탕";
        assertThatThrownBy(() -> MenuItems.MENU_ITEMS.getMenuItem(invalidMenuName)).isInstanceOf(
                IllegalArgumentException.class);
    }
}
