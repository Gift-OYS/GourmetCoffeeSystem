package GourmetCoffeeSystem;

public class Product {
    private String code;
    private String description;
    private double price;

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Object object){
        return object instanceof Product && getCode().equals(((Product) object).getCode());
    }

    public String toString(){
        return getCode() + "_" + getDescription() + "_" + getPrice();
    }
}
