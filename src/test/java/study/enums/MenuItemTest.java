package study.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

import christmas.enums.Category;
import christmas.enums.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MenuItemTest {

    @Test
    @DisplayName("메뉴 아이템 이름이 잘 저장되었는지 확인해보기")
    void menuItem_양송이스프_이름_검사() {
        MenuItem menuItem = MenuItem.MUSHROOM_SOUP;

        assertThat(menuItem.getName()).isEqualTo("양송이수프");
    }

    @Test
    @DisplayName("메뉴 아이템 가격이 잘 저장되었는지 확인해보기")
    void menuItem_제로콜라_가격_검사() {
        MenuItem menuItem = MenuItem.ZERO_COLA;

        assertThat(menuItem.getPrice()).isEqualTo(3_000);
    }

    @ParameterizedTest
    @DisplayName("메뉴 아이템의 카테고리 확인해보기")
    @EnumSource(value = MenuItem.class, mode = INCLUDE, names = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
    void menuItem_카테고리_확인(MenuItem menuItem) {
        assertThat(menuItem.getCategory()).isEqualTo(Category.APPETIZER);
    }


}
