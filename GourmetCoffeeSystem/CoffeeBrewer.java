package GourmetCoffeeSystem;

public class CoffeeBrewer extends Product{
    private String model;
    private String waterSupply;
    private int capacity;

    public CoffeeBrewer(String code, String description, double price, String model, String waterSupply, int capacity) {
        super(code, description, price);
        this.model = model;
        this.waterSupply = waterSupply;
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public int getCapacity() {
        return capacity;
    }

    public String toString(){
        return super.toString() +"_" + getModel() + "_" + getWaterSupply() + "_" + getCapacity();
    }
}
