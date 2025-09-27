package app;



import services.CustomerActions;
import services.StoreManagerActions;
import services.StoreFront;
import util.InputUtilities;

public class StartingActions {

    public void start(StoreFront store) {
        InputUtilities in = new InputUtilities();

        System.out.println("==================================");
        System.out.println("  Welcome to the StoreFront App   ");
        System.out.println("==================================");

        boolean running = true;
        while (running) {
            System.out.println("\nWill you use the app as:");
            System.out.println("1) Customer");
            System.out.println("2) Store Manager");
            System.out.println("0) Exit");
            int choice = in.readInt("Enter 0, 1 or 2: ", 0, 2);

            switch (choice) {
                case 1 -> {
                    System.out.println("\nYou are using the app as a customer.");
                    new CustomerActions(store).handleCustomerActions();
                }
                case 2 -> {
                    System.out.println("\nYou are using the app as a store manager.");
                    new StoreManagerActions(store).handleManagerActions();
                }
                case 0 -> {
                    System.out.println("\nGoodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
