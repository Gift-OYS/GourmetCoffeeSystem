package GourmetCoffeeSystem;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Iterator;

public class Order implements Iterable<OrderItem>{
    private ArrayList<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<OrderItem>();
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItems.remove(orderItem);
    }

    public Iterator<OrderItem> iterator(){
        return orderItems.iterator();
    }

    public OrderItem getOrderItem(Product product){
        for (OrderItem orderItem : orderItems){
            if (orderItem.getProduct().equals(product)){
                return orderItem;
            }
        }
        return null;
    }

    public int getNumberOfItems(){
        return orderItems.size();
    }

    public double getTotalCost(){
        double cost = 0;

        for (OrderItem orderItem : orderItems){
            cost += orderItem.getValue();
        }

        return cost;
    }
}
