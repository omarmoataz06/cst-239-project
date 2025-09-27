/**
 * Console UI for store managers. Orchestrates menu flows and delegates to InventoryManager.
 * Handles editing parent and subclass fields (via instanceof) and List<String> editing.
 */

package services;

import model.*;
import util.InputUtilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoreManagerActions {
    private final StoreFront store;

    public StoreManagerActions(StoreFront store) {
        this.store = store;
    }

    public void handleManagerActions() {
        InputUtilities in = new InputUtilities();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Store Manager Menu ---");
            System.out.println("1) View Inventory");
            System.out.println("2) Search by Name");
            System.out.println("3) Search by Description");
            System.out.println("4) Add Product");
            System.out.println("5) Edit Product");
            System.out.println("6) Remove Product");
            System.out.println("7) Update Quantity");
            System.out.println("8) Clear Inventory");
            System.out.println("9) Save Inventory (stub)");
            System.out.println("10) Load Inventory (stub)");
            System.out.println("0) Back to Main Menu");

            int choice = in.readInt("Select option: ", 0, 10);

            switch (choice) {
                case 1 -> viewInventory();
                case 2 -> searchByName();
                case 3 -> searchByDescription();
                case 4 -> addProductFlow();
                case 5 -> editProductFlow();
                case 6 -> removeProductFlow();
                case 7 -> updateQuantityFlow();
                case 8 -> clearInventoryFlow();
                case 9 -> System.out.println("[Milestone 4] Save not implemented.");
                case 10 -> System.out.println("[Milestone 4] Load not implemented.");
                case 0 -> back = true;
            }
        }
    }

    // ---------- menu actions ----------
    private void viewInventory() {
        var inv = store.getInventoryManager().getAllProducts();
        if (inv.isEmpty()) {
            System.out.println("(inventory is empty)");
            return;
        }
        inv.forEach(item -> System.out.println(item));
    }

    private void searchByName() {
        InputUtilities in = new InputUtilities();
        String term = in.readLine("Enter name search term: ");
        var results = store.getInventoryManager().searchProductsByName(term);
        printSearchResults(results);
    }

    private void searchByDescription() {
        InputUtilities in = new InputUtilities();
        String term = in.readLine("Enter description search term: ");
        var results = store.getInventoryManager().searchProductsByDescription(term);
        printSearchResults(results);
    }

    private void addProductFlow() {
        InputUtilities in = new InputUtilities();
        InventoryManager manager = store.getInventoryManager();

        System.out.println("\nChoose product type to add:");
        System.out.println("1) PetFood");
        System.out.println("2) PetToy");
        System.out.println("3) PetAccessory");
        int type = in.readInt("Enter 1-3: ", 1, 3);

        // common fields
        String name = in.readLine("Name: ");
        String desc = in.readLine("Description: ");
        LocalDate dom = in.readLocalDate("Date of Manufacture", "yyyy-MM-dd");
        double price = Double.parseDouble(in.readLine("Price: "));

        SalableProduct product;
        if (type == 1) {
            String nutrition = in.readLine("Nutrition Value: ");
            LocalDate exp = in.readLocalDate("Expiration Date", "yyyy-MM-dd");
            List<String> ingredients = readStringList("Add ingredient (blank to finish): ");
            product = new PetFood(manager.generateId(), name, desc, dom, price, nutrition, exp, ingredients);
        } else if (type == 2) {
            String material = in.readLine("Material: ");
            String durability = in.readLine("Durability (Low/Med/High): ");
            product = new PetToy(manager.generateId(), name, desc, dom, price, material, durability);
        } else {
            String size = in.readLine("Size (S/M/L): ");
            String color = in.readLine("Color: ");
            product = new PetAccessory(manager.generateId(), name, desc, dom, price, size, color);
        }

        int qty = in.readInt("Starting quantity: ", 0, Integer.MAX_VALUE);
        manager.addProduct(product, qty);
        System.out.println("Added: " + product + ", Qty=" + qty);
    }

    private void editProductFlow() {
        InputUtilities in = new InputUtilities();
        InventoryManager manager = store.getInventoryManager();

        viewInventory();
        int id = in.readInt("Enter product ID to edit: ");

        SalableProduct p = manager.getProductById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        // base fields (press Enter to keep)
        String name = readOptionalString("Current name = " + p.getName() + " | New name (Enter to keep): ");
        if (!name.isBlank()) p.setName(name);

        String desc = readOptionalString("Current description = " + p.getDescription() + " | New description (Enter to keep): ");
        if (!desc.isBlank()) p.setDescription(desc);

        String priceText = readOptionalString("Current price = " + p.getPrice() + " | New price (Enter to keep): ");
        if (!priceText.isBlank()) p.setPrice(Double.parseDouble(priceText));

        String domText = readOptionalString("Current DoM = " + p.getDateOfManufacture() + " | New yyyy-MM-dd (Enter to keep): ");
        if (!domText.isBlank()) p.setDateOfManufacture(LocalDate.parse(domText));

        // subclass-specific
        if (p instanceof PetFood food) {
            String nv = readOptionalString("Current nutritionValue = " + food.getNutritionValue() + " | New (Enter to keep): ");
            if (!nv.isBlank()) food.setNutritionValue(nv);

            String expText = readOptionalString("Current expiration = " + food.getExpirationDate() + " | New yyyy-MM-dd (Enter to keep): ");
            if (!expText.isBlank()) food.setExpirationDate(LocalDate.parse(expText));

            editStringListInPlace("ingredients", food.getIngredients());
        } else if (p instanceof PetToy toy) {
            String material = readOptionalString("Current material = " + toy.getMaterial() + " | New (Enter to keep): ");
            if (!material.isBlank()) toy.setMaterial(material);

            String dura = readOptionalString("Current durability = " + toy.getDurability() + " | New (Enter to keep): ");
            if (!dura.isBlank()) toy.setDurability(dura);
        } else if (p instanceof PetAccessory acc) {
            String size = readOptionalString("Current size = " + acc.getSize() + " | New (Enter to keep): ");
            if (!size.isBlank()) acc.setSize(size);

            String color = readOptionalString("Current color = " + acc.getColor() + " | New (Enter to keep): ");
            if (!color.isBlank()) acc.setColor(color);
        }

        boolean ok = manager.updateProduct(p);
        System.out.println(ok ? "Product updated." : "Update failed.");
    }

    private void removeProductFlow() {
        InputUtilities in = new InputUtilities();
        InventoryManager manager = store.getInventoryManager();

        viewInventory();
        int id = in.readInt("Enter product ID to remove: ");
        SalableProduct p = manager.getProductById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        boolean ok = manager.removeProduct(p);
        System.out.println(ok ? "Removed." : "Remove failed.");
    }

    private void updateQuantityFlow() {
        InputUtilities in = new InputUtilities();
        InventoryManager manager = store.getInventoryManager();

        viewInventory();
        int id = in.readInt("Enter product ID to change quantity: ");
        int q = in.readInt("New quantity (>=0): ", 0, Integer.MAX_VALUE);
        boolean ok = manager.updateQuantity(id, q);
        System.out.println(ok ? "Quantity updated." : "Update failed.");
    }

    private void clearInventoryFlow() {
        InputUtilities in = new InputUtilities();
        if (in.readBoolean("Are you sure you want to clear all inventory?")) {
            store.getInventoryManager().clearInventory();
            System.out.println("Inventory cleared.");
        } else {
            System.out.println("Canceled.");
        }
    }

    // ---------- helpers ----------
    private void printSearchResults(List<SalableProduct> results) {
        if (results.isEmpty()) {
            System.out.println("(no matches)");
            return;
        }
        results.forEach(p -> {
            // show quantity too
            int qty = store.getInventoryManager().getAllProducts().stream()
                    .filter(pq -> pq.getProduct().equals(p))
                    .findFirst().map(ProductWithQuantity::getQuantity).orElse(0);
            System.out.println(p + ", Qty=" + qty);
        });
    }

    private List<String> readStringList(String promptEach) {
        InputUtilities in = new InputUtilities();
        List<String> list = new ArrayList<>();
        while (true) {
            String item = in.readLine(promptEach);
            if (item == null || item.isBlank()) break;
            list.add(item.trim());
        }
        return list;
    }

    private void editStringListInPlace(String label, List<String> list) {
        InputUtilities in = new InputUtilities();
        System.out.println("Editing " + label + " (current: " + list + ")");
        boolean done = false;
        while (!done) {
            System.out.println("1) Add  2) Remove  3) Done");
            int c = in.readInt("Choose: ", 1, 3);
            switch (c) {
                case 1 -> {
                    String s = in.readLine("Add item (blank to cancel): ");
                    if (!s.isBlank()) list.add(s.trim());
                }
                case 2 -> {
                    String s = in.readLine("Remove item by exact text: ");
                    list.removeIf(x -> x.equalsIgnoreCase(s.trim()));
                }
                case 3 -> done = true;
            }
            System.out.println("Current " + label + ": " + list);
        }
    }

    private String readOptionalString(String prompt) {
        InputUtilities in = new InputUtilities();
        System.out.print(prompt);
        return in.readLine("");
    }
}
