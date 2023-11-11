package christmas.domain.benefit;

import christmas.enums.MenuItem;
import java.util.Map.Entry;

public class GiftEntry implements Entry<MenuItem, Integer> {
    private final MenuItem menuItem;
    private Integer giftCount;

    public GiftEntry(MenuItem menuItem, Integer giftCount) {
        this.menuItem = menuItem;
        this.giftCount = giftCount;
    }

    @Override
    public MenuItem getKey() {
        return menuItem;
    }

    @Override
    public Integer getValue() {
        return giftCount;
    }

    @Override
    public Integer setValue(Integer giftCount) {
        Integer oldAmount = this.giftCount;
        this.giftCount = giftCount;
        return oldAmount;
    }
}
