package com.floydrise.tacocloud.tacos.clients;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;

@Service
public class IngredientClient {
    private RestClient restClient;

    public IngredientClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return restClient.get().uri("http://localhost:8080/data-api/ingredients/{id}", urlVariables).retrieve()
                .body(Ingredient.class);
    }
}
