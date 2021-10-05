package GourmetCoffeeSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class Sales implements Iterable<Order>{
    private ArrayList<Order> orders;

    public Sales() {
        this.orders = new ArrayList<Order>();
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public int getNumberOfOrders(){
        return orders.size();
    }

    public Iterator<Order> iterator(){
        return orders.iterator();
    }
}
