package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;

public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {
}
