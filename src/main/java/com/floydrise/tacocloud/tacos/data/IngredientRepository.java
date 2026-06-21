package com.floydrise.tacocloud.tacos.data;

import java.util.List;
import java.util.Optional;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;

public interface IngredientRepository {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
