package RoniAndMax;

public class Seller extends User implements Comparable<Seller> {
    private Product[] products;
    private int productsSize;

    public Seller(String username,String password) {
        super(username,password);
        productsSize = 0;
        products = new Product[2];
    }

    public Seller(Seller other) {
        super(other.getUsername(),other.getPassWord());
        this.productsSize = other.getProductsSize();
        this.products = other.cloneProductArray();
    }

    public Product[] cloneProductArray() {
        Product[] newProductArray = new Product[products.length];
        for (int i = 0; i < productsSize; i++) {
            newProductArray[i] = new Product(products[i]);
        }
        return newProductArray;
    }

    public int getProductsSize() {
        return productsSize;
    }

    public void addNewProduct(Product product) {
        validateProductArray();
        products[productsSize]=product;
        products[productsSize].setSeller(this);
        productsSize++;
    }

    public void validateProductArray() {
        if(productsSize < products.length) return;

        Product[] newProducts = new Product[products.length * 2];
        for(int i = 0; i < productsSize; i++) {
            newProducts[i] = products[i];
        }
        products = newProducts;
    }

    public Product getProductAtIndex(int index) {
        return products[index];
    }

    public String getProducts() {
        String productsStr = "";
        for (int i = 0; i < productsSize; i++) {
            productsStr += "    " + (i+1) + " - " + products[i].toString() + "\n";
        }
        return productsStr;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\n    Product list: " + "\n" + getProducts() +
                "    number of products: " + productsSize + "\n";
    }

    @Override
    public int compareTo(Seller o) {
        return Integer.compare(o.productsSize, productsSize);
    }
}