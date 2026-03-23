package rvt.OnlineShop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Map<String, Integer> prices = new HashMap<>();
    private Map<String, Integer> inStock = new HashMap<>();

    public void addProduct(String product, int price, int stock) {
        prices.put(product, price);
        inStock.put(product, stock);
    }

    public int price(String product) {
        return this.prices.getOrDefault(product, -99);
    }

    public int stock(String product) {
        return this.inStock.getOrDefault(product, 0);
    }

    public boolean take(String product) {
        int currentStock = this.inStock.getOrDefault(product, 0);
        if (currentStock > 0) {
            currentStock -= 1;
            inStock.put(product, currentStock);
            return true;
        } else {
            return false;
        }
    }

    public Set<String> products() {
        return this.prices.keySet();
    }
}
