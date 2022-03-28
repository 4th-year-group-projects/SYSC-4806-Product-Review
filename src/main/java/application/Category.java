package application;

public enum Category {
    TECH("Electronics, Computers, & Office"),
    CLOTHING("Apparel"),
    COSMETICS("Beauty & Personal Care"),
    HOUSE("Health & Household"),
    BOOKS("Books"),
    SPORTS("Sports & Outdoors");

    private final String categoryName;

    private Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
}
