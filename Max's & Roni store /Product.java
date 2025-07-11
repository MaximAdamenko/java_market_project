package RoniAndMax;


public class Product {
    public enum eCategory {Kids, Electricity, Desk, Cloth};

    private static int numberOfProducts = 0;
    private String name;
    private double price;
    private eCategory category;
    private final String serialNumber;
    private Seller seller;

    public Product(String name, double price, eCategory category) {
        setName(name);
        setPrice(price);
        setCategory(category);
        numberOfProducts ++;
        serialNumber = setSerialNumber(numberOfProducts);
    }

    public Product(Product other) {
        this.name = other.getName();
        this.price = other.getPrice();
        this.category = other.getCategory();
        this.seller = other.getSeller();
        this.serialNumber = other.serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public eCategory getCategory() {
        return category;
    }

    public static int getNumberOfProducts() {
        return numberOfProducts;
    }

    public boolean setName(String name) {
        this.name = name.substring(0,1).toUpperCase() + name.substring(1);
        return true;
    }

    public boolean setPrice(double price) {
        this.price = price;
        return true;
    }

    public boolean setCategory(eCategory category) {
        this.category = category;
        return true;
    }

    public boolean setSeller(Seller seller) {
        this.seller = seller;
        return true;
    }

    public String setSerialNumber(int num) {
        int constantNum = 6;
        float newNum = (float) num;
        int count = 0;
        String Serial = "";

        while(newNum >= 1) {
            newNum /=10;
            count ++;
        }

        if(constantNum <= count) {
            constantNum = count + 2;
        }

        count = constantNum - count;

        for (int i = 0; i < count; i++) {
            Serial += "0";
        }

        Serial += num;
        return Serial;
    }

    @Override
    public String toString() {
        return
                "name: " + name +
                        "\n        price: " + price +
                        "\n        Serial Number: " + serialNumber;
    }
}