package christmas.domain.benefit;

import christmas.enums.MenuItem;

public record Gift(String policyName, MenuItem menuItem, Integer quantity) {
    public int calculateTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}
