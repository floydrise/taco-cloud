package com.floydrise.tacocloud.tacos.clients;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return restClient
                .get()
                .uri("/ingredients/{id}", ingredientId)
                .retrieve()
                .body(Ingredient.class);
    }

    public Ingredient postIngredient(Ingredient ingredient) {
        return restClient
                .post()
                .uri("/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .body(ingredient)
                .retrieve()
                .body(Ingredient.class);
    }

    public ResponseEntity<Void> deleteIngredientById(String id) {
        return restClient.delete().uri("/ingredients/{id}", id).retrieve().toBodilessEntity();
    }
}
