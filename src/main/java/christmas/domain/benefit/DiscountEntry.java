package christmas.domain.benefit;

import java.util.Map.Entry;

public class DiscountEntry implements Entry<String, Integer> {
    private final String polocyName;
    private Integer discountAmount;

    public DiscountEntry(String polocyName, Integer discountAmount) {
        this.polocyName = polocyName;
        this.discountAmount = discountAmount;
    }

    @Override
    public String getKey() {
        return polocyName;
    }

    @Override
    public Integer getValue() {
        return discountAmount;
    }

    @Override
    public Integer setValue(Integer discountAmount) {
        Integer oldAmount = this.discountAmount;
        this.discountAmount = discountAmount;
        return oldAmount;
    }
}
