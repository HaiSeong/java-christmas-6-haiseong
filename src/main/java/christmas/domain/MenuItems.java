package christmas.domain;

import christmas.enums.MenuItem;
import java.util.HashMap;
import java.util.Map;

public enum MenuItems {
    MENU_ITEMS;

    private static final String MENU_ITEM_NOT_FOUND_MESSAGE = "존재하지 않는 메뉴입니다.";

    private final Map<String, MenuItem> items;

    MenuItems() {
        items = new HashMap<>();
        initializeItems();
    }

    private void initializeItems() {
        for (MenuItem menuItem : MenuItem.values()) {
            items.put(menuItem.getName(), menuItem);
        }
    }

    public MenuItem getMenuItem(String itemName) {
        MenuItem menuItem = items.get(itemName);
        if (menuItem == null) {
            throw new IllegalArgumentException(MENU_ITEM_NOT_FOUND_MESSAGE);
        }
        return menuItem;
    }
}
