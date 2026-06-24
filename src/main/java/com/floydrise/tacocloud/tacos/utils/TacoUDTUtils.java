package com.floydrise.tacocloud.tacos.utils;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;
import com.floydrise.tacocloud.tacos.attributes.IngredientUDT;
import com.floydrise.tacocloud.tacos.attributes.Taco;
import com.floydrise.tacocloud.tacos.attributes.TacoUDT;

public class TacoUDTUtils {
    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }
}
