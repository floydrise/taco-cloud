package com.floydrise.tacocloud.tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;
import com.floydrise.tacocloud.tacos.attributes.Ingredient.Type;
import com.floydrise.tacocloud.tacos.clients.IngredientClient;

@Controller
@RequestMapping("/ingredient")
public class IngredientByIdController {
    private IngredientClient ingredientClient;

    public IngredientByIdController(IngredientClient ingredientClient) {
        this.ingredientClient = ingredientClient;
    }

    @GetMapping("/{id}")
    public String printIngredient(@PathVariable String id) {
        Ingredient ingredient = ingredientClient.getIngredientById(id);
        System.out.println(ingredient);
        return "ingredientTemplate";
    }

    @PostMapping
    public String createIngredient() {
        Ingredient ingredient = ingredientClient.postIngredient(new Ingredient(
                "VEGN", "Vegan Tortilla", Type.WRAP));
        System.out.println(ingredient);
        return "redirect:/";
    }
}
