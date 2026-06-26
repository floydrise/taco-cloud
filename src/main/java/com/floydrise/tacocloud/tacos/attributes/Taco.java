package com.floydrise.tacocloud.tacos.attributes;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private Date createdAt = new Date();

    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
