package services;

import model.SalableProduct;

public class ProductWithQuantity {
    private final SalableProduct product;
    private int quantity;

    public ProductWithQuantity(SalableProduct product, int quantity) {
        this.product = product;
        this.quantity = Math.max(0, quantity);
    }

    public SalableProduct getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = Math.max(0, quantity); }

    @Override
    public String toString() {
        return product.toString() + ", Qty=" + quantity;
    }
}
