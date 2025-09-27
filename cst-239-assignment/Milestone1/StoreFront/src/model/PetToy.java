package model;

import java.time.LocalDate;

public class PetToy extends SalableProduct {
    private String material;
    private String durability;

    public PetToy() {}

    public PetToy(int id, String name, String description, LocalDate dom, double price,
                  String material, String durability) {
        super(id, name, description, dom, price);
        this.material = material;
        this.durability = durability;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getDurability() { return durability; }
    public void setDurability(String durability) { this.durability = durability; }
}
