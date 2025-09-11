package app;

public class Product {
public final String name;
public final String description;
public final double price;
public int quantity;
public Product(String name, String description, double price, int quantity){
this.name=name; this.description=description; this.price=price; this.quantity=quantity;
}
@Override public String toString(){ return name+" ($"+price+") qty="+quantity; }
}