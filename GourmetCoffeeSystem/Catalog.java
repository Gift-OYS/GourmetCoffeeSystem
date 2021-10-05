package GourmetCoffeeSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class Catalog implements Iterable<Product>{
    private ArrayList<Product> products;

    public Catalog() {
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Iterator<Product> iterator(){
        return this.products.iterator();
    }

    public Product getProduct(String code){
        for (Product product : this.products){
            if (product.getCode().equals(code)){
                return product;
            }
        }
        return null;
    }

    public int getNumberOfProducts(){
        return this.products.size();
    }
}
