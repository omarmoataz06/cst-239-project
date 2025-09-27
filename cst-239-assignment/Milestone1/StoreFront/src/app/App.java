package app;

import services.StoreFront;

public class App {
    public static void main(String[] args) {
        // Create the StoreFront (now includes an InventoryManager with seed data)
        StoreFront store = new StoreFront();

        // Start the role selector and menus
        StartingActions startingActions = new StartingActions();
        startingActions.start(store);
    }
}
