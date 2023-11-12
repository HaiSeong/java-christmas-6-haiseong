package christmas.service;

import static christmas.domain.MenuItems.MENU_ITEMS;

import christmas.enums.MenuItem;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ParserService {
    private static final String ITEM_DELIMITER = ",";
    private static final String DETAIL_DELIMITER = "-";

    public Map<MenuItem, Integer> parseOrder(String line) {
        try {
            return Arrays.stream(line.split(ITEM_DELIMITER))
                    .map(item -> item.split(DETAIL_DELIMITER))
                    .collect(Collectors.toMap(
                            itemDetails -> MENU_ITEMS.getMenuItem(itemDetails[0].trim()),
                            itemDetails -> Integer.parseInt(itemDetails[1].trim())
                    ));
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}

