/**
 * Service layer for store inventory.
 * Holds and manages a list of ProductWithQuantity items.
 * Pure logic: no console I/O. Provides CRUD, search, and quantity updates.
 */

package services;


import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryManager {
    private final List<ProductWithQuantity> inventory = new ArrayList<>();
    private int nextId = 1;

    public InventoryManager(boolean initialize) {
        if (initialize) initializeInventory();
    }

    // ---------- seed data ----------
    void initializeInventory() {
        PetFood food1 = new PetFood(generateId(),
                "Crunchy Kibble", "High-protein dry food",
                LocalDate.of(2024, 6, 10), 24.99,
                "High Protein", LocalDate.of(2026, 1, 31),
                List.of("Chicken", "Oats", "Vitamins"));
        addProduct(food1, 15);

        PetToy toy1 = new PetToy(generateId(),
                "Squeaky Ball", "Durable rubber ball",
                LocalDate.of(2024, 3, 2), 8.49,
                "Rubber", "High");
        addProduct(toy1, 30);

        PetAccessory acc1 = new PetAccessory(generateId(),
                "Comfort Collar", "Adjustable nylon collar",
                LocalDate.of(2023, 11, 20), 12.99,
                "M", "Blue");
        addProduct(acc1, 20);

        PetFood food2 = new PetFood(generateId(),
                "Gourmet Wet Food", "Grain-free wet food",
                LocalDate.of(2024, 8, 1), 3.49,
                "Grain Free", LocalDate.of(2025, 12, 15),
                List.of("Turkey", "Pumpkin", "Broth"));
        addProduct(food2, 40);
    }

    int generateId() { return nextId++; }

    // ---------- CRUD ----------
    public List<ProductWithQuantity> getAllProducts() {
        return new ArrayList<>(inventory);
    }

    public SalableProduct getProductById(int id) {
        return inventory.stream()
                .map(ProductWithQuantity::getProduct)
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(SalableProduct product, int quantity) {
        Objects.requireNonNull(product, "product");
        inventory.add(new ProductWithQuantity(product, Math.max(0, quantity)));
    }

    public boolean removeProduct(SalableProduct product) {
        return inventory.removeIf(pq -> pq.getProduct().equals(product));
    }

    /** Replace an equal-id product instance already in inventory. */
    public boolean updateProduct(SalableProduct updated) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getProduct().getId() == updated.getId()) {
                int q = inventory.get(i).getQuantity();
                inventory.set(i, new ProductWithQuantity(updated, q));
                return true;
            }
        }
        return false;
    }

    public boolean updateQuantity(int id, int newQty) {
        for (ProductWithQuantity pq : inventory) {
            if (pq.getProduct().getId() == id) {
                pq.setQuantity(Math.max(0, newQty));
                return true;
            }
        }
        return false;
    }

    // ---------- search ----------
    public List<SalableProduct> searchProductsByName(String term) {
        String t = term == null ? "" : term.toLowerCase();
        return inventory.stream()
                .map(ProductWithQuantity::getProduct)
                .filter(p -> (p.getName() != null) && p.getName().toLowerCase().contains(t))
                .toList();
    }

    public List<SalableProduct> searchProductsByDescription(String term) {
        String t = term == null ? "" : term.toLowerCase();
        return inventory.stream()
                .map(ProductWithQuantity::getProduct)
                .filter(p -> (p.getDescription() != null) && p.getDescription().toLowerCase().contains(t))
                .toList();
    }

    public void clearInventory() {
        inventory.clear();
    }
}
