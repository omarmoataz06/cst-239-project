package model;

import java.time.LocalDate;

public class PetAccessory extends SalableProduct {
    private String size;
    private String color;

    public PetAccessory() {}

    public PetAccessory(int id, String name, String description, LocalDate dom, double price,
                        String size, String color) {
        super(id, name, description, dom, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
