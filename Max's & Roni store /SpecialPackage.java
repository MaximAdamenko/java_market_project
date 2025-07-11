package RoniAndMax;

public class SpecialPackage extends Product{

    public SpecialPackage(String name, double price, eCategory category) {
        super(name, price, category);
    }

    public SpecialPackage(Product other) {
        super(other);
    }

    public void packaging(double packagePrice) {
        setPrice(getPrice() + packagePrice);
    }
}