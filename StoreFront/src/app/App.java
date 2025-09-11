package app;

import java.util.*;


public class App {
public static void main(String[] args){
StoreFront sf = new StoreFront();
sf.initializeStore(List.of(
new Product("Coffee","Arabica 1kg",12.5,10),
new Product("Tea","Green tea 50 bags",6.25,20),
new Product("Mug","Ceramic mug",4.99,15)
));


sf.printState();


// purchases (no cart)
sf.purchaseProduct("Coffee",2);
sf.purchaseProduct("Mug",1);
sf.printState();


// cancel one unit of Coffee
sf.cancelPurchase("Coffee",1);
sf.printState();
}
}