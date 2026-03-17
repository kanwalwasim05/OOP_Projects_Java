package Inventory_Shop;

public class Product {
    //Product attributes
    private int id;
    private String name;
    private double price;
    private int quantity;

    //Constructor Formation
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
//getter for product id
    public int getId() {
        return id;
    }
    //getter for product name
    public String getName() {
        return name;
    }
    //getter for price
    public double getPrice() {
        return price;
    }
    //getter for quantity
    public int getQuantity() {
        return quantity;
    }
    //setter for id
    public void setId(int id) {
        this.id = id;
    }
    //setter for product name
    public void setName(String name) {
        this.name = name;
    }
    //setter for product prize
    public void setPrice(double price) {
        this.price = price;
    }
    //setter for product quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // toString formula:
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price: $" + price + " | Qty: " + quantity;
    }
}