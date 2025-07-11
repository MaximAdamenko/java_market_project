package RoniAndMax;


import java.util.InputMismatchException;
import java.util.Scanner;


//name: Maxim Adamenko id:318762721
//name:Roni Aharon Laufer id:322438102
// in class 10128 with Keren Kalif on monday 12:00-15:00

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        BusinessManager MyBusiness = new BusinessManager("At Roni's");
        startDefaultData(MyBusiness);
        menu(MyBusiness);
    }

    public static void startDefaultData(BusinessManager OurBusiness) {
        try {
            OurBusiness.addSeller("Max Adamenko", "Max1479");
            OurBusiness.addSeller("Roni Laufer", "Roni1234");
            Address yuvalsAddress = new Address("King George", 10, "Ashdod", "Israel");
            Address davidsAddress = new Address("Bakers street", 221, "London", "England");
            OurBusiness.addBuyer("Yuval Kot", "310520", yuvalsAddress);
            OurBusiness.addBuyer("David Cohen", "dave3456", davidsAddress);

            int Index1 = OurBusiness.getSellerIndex("Max Adamenko");
            Product product1 = new Product("iphone", 599.0, Product.eCategory.Electricity);
            OurBusiness.addProductToSeller(product1, Index1);
            Product product2 = new Product("Mac", 999.0, Product.eCategory.Electricity);
            OurBusiness.addProductToSeller(product2, Index1);
            Product product3 = new Product("Apple Watch", 399.0, Product.eCategory.Electricity);
            OurBusiness.addProductToSeller(product3, Index1);
            Product product4 = new Product("Ipad", 799.0, Product.eCategory.Electricity);
            OurBusiness.addProductToSeller(product4, Index1);

            int Index2 = OurBusiness.getSellerIndex("Roni Laufer");
            Product product5 = new Product("Guitar", 499.0, Product.eCategory.Desk);
            OurBusiness.addProductToSeller(product5, Index2);
            Product product6 = new Product("Piano", 2000.0, Product.eCategory.Desk);
            OurBusiness.addProductToSeller(product6, Index2);
            Product product7 = new Product("Violin", 1200.0, Product.eCategory.Desk);
            OurBusiness.addProductToSeller(product7, Index2);

            OurBusiness.sortUserArray(OurBusiness.getAllBuyers(), OurBusiness.getBuyerCount());
            OurBusiness.sortUserArray(OurBusiness.getAllSellers(), OurBusiness.getSellerCount());

            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("We have a technical issue! we will be right back. ");
            scan.nextLine();
        }


    }


    public static void menu(BusinessManager businessManager) {
        int choice;
        do {
            try {
                System.out.println("enter a number between 0 - 9 for your requested option");
                String Message = """
                        0 - Exit
                        1 - Add seller
                        2 - Add buyer
                        3 - Add product to seller
                        4 - Add product to buyer
                        5 - Checkout buyer
                        6 - View buyers
                        7 - View sellers
                        8 - View products per category
                        9 - Create new cart from history""";
                System.out.println(Message);
                choice = scan.nextInt();


                switch (choice) {
                    case 0:
                        System.out.println("goodbye :)");
                        break;
                    case 1:
                        addSeller(businessManager);
                        break;
                    case 2:
                        addBuyer(businessManager);
                        break;
                    case 3:
                        addProductToSeller(businessManager);
                        break;
                    case 4:
                        addProductToBuyer(businessManager);
                        break;
                    case 5:
                        checkOut(businessManager);
                        break;
                    case 6:
                        businessManager.showAllBuyers();
                        break;
                    case 7:
                        businessManager.showAllSellers();
                        break;
                    case 8:
                        showProductsPerCategory(businessManager);
                        break;
                    case 9:
                        createCartFromHistory(businessManager);
                        break;
                }
                if (choice == 0) {
                    break;
                }
                System.out.println("Do you wish to continue?");
                System.out.println("Enter 0 to exit, if you wish to continue press any number:");
                choice = scan.nextInt();
                System.out.println(choice == 0 ? "goodbye :)" : "");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter a number not a string");
                scan.nextLine();
                choice = 1;
            } catch (noProductsInCartException e) {
                System.out.println("There are no products at the cart , checkout is unavailable");
                scan.nextLine();
                choice = 1;
            } catch (Exception e) {
                System.out.println("We have a technical issue! we will be right back. ");
                scan.nextLine();
                choice = 1;
            }


        } while (choice != 0);
    }

    private static String createName() {
        System.out.println("enter a first name: ");
        String firstName = scan.next();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        System.out.println("enter last name: ");
        String lastName = scan.next();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        return firstName + " " + lastName;
    }

    private static String createPassword() {
        System.out.println("enter your password: ");
        String password = scan.next();

        while (password.length() < 5 || password.length() > 20) {
            System.out.println("Your password  is out of Range (5-20 characters max):");
            password = scan.next();
        }

        password = password.substring(0, 1).toUpperCase() + password.substring(1);
        return password;
    }

    private static Address createAddress() {
        System.out.println("Enter your street: ");
        String street = scan.next() + scan.nextLine();
        street = street.substring(0, 1).toUpperCase() + street.substring(1);

        System.out.println("Enter the number of your address: ");
        int numOfAddress = readInteger();
        System.out.println("Enter your city: ");
        String city = scan.next() + scan.nextLine();
        city = city.substring(0, 1).toUpperCase() + city.substring(1);
        System.out.println("Enter your country: ");
        String country = scan.next();
        country = country.substring(0, 1).toUpperCase() + country.substring(1);
        return new Address(street, numOfAddress, city, country);
    }


    public static void addSeller(BusinessManager businessManager) {
        String username = createName();
        String password = createPassword();
        while (!businessManager.addSeller(username, password)) {
            System.out.println("The user name already exists in the system. please try again:\n");
            username = createName();
        }
    }

    public static void addBuyer(BusinessManager businessManager) {
        String username = createName();
        String password = createPassword();
        Address address = createAddress();
        while (!businessManager.addBuyer(username, password, address)) {
            System.out.println("The user name already exists in the system. please try again:\n");
            username = createName();
        }
    }


    public static Product createProduct(BusinessManager businessManager) {
        String productName;
        Product.eCategory category = null;
        Product.eCategory[] allCategories = Product.eCategory.values();
        int categoryChoice, isSpecialPackage;
        double price;
        System.out.println("Enter the product that you're selling");
        productName = scan.next();

        System.out.println("Choose the category of the product:");
        businessManager.displayCategories();
        categoryChoice = readInteger();
        while (!businessManager.checkCategory(categoryChoice)) {
            System.out.println("Invalid input - choose again");
            categoryChoice = readInteger();
        }

        category = allCategories[categoryChoice - 1];
        System.out.println("Enter the price of the product(USD):");
        price = readPrice();
        System.out.println("Do you want to add a special package to your product?\n" +
                "if yes enter 1, if no enter any other number");
        isSpecialPackage = readInteger();
        if (isSpecialPackage == 1) {
            return new SpecialPackage(productName, price, category);
        }
        return new Product(productName, price, category);
    }

    public static void addProductToSeller(BusinessManager businessManager) {
        int sellerIndex;
        double specialPackagePrice;
        System.out.println("Please chose one of the sellers below");
        if (!businessManager.displaySellers()) {
            System.out.println("No Sellers available in the system");
            return;
        }
        sellerIndex = readInteger();
        while (!businessManager.checkSeller(sellerIndex)) {
            System.out.println("Invalid input - choose again");
            sellerIndex = readInteger();
        }
        Product product = createProduct(businessManager);
        if (product instanceof SpecialPackage) {
            System.out.println("Enter the special package price:");
            specialPackagePrice = readPrice();
            System.out.println("Your product has been packed");
            ((SpecialPackage) product).packaging(specialPackagePrice);
        }
        businessManager.addProductToSeller(product, sellerIndex);
        System.out.println("The item " + product.getName() +
                " was added successfully with the price tag of "
                + product.getPrice() + "$");
    }

    public static void addProductToBuyer(BusinessManager businessManager) {
        int buyerIndex, sellerIndex, productIndex;
        System.out.println("Please chose one of the buyers below");
        if (!businessManager.displayBuyers()) {
            System.out.println("No Buyers available in the system");
            return;
        }
        buyerIndex = readInteger();
        while (!businessManager.checkBuyer(buyerIndex)) {
            System.out.println("Invalid input - choose again");
            buyerIndex = readInteger();
        }


        System.out.println("From which seller would you like to purchase?");
        if (!businessManager.displaySellers()) {
            System.out.println("No Sellers available in the system");
            return;
        }
        sellerIndex = readInteger();
        while (!businessManager.checkSeller(sellerIndex)) {
            System.out.println("Invalid input - choose again");
            sellerIndex = readInteger();
        }
        System.out.println("Please choose one of the products below: ");
        if (!businessManager.displayProducts(sellerIndex)) {
            System.out.println("No Products available for this seller");
            return;
        }
        productIndex = readInteger();
        while (!businessManager.checkProduct(sellerIndex, productIndex)) {
            System.out.println("Invalid input - choose again");
            productIndex = readInteger();
        }
        businessManager.addProductToBuyer(buyerIndex, sellerIndex, productIndex);
        System.out.println("The product successfully added to the cart");
    }


    public static void checkOut(BusinessManager businessManager) throws noProductsInCartException {
        int buyerIndex;
        System.out.println("Please chose one of the buyers below");
        if (!businessManager.displayBuyers()) {
            System.out.println("No Buyers available in the system");
            return;
        }
        buyerIndex = readInteger();
        while (!businessManager.checkBuyer(buyerIndex)) {
            System.out.println("Invalid input - choose again");
            buyerIndex = readInteger();
        }

        if (!businessManager.checkPayment(buyerIndex)) {//
            throw new noProductsInCartException("The cart is empty");
        }
        System.out.println("Your total price is: " + businessManager.checkOut(buyerIndex) + "$");
    }

    public static void showProductsPerCategory(BusinessManager businessManager) {
        int categoryIndex;
        System.out.println("Choose the category of that product:");
        businessManager.displayCategories();
        categoryIndex = readInteger();
        while (!businessManager.checkCategory(categoryIndex)) {
            System.out.println("Invalid input - choose again");
            categoryIndex = readInteger();
        }

        if (!businessManager.showProductsPerCategory(categoryIndex)) {
            System.out.println("There are no items available from this category");
        }
    }

    public static void createCartFromHistory(BusinessManager businessManager) {
        int buyerIndex, cartIndex;
        boolean replace = false;
        System.out.println("Please choose one of the buyers below");
        businessManager.displayBuyers();
        buyerIndex = readInteger();
        while (!businessManager.checkBuyer(buyerIndex)) {
            System.out.println("Invalid input - choose again");
            buyerIndex = readInteger();
        }
        System.out.println("Please choose one of the carts below");
        if (!businessManager.showOrderHistory(buyerIndex)) {
            System.out.println("Your cart history is empty");
            return;
        }
        cartIndex = readInteger();
        while (!businessManager.checkOrderHistory(buyerIndex, cartIndex)) {
            System.out.println("Invalid input - choose again");
            cartIndex = readInteger();
        }
        if (!businessManager.createCartFromHistory(buyerIndex, cartIndex, replace)) {
            System.out.println("Your current cart is not empty. do you want to replace it?\n" +
                    "if yes enter 1, if no enter any number");
            buyerIndex = readInteger();
            if (buyerIndex != 1) {
                return;
            }
        }
        replace = true;
        businessManager.createCartFromHistory(buyerIndex, cartIndex, replace);
        System.out.println("Your cart has been updated");
    }

    private static int readInteger() {
        int input = 0;
        boolean validInput = false;
        do {
            try {
                input = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter an integer number:  ");
                scan.nextLine();
            }
        } while (!validInput);
        return input;
    }

    private static double readPrice() {
        double input = 0;
        boolean validInput = false;
        do {
            try {
                input = scan.nextFloat();
                if (input < 0 || input > 1_000_000) throw new priceOutOfBoundsException("");
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter a number:");
                scan.nextLine();
            } catch (priceOutOfBoundsException e) {
                System.out.println("Your price is out of range (0-1,000,000) enter again: ");
                scan.nextLine();
            }
        } while (!validInput);
        return input;
    }

    public static class noProductsInCartException extends Exception {
        public noProductsInCartException(String msg) {
            super(msg);
        }
    }

    public static class priceOutOfBoundsException extends Exception {
        public priceOutOfBoundsException(String msg) {
            super(msg);
        }
    }
}