package app;

import java.util.*;


public class StoreFront {
public final InventoryManager inv = new InventoryManager();
// track simple purchases (acts like a receipt), no cart
public final Map<String,Integer> purchases = new LinkedHashMap<>();


public void initializeStore(List<Product> products){ inv.initialize(products); }


// purchase: decrement inventory and record purchase (assumes valid)
public void purchaseProduct(String name, int qty){
inv.get(name).quantity -= qty;
purchases.put(name, purchases.getOrDefault(name,0) + qty);
}


// cancel: undo purchase (increment inventory and unrecord)
public void cancelPurchase(String name, int qty){
inv.get(name).quantity += qty;
int left = purchases.getOrDefault(name,0) - qty;
if(left <= 0) purchases.remove(name); else purchases.put(name, left);
}


public double getTotal(){
double sum = 0;
for(var e: purchases.entrySet()) sum += inv.get(e.getKey()).price * e.getValue();
return sum;
}


public void printState(){
System.out.println("INVENTORY:");
for(Product p: inv.list()) System.out.println(" "+p);
System.out.println("PURCHASES:");
if(purchases.isEmpty()) System.out.println(" (none)");
else for(var e: purchases.entrySet()) System.out.println(" "+e.getKey()+" x"+e.getValue());
System.out.println("Total: $"+getTotal());
}
}