package christmas.domain.benefit;

import java.util.Map.Entry;

public class DiscountEntry implements Entry<String, Integer> {
    private final String policyName;
    private Integer discountAmount;

    public DiscountEntry(String policyName, Integer discountAmount) {
        this.policyName = policyName;
        this.discountAmount = discountAmount;
    }

    @Override
    public String getKey() {
        return policyName;
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
