/**
 * Application façade that owns the InventoryManager (and later ShoppingCart).
 * Central access point for manager/customer actions.
 * Includes save/load stubs for Milestone 4.
 */

package services;

public class StoreFront {
    private InventoryManager inventoryManager;
    // private ShoppingCart cart; // Milestone 3

    public StoreFront() {
        this.inventoryManager = new InventoryManager(true);
        // this.cart = new ShoppingCart(); // Milestone 3
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    // Milestone 4 stubs
    public boolean saveInventoryToFile(String filename) { return false; }
    public boolean loadInventoryFromFile(String filename) { return false; }
}
