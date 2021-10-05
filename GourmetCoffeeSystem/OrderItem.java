package GourmetCoffeeSystem;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue(){
        return quantity * product.getPrice();
    }

    public String toString(){
        return getQuantity() + "_" + product.getCode() + "_" + product.getDescription() + "_" + product.getPrice();
    }
}
