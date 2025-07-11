package RoniAndMax;


public class Buyer extends User implements Comparable<Buyer> {
    private Address address;
    private Order order;

    private Order[] ordersHistory;
    private int orderHistorySize;

    public Buyer(String username, String password, Address address) {
        super(username, password);
        setAddress(address);
        ordersHistory = new Order[2];
        order = new Order();
    }

    public Buyer(Buyer other) {
        super(other.getUsername(), other.getPassWord());
        this.address = new Address(other.address);
        this.order = new Order(other.order);
        this.orderHistorySize = other.getOrderHistorySize();
        this.ordersHistory = other.cloneOrderHistoryArray();
    }

    public Order[] cloneOrderHistoryArray() {
        Order[] newOrderHistory = new Order[ordersHistory.length];
        for (int i = 0; i < orderHistorySize; i++) {
            newOrderHistory[i] = new Order(ordersHistory[i]);
        }
        return newOrderHistory;
    }

    public Address getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    public Order[] getOrderHistory() {
        return ordersHistory;
    }

    public int getOrderHistorySize() {
        return orderHistorySize;
    }

    public boolean setAddress(Address address) {
        if (address.toString() == null || address.toString().isEmpty()) {
            return false;
        }
        this.address = address;
        return true;
    }

    public void addOrderToHistory() {
        validateOrderArraySize(orderHistorySize);
        ordersHistory[orderHistorySize] = order;
        orderHistorySize++;
        order = new Order();
    }

    public void replaceOrderFromHistory(Order order) {
        this.order = new Order(order);
        this.order.resetDateOfPurchase();
    }


    private void validateOrderArraySize(int Size) {
        if (Size == 0) Size++;
        if (Size < ordersHistory.length) return;
        if (Size == ordersHistory.length) Size *= 2;
        Order[] newOrder = new Order[Size];
        for (int i = 0; i < ordersHistory.length; i++) newOrder[i] = ordersHistory[i];
        ordersHistory = newOrder;
    }

    public String orderHistoryToString() {
        String str = "\n   ";
        for (int i = 0; i < orderHistorySize; i++) {
            str += " " + (i + 1) + ")" + ordersHistory[i] + "\n   ";
        }
        return str;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n    address: " + address +
                "\n    current cart: " + order +
                "\n    cart history:\n" + orderHistoryToString();
    }

    @Override
    public int compareTo(Buyer o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}