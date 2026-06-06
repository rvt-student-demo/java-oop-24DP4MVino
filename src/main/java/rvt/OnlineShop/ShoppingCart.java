package rvt.OnlineShop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Item> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }
    
    public Collection<Item> values() {
        return this.cart.values();
    }

    public void add(String product, int price) {
        Item existingItem = this.cart.get(product);

        if (existingItem != null) {
            existingItem.increaseQuantity();
        } else {
            Item newItem = new Item(product, 1, price);
            this.cart.put(product, newItem);
        }
    }

    public int price() {
        int total = 0;

        for (Item item : this.cart.values()) {
            total += item.price();
        }
        return total;
    }

    public void print() {
        for (Item item : this.cart.values()) {
            System.out.println(item);
        }
    }
}
