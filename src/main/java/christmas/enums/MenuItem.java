package christmas.enums;

public enum MenuItem {
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, Category.MAIN),
    BBQ_RIBS("바비큐립", 54_000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),

    ZERO_COLA("제로콜라", 3_000, Category.DRINK),
    RED_WINE("레드와인", 60_000, Category.DRINK),
    CHAMPAGNE("샴페인", 25_000, Category.DRINK);

    private final String name;
    private final int price;
    private final Category category;

    MenuItem(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
