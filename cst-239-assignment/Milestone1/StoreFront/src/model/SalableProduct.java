package model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class SalableProduct implements Comparable<SalableProduct> {
    private int id;
    private String name;
    private String description;
    private LocalDate dateOfManufacture;
    private double price;

    public SalableProduct() {}

    public SalableProduct(int id, String name, String description, LocalDate dateOfManufacture, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfManufacture = dateOfManufacture;
        this.price = price;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateOfManufacture() { return dateOfManufacture; }
    public void setDateOfManufacture(LocalDate dateOfManufacture) { this.dateOfManufacture = dateOfManufacture; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public int compareTo(SalableProduct o) {
        // Default: compare by name (you can extend later to by date/price)
        if (o == null || o.getName() == null) return 1;
        if (this.name == null) return -1;
        return this.name.compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalableProduct that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Product{id=%d, name='%s', price=%.2f}".formatted(id, name, price);
    }
}
