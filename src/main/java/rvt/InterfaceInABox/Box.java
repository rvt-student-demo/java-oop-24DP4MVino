package rvt.InterfaceInABox;

import java.util.ArrayList;

public class Box implements Packable {
    private ArrayList<Packable> packables;
    private double maxCapacity;

    public Box(double maxCapacity) {
        this.packables = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public void add(Packable packable) {
        if (packable.weight() + this.weight() <= this.maxCapacity){
            packables.add(packable);
        }
    }

    public double weight() {
        double sum = 0;
        for(int i = 0; i < packables.size(); i++) {
            sum += packables.get(i).weight();
        }
        return sum;
    }

    public String toString() {
        return "Box: " + this.packables.size() + " items, total weight " + this.weight() + " kg";
    }
}
