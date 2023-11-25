package com.example.recette_backend.controller;

import com.example.recette_backend.Service.RecipeService;
import com.example.recette_backend.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        Optional<Recipe> recipeData = recipeService.findById(id);

        if (recipeData.isPresent()) {
            Recipe updatedRecipe = recipeData.get();
            updatedRecipe.setName(recipeDetails.getName());
            updatedRecipe.setIngredients(recipeDetails.getIngredients());
            updatedRecipe.setSteps(recipeDetails.getSteps());
            updatedRecipe.setPreparationTime(recipeDetails.getPreparationTime());
            updatedRecipe.setPhoto(recipeDetails.getPhoto());
            return new ResponseEntity<>(recipeService.save(updatedRecipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}