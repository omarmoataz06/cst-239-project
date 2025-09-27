package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetFood extends SalableProduct {
    private String nutritionValue;
    private LocalDate expirationDate;
    private List<String> ingredients = new ArrayList<>();

    public PetFood() {}

    public PetFood(int id, String name, String description, LocalDate dom, double price,
                   String nutritionValue, LocalDate expirationDate, List<String> ingredients) {
        super(id, name, description, dom, price);
        this.nutritionValue = nutritionValue;
        this.expirationDate = expirationDate;
        if (ingredients != null) this.ingredients = ingredients;
    }

    public String getNutritionValue() { return nutritionValue; }
    public void setNutritionValue(String nutritionValue) { this.nutritionValue = nutritionValue; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
}
