package com.floydrise.tacocloud.tacos.attributes;

import lombok.Data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.floydrise.tacocloud.tacos.IngredientRef;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Table
public class Taco {
    @Id
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<IngredientRef> ingredients;
}
