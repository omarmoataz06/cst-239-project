package app;

import java.util.*;


public class InventoryManager {
public final Map<String,Product> products = new LinkedHashMap<>();
public void initialize(List<Product> items){ products.clear(); for(Product p: items) products.put(p.name, p); }
public Product get(String name){ return products.get(name); } // assumes exists
public Collection<Product> list(){ return products.values(); }
}