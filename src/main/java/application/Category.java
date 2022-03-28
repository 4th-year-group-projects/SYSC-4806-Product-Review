package application;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    TECH("Electronics, Computers, & Office"),
    CLOTHING("Apparel"),
    COSMETICS("Beauty & Personal Care"),
    HOUSE("Health & Household"),
    BOOKS("Books"),
    SPORTS("Sports & Outdoors");

    private static final Map<String, Category> BY_LABEL = new HashMap<>();

    static {
        for (Category c: values()) {
            BY_LABEL.put(c.label, c);
        }
    }

    private final String label;

    private Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Category valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    @Override
    public String toString() {
        return this.label;
    }
}
