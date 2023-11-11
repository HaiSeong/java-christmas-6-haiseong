package christmas.domain;

import christmas.enums.Category;
import christmas.enums.MenuItem;
import java.util.Map;
import java.util.Set;

public class Order {
    private static final int MAX_QUANTITY_PER_ORDER = 20;
    private static final String EMPTY_ORDER_ERROR_MESSAGE = "빈 주문입니다.";
    private static final String ONLY_DRINKS_ERROR_MESSAGE = "음료만 주문할 수 없습니다.";
    private static final String MAX_QUANTITY_ERROR_MESSAGE = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";

    private final Map<MenuItem, Integer> orderItems;

    public Order(Map<MenuItem, Integer> orderItems) {
        validate(orderItems);
        this.orderItems = orderItems;
    }

    private void validate(Map<MenuItem, Integer> orderItems) {
        validateEmpty(orderItems);
        validateTotalQuantity(orderItems);
        validateNotOnlyDrinks(orderItems);
    }

    private void validateEmpty(Map<MenuItem, Integer> orderItems) {
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateTotalQuantity(Map<MenuItem, Integer> orderItems) {
        int totalQuantity = orderItems.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalQuantity > MAX_QUANTITY_PER_ORDER) {
            throw new IllegalArgumentException(MAX_QUANTITY_ERROR_MESSAGE);
        }
    }

    private void validateNotOnlyDrinks(Map<MenuItem, Integer> orderItems) {
        boolean isOnlyDrinksOrder = orderItems.keySet()
                .stream()
                .allMatch(menuItem -> menuItem.getCategory() == Category.DRINK);

        if (isOnlyDrinksOrder) {
            throw new IllegalArgumentException(ONLY_DRINKS_ERROR_MESSAGE);
        }
    }

    public int countItemsByCategory(Category category) {
        return orderItems.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
