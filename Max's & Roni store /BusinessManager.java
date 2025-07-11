package RoniAndMax;

import java.util.Arrays;
import java.util.Objects;

public class BusinessManager {
    private Seller[] allSellers;
    private Buyer[] allBuyers;
    private final String nameOfBusiness;//
    private int sellerCount = 0;
    private int buyerCount = 0;

    public BusinessManager(String name) {
        this.nameOfBusiness = name;
        this.allSellers = new Seller[2];
        this.allBuyers = new Buyer[2];
    }

    public BusinessManager(BusinessManager other) {
        this.nameOfBusiness = other.nameOfBusiness;
        this.allBuyers = other.cloneBuyerArray();
        this.allSellers = other.cloneSellerArray();
        this.sellerCount = other.getSellerCount();
        this.buyerCount = other.getBuyerCount();
    }

    public Seller[] cloneSellerArray() {
        Seller[] newSellerArray = new Seller[allSellers.length];
        for (int i = 0; i < sellerCount; i++) {
            newSellerArray[i] = new Seller(allSellers[i]);
        }
        return newSellerArray;
    }

    public Buyer[] cloneBuyerArray() {
        Buyer[] newBuyerArray = new Buyer[allBuyers.length];
        for (int i = 0; i < buyerCount; i++) {
            newBuyerArray[i] = new Buyer(allBuyers[i]);
        }
        return newBuyerArray;
    }

    private void validateBuyerArray(){
        if(buyerCount < allBuyers.length) return;
        Buyer[] newBuyerArray = new Buyer[allBuyers.length * 2];
        for (int i = 0; i < buyerCount; i++) {
            newBuyerArray[i] = allBuyers[i];
        }
        allBuyers = newBuyerArray;
    }

    private void validateSellerArray(){
        if(sellerCount < allSellers.length) return;
        Seller[] newSellerArray = new Seller[allSellers.length * 2];
        for (int i = 0; i < sellerCount; i++) {
            newSellerArray[i] = allSellers[i];
        }
        allSellers = newSellerArray;
    }

    public void sortUserArray(User[] o, int count) {//
        User[] newArray;
        if (o instanceof Buyer[]) newArray = new Buyer[count];
        else newArray = new Seller[count];

        for (int i = 0; i < count; i++) newArray[i] = o[i];
        Arrays.sort(newArray);
        if (newArray instanceof Buyer[]) {
            for (int i = 0; i < count; i++) {
                allBuyers[i] = (Buyer) newArray[i];
            }
        } else {
            for (int i = 0; i < count; i++) {
                allSellers[i] = (Seller) newArray[i];
            }
        }
    }

    public boolean addSeller(String username, String Password) {
        validateSellerArray();

        for (int i = 0; i < sellerCount; i++) {
            if (allSellers[i].getUsername().equals(username)) {
                return false;
            }
        }

        Seller seller = new Seller(username, Password);
        allSellers[sellerCount] = seller;
        sellerCount++;
        return true;

    }

    public boolean addBuyer(String username, String Password, Address address) {
        validateBuyerArray();

        for (int i = 0; i < buyerCount; i++) {
            if (allBuyers[i].getUsername().equals(username)) {
                return false;
            }
        }

        Buyer buyer = new Buyer(username, Password, address);
        allBuyers[buyerCount] = buyer;
        buyerCount++;
        return true;
    }

    public int getSellerIndex(String name) {
        for (int i = 0; i < sellerCount; i++) {
            if (allSellers[i].getUsername().equals(name))
                return i + 1;
        }
        if (sellerCount == 0) {
            return 0;
        }
        return 0;
    }

    public boolean displaySellers() {
        if (sellerCount == 0) {
            return false;
        }
        for (int i = 0; i < sellerCount; i++) System.out.println((i + 1) + " - " + allSellers[i].getUsername());
        return true;
    }

    public boolean displayBuyers() {
        if (buyerCount == 0) {
            return false;
        }
        for (int i = 0; i < buyerCount; i++) System.out.println((i + 1) + " - " + allBuyers[i].getUsername());
        return true;
    }

    public boolean displayProducts(int Index) {
        if (allSellers[Index - 1].getProducts().isEmpty())
            return false;
        System.out.println(allSellers[Index - 1].getProducts());
        return true;
    }

    public void displayCategories() {
        Product.eCategory[] allCategories = Product.eCategory.values();
        for (Product.eCategory Category : allCategories) {
            System.out.println((Category.ordinal() + 1) + ") " + Category.name());
        }
    }

    public boolean checkSeller(int Index) {
        return Index > 0 && Index <= sellerCount;
    }

    public boolean checkBuyer(int Index) {
        return Index > 0 && Index <= buyerCount;
    }

    public boolean checkProduct(int sellerIndex, int productIndex) {
        return productIndex > 0 && productIndex <= allSellers[sellerIndex - 1].getProductsSize();
    }

    public boolean checkCategory(int index) {
        Product.eCategory[] allCategories = Product.eCategory.values();
        return index > 0 && index <= allCategories.length;
    }

    public boolean addProductToSeller(Product product, int Index) {
        allSellers[Index - 1].addNewProduct(product);
        return true;
    }

    public boolean addProductToBuyer(int BuyerChoice, int SellerChoice, int ProductChoice) {
        Buyer buyer = allBuyers[BuyerChoice - 1];
        Seller seller = allSellers[SellerChoice - 1];
        Product product = seller.getProductAtIndex(ProductChoice - 1);
        Order buyersOrder = buyer.getOrder();
        buyersOrder.addProductToCart(product);
        return true;
    }

    public boolean checkPayment(int Index) {
        return allBuyers[Index - 1].getOrder().payment() != 0;
    }

    public double checkOut(int Index) {
        Buyer buyer = allBuyers[Index - 1];
        Order checkedOrder = buyer.getOrder();

        double payment = checkedOrder.payment();
        buyer.addOrderToHistory();
        return payment;
    }

    public void showAllBuyers() {
        if (buyerCount == 0) System.out.println("No Buyers available in the system");
        sortUserArray(allBuyers, buyerCount);
        for (int i = 0; i < buyerCount; i++)
            System.out.println((i + 1) + " - " + allBuyers[i].toString());
    }

    public void showAllSellers() {
        if (sellerCount == 0) System.out.println("No Sellers available in the system");
        sortUserArray(allSellers, sellerCount);
        for (int i = 0; i < sellerCount; i++)
            System.out.println((i + 1) + " - " + allSellers[i].toString());
    }

    public boolean showProductsPerCategory(int index) {
        int categoryIndex = 0;
        Product.eCategory[] allCategories = Product.eCategory.values();
        Product.eCategory category = allCategories[index - 1];

        System.out.println(category.name());
        for (Seller seller : allSellers) {
            for (int j = 0; j < seller.getProductsSize(); j++)
                if (Objects.equals(seller.getProductAtIndex(j).getCategory().name(), category.name())) {
                    String nameOfSeller = seller.getProductAtIndex(j).getSeller().getUsername();
                    String nameOfProduct = seller.getProductAtIndex(j).getName();
                    double priceOfProduct = seller.getProductAtIndex(j).getPrice();
                    System.out.println((++categoryIndex) + " - " + nameOfProduct + "\n" +
                            "    seller: " + nameOfSeller + "\n" +
                            "    Price: " + priceOfProduct);
                }
        }
        return categoryIndex != 0;
    }

    public boolean showOrderHistory(int index) {
        if (allBuyers[index - 1].getOrderHistorySize() == 0)
            return false;
        System.out.println(allBuyers[index - 1].orderHistoryToString());
        return true;
    }

    public boolean checkOrderHistory(int buyerIndex, int cartIndex) {
        return cartIndex > 0 && cartIndex <= allBuyers[buyerIndex - 1].getOrderHistorySize();
    }

    public boolean createCartFromHistory(int buyerIndex, int cartIndex, boolean replace) {
        Buyer buyer = allBuyers[buyerIndex - 1];
        int sizeOfCart = buyer.getOrder().getProductsSize();
        Order oldOrderHistory = buyer.getOrderHistory()[cartIndex - 1];
        if (!replace)
            if (sizeOfCart > 0)
                return false;
        buyer.replaceOrderFromHistory(oldOrderHistory);
        return true;
    }

    public int getSellerCount() {
        return sellerCount;
    }

    public Seller[] getAllSellers() {
        return allSellers;
    }

    public int getBuyerCount() {
        return buyerCount;
    }

    public Buyer[] getAllBuyers() {
        return allBuyers;
    }

}