package com.floydrise.tacocloud.tacos.web;

import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.floydrise.tacocloud.tacos.attributes.Ingredient;
import com.floydrise.tacocloud.tacos.attributes.IngredientUDT;
import com.floydrise.tacocloud.tacos.data.IngredientRepository;
import com.floydrise.tacocloud.tacos.utils.TacoUDTUtils;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {
        private IngredientRepository ingredientRepository;

        public IngredientByIdConverter(IngredientRepository ingredientRepository) {
                this.ingredientRepository = ingredientRepository;
        }

        @Override
        public IngredientUDT convert(@NonNull String id) {
                Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
                if (ingredient == null) {
                        return null;
                }

                return TacoUDTUtils.toIngredientUDT(ingredient);
        }
}
