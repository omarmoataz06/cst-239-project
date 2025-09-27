package services;



import util.InputUtilities;

public class CustomerActions {
    private final StoreFront store;

    public CustomerActions(StoreFront store) {
        this.store = store;
    }

    public void handleCustomerActions() {
        InputUtilities in = new InputUtilities();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1) View Products (placeholder)");
            System.out.println("2) Add to Cart (placeholder)");
            System.out.println("3) Remove from Cart (placeholder)");
            System.out.println("4) View Cart (placeholder)");
            System.out.println("5) Checkout (placeholder)");
            System.out.println("0) Back to Main Menu");

            int choice = in.readInt("Select option: ", 0, 5);

            switch (choice) {
                case 1 -> System.out.println("[Placeholder] Displaying products...");
                case 2 -> System.out.println("[Placeholder] Add product to cart...");
                case 3 -> System.out.println("[Placeholder] Remove product from cart...");
                case 4 -> System.out.println("[Placeholder] Viewing cart...");
                case 5 -> System.out.println("[Placeholder] Checking out...");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

