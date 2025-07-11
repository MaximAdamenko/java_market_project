package RoniAndMax;

import java.util.Date;

public class Order {
    private Product[] products;
    private String dateOfPurchase = "";
    private int productsSize;

    public Order() {
        products = new Product[2];
        productsSize = 0;
    }

    public Order(Order other) {
        this.productsSize = other.getProductsSize();
        this.products = other.cloneProductsArray();
        this.dateOfPurchase = other.getDateOfPurchase();
    }

    public Product[] cloneProductsArray() {
        Product[] newProductArray = new Product[products.length];
        for (int i = 0; i < productsSize; i++) {
            newProductArray[i] = new Product(products[i]);
        }
        return newProductArray;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public int getProductsSize() {
        return productsSize;
    }

    public void resetDateOfPurchase() {
        this.dateOfPurchase = "";
    }

    public void addProductToCart(Product newProduct) {
        validateProductArray();
        products[productsSize] = new Product(newProduct);
        productsSize++;
    }

    public void validateProductArray() {
        if (productsSize < products.length) return;

        Product[] newProducts = new Product[products.length * 2];
        for (int i = 0; i < productsSize; i++) {
            newProducts[i] = products[i];
        }
        products = newProducts;
    }

    public void getProductByName(String name) {
        for (int i = 0; i < productsSize; i++) {
            if (products[i].getName().equals(name)) {
                System.out.println(products[i]);
                return;
            }
        }
        System.out.println("Product not found");
    }

    public void getProductByIndex(int index) {
        if (index > productsSize || index < 0) {
            System.out.println("Product not found");
            return;
        }
        System.out.println(products[index]);
    }

    public double payment() {
        double totalPrice = 0;
        for (int i = 0; i < productsSize; i++) {
            totalPrice += products[i].getPrice();
        }
        if (totalPrice == 0) {
            return 0;
        }

        Date orderDate = new Date();
        long time = (long) (orderDate.getTime() / 1000);
        int years = (int) (((time / 3600) / 24) / 365);
        int currentYear = years + 1970;
        dateOfPurchase = orderDate.getDate() + "/" + (orderDate.getMonth() + 1) + "/" + currentYear;
        return totalPrice;
    }

    public String productsToString(Product[] products) {
        String productsStr = "\n";
        for (int i = 0; i < productsSize; i++) {
            productsStr += "    " + (i + 1) + " - " + products[i].toString() + "\n";
        }
        return productsStr;
    }

    @Override
    public String toString() {
        return "\n    Order specifics:\n" +
                "    ---------------\n" +
                "    Products: " + productsToString(products) +
                "    Date Of Purchase: " + dateOfPurchase + "\n" +
                "    Number of products cart: " + productsSize;
    }
}