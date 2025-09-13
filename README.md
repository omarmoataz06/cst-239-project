
# Store Front — Minimal Viable Product (Cartless, No Exceptions)

A super-simple Java console app that models a store with products and an inventory.
There is **no shopping cart** in this milestone. Purchases are tracked in a simple `Map<String,Integer>` inside `StoreFront`.

# Video Link
https://www.loom.com/share/924fbfb979cb439682aa45ed4c0c3df6

> **Milestone constraints:** No validations, no exceptions, assumes valid input and existing products.

---

## Requirements

* JDK **17+** (or 11+ should work; this project uses `List.of(...)`).
* Any terminal/console.

## Project Files

```
Product.java
InventoryManager.java
StoreFront.java
App.java            # entry point (demo)
```

## Build & Run

```bash
javac *.java && java App
```

You should see inventory printed, a couple of purchases, a cancel, and the running total.

## How It Works

* **Product**: name, description, price, quantity.
* **InventoryManager**: holds products in a `Map` keyed by **name**.
* **StoreFront**: exposes required actions:

  * `initializeStore(List<Product>)`
  * `purchaseProduct(name, qty)` → decrements inventory and records the purchase
  * `cancelPurchase(name, qty)` → increments inventory and reduces recorded purchase
  * `getTotal()` → computes sum of `price * purchasedQty`
  * `printState()` → prints inventory, purchases, and total

### Why no Shopping Cart?

This milestone is intentionally minimal. Instead of a separate cart object, `StoreFront` keeps a simple `purchases` map that acts as a running receipt.

## Try It

Edit `App.java` and change the sequence of calls:

```java
sf.purchaseProduct("Coffee", 2);
sf.purchaseProduct("Mug", 1);
sf.cancelPurchase("Coffee", 1);
System.out.println("Total: $" + sf.getTotal());
```

Rebuild and run to see the effect on inventory and total.

## Limitations (intentional for MVP)

* No validation (can go negative if you misuse inputs).
* No concurrency or persistence.
* Uses `double` for money (OK for demo, not for real currency math).

## Next Steps (beyond MVP)

* Add input validation & basic error handling.
* Replace `double` with `BigDecimal` for prices.
* Extract a real **ShoppingCart** class if needed.
* Introduce product identifiers (SKU) instead of name keys.
* Add a simple CLI menu or UI and unit tests.
