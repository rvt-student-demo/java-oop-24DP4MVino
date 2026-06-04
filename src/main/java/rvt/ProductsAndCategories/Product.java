package rvt.ProductsAndCategories;

public class Product {
    private int id;
    private String name;
    private double price;
    private int category_id;
    
    public Product(int id, String name, double price, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String toString() {
        return "ID: " + id + ", Nosaukums: " + name + ", Cena: " + price + "€";
    }
}
