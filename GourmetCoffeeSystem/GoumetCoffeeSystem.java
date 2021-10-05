package GourmetCoffeeSystem;

import java.awt.font.FontRenderContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.NumberFormat;

public class GoumetCoffeeSystem {
    private static BufferedReader stdIn=
            new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter stdOut=
            new PrintWriter(System.out,true);
    private PrintWriter stdErr=
            new PrintWriter(new PrintWriter(System.err,true));
    private static final NumberFormat CURRENCY=
            NumberFormat.getCurrencyInstance();

    private Catalog catalog;
    private Order order;
    private Sales sales;

    public static void main(String[] args) throws IOException {

        GoumetCoffeeSystem application = new GoumetCoffeeSystem();
        application.run();

    }

    public GoumetCoffeeSystem() throws IOException{
        this.catalog = loadCatalog();
        this.order = new Order();
        this.sales = loadSales(this.catalog);
    }

    private Catalog loadCatalog(){
        Catalog catalog = new Catalog();

        catalog.addProduct(new Coffee("C001","Colombia1, Whole, 1 lb",17.99, "Colombia","Colombia1","Rich and Hearty","intense","Rich","Medium"));
        catalog.addProduct(new Coffee("C002","Colombia2, Whole, 1 lb",20.00, "Colombia","Colombia2","Rich and Hearty","intense","Rich","Medium"));
        catalog.addProduct(new Coffee("C003","Colombia3, Whole, 1 lb",16.70, "Colombia","Colombia3","Rich and Hearty","intense","Rich","Medium"));
        catalog.addProduct(new Coffee("C004","Colombia4, Whole, 1 lb",18.00, "Colombia","Colombia4","Rich and Hearty","intense","Rich","Medium"));
        catalog.addProduct(new Coffee("C005","Colombia5, Whole, 1 lb",21.00, "Colombia","Colombia5","Rich and Hearty","intense","Rich","Medium"));

        catalog.addProduct(new CoffeeBrewer("B001","Coffee Brewer, 1 Warmers",150.00, "Brewer 100","Pourover",6));
        catalog.addProduct(new CoffeeBrewer("B002","Coffee Brewer, 2 Warmers",200.00, "Brewer 200","Pourover",12));
        catalog.addProduct(new CoffeeBrewer("B003","Coffee Brewer, 3 Warmers",280.00, "Brewer 210","Pourover",12));

        catalog.addProduct(new Product("A001","Almond Flavored Syrup",9.00));
        catalog.addProduct(new Product("A002","Irish Creme Flavored Syrup",9.00));
        catalog.addProduct(new Product("A003","Mint Flavored syrup",8.00));
        catalog.addProduct(new Product("A004","Caramel Flavored Syrup",8.00));
        catalog.addProduct(new Product("A005","Gourment Coffee Cookies",12.00));

        return catalog;
    }

    private Sales loadSales(Catalog catalog){
        Sales sales = new Sales();
        Order[] orders = new Order[6];

        orders[0]=new Order();
        orders[0].addOrderItem(new OrderItem(this.catalog.getProduct("C001"),5));
        sales.addOrder(orders[0]);

        orders[1]=new Order();
        orders[1].addOrderItem(new OrderItem(this.catalog.getProduct("C002"),5));
        orders[1].addOrderItem(new OrderItem(this.catalog.getProduct("A001"),6));
        orders[1].addOrderItem(new OrderItem(this.catalog.getProduct("A003"),5));
        sales.addOrder(orders[1]);

        orders[2]=new Order();
        orders[2].addOrderItem(new OrderItem(this.catalog.getProduct("B002"),5));
        orders[2].addOrderItem(new OrderItem(this.catalog.getProduct("C001"),2));
        orders[2].addOrderItem(new OrderItem(this.catalog.getProduct("A005"),5));
        sales.addOrder(orders[2]);

        orders[3]=new Order();
        orders[3].addOrderItem(new OrderItem(this.catalog.getProduct("B002"),3));
        orders[3].addOrderItem(new OrderItem(this.catalog.getProduct("C001"),3));
        orders[3].addOrderItem(new OrderItem(this.catalog.getProduct("A003"),3));
        sales.addOrder(orders[3]);

        orders[4]=new Order();
        orders[4].addOrderItem(new OrderItem(this.catalog.getProduct("B003"),5));
        orders[4].addOrderItem(new OrderItem(this.catalog.getProduct("C003"),1));
        orders[4].addOrderItem(new OrderItem(this.catalog.getProduct("A005"),5));
        sales.addOrder(orders[4]);

        orders[5]=new Order();
        orders[5].addOrderItem(new OrderItem(this.catalog.getProduct("A002"),5));
        orders[5].addOrderItem(new OrderItem(this.catalog.getProduct("B002"),6));
        orders[5].addOrderItem(new OrderItem(this.catalog.getProduct("C005"),5));
        sales.addOrder(orders[5]);

        return sales;
    }

    private void run() throws IOException{
        int choice = getChoice();

        while (choice != 0){
            if (choice == 1)        displayCatalog();
            else if (choice == 2)   displayProductInfo();
            else if (choice == 3)   displayOrder();
            else if (choice == 4)   addModifyProduct();
            else if (choice == 5)   removeProdcut();
            else if (choice == 6)   saleOrder();
            else if (choice == 7)   displayOrdersSold();

            choice = getChoice();
        }
    }

    private int getChoice() throws IOException{
        int input;

        do {
            try {
                stdErr.println();
                stdErr.print(     "[0] Quit\n"
                                + "[1] Display catalog\n"
                                + "[2] Display product\n"
                                + "[3] Display current order\n"
                                + "[4] Add/modify product to/in current order\n"
                                + "[5] Remove product from current order\n"
                                + "[6] Register sale of current order\n"
                                + "[7] Display sales\n"
                                + "choice> ");
                stdErr.flush();

                input = Integer.parseInt(stdIn.readLine());

                stdErr.println();

                if(0 <= input && 7 >= input){
                    break;
                }else {
                    stdErr.println("Invalid choice: " + input);
                }
            }catch (NumberFormatException nfe){
                stdErr.println(nfe);
            }
        }while (true);

        return input;
    }


    public void displayCatalog(){
        int size = this.catalog.getNumberOfProducts();

        if (size == 0){
            stdErr.println("The catalog is empty");
        }else {
            for (Product product : this.catalog){
                stdOut.println(product.getCode() + "  " + product.getDescription());
            }
        }
    }

    public void displayProductInfo() throws IOException{
        Product product = readProduct();

        stdOut.println("Description: " + product.getDescription());
        stdOut.println("Price: " + product.getPrice());

        if (product instanceof Coffee){
            Coffee coffee = (Coffee) product;

            stdOut.println("Origin: " + coffee.getOrigin());
            stdOut.println(" Roast: " + coffee.getRoast());
            stdOut.println(" Flavor: " + coffee.getFlavor());
            stdOut.println(" Aroma: " + coffee.getAroma());
            stdOut.println(" Acidity: " + coffee.getAcidity());
            stdOut.println(" Body: " + coffee.getBody());
        }else if(product instanceof CoffeeBrewer){
            CoffeeBrewer coffeeBrewer = (CoffeeBrewer) product;

            stdOut.println(" Model: " + coffeeBrewer.getModel());
            stdOut.println(" Water Supply: " + coffeeBrewer.getWaterSupply());
            stdOut.println(" Number of Cups: " + coffeeBrewer.getCapacity());
        }
    }


    public Product readProduct() throws IOException{
        do {
            stdErr.print("Product code> ");
            stdErr.flush();

            Product product = this.catalog.getProduct(stdIn.readLine());

            if (product != null){
                return product;
            }else {
                stdErr.println("There are no products with that code");
            }
        }while (true);
    }

    public void displayOrder(){
        int size = this.order.getNumberOfItems();

        if(size == 0){
            stdErr.println("The current order is empty");
        }else {
            for (OrderItem orderItem : this.order){
                stdOut.println(orderItem.toString());
            }
            stdOut.println("Total: " + CURRENCY.format(this.order.getTotalCost()));
        }
    }

    public void addModifyProduct() throws IOException{
        Product product = readProduct();
        int quantity = readQuantity();
        OrderItem orderItem = this.order.getOrderItem(product);

        if(orderItem == null){
            this.order.addOrderItem(new OrderItem(product, quantity));
            stdOut.println("This product " + product.getCode() + " has been added");
        }else {
            orderItem.setQuantity(quantity);
            stdOut.println("The quantity of the product " + product.getCode() + " has been modified");
        }
    }

    public int readQuantity() throws IOException{
        int quantity;
        do {
            try {
                stdErr.println("Quantity> ");
                stdErr.flush();
                quantity = Integer.parseInt(stdIn.readLine());
                if (quantity > 0){
                    return quantity;
                }else {
                    stdErr.println("Invalid input. Please enter a positive integer");
                }
            }catch (NumberFormatException nfe){
                stdErr.println(nfe);
            }
        }while (true);
    }

    public void removeProdcut() throws IOException{
        Product product = readProduct();
        OrderItem orderItem = this.order.getOrderItem(product);

        if (orderItem != null){
            this.order.removeOrderItem(orderItem);
            stdOut.println("The product " + product.getCode() + " has been removed from the current order");
        }else {
            stdErr.println("There are no products in the current order with that code");
        }
    }

    public void saleOrder(){
        if (this.order.getNumberOfItems() > 0){
            this.sales.addOrder(this.order);
            stdOut.println("The sale of the order has been registered");
        }else {
            stdErr.println("The current order is empty");
        }
    }

    public void displayOrdersSold(){
        int numOrder = this.sales.getNumberOfOrders();

        if (numOrder != 0){
            int orderNumber = 1;
            for (Order order : this.sales){
                stdOut.println("Order: " + orderNumber++);

                for (OrderItem orderItem : order){
                    stdOut.println("  " + orderItem.toString());
                }
                stdOut.println(" Total: " + CURRENCY.format(order.getTotalCost()));
            }
        }else {
            stdErr.println("There are no sales");
        }
    }

}
