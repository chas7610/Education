package ru.sbt.school.day25.dao;

import ru.sbt.school.day25.model.CookingRecipe;

import java.util.List;

public interface RecipeDao {
    List<CookingRecipe> getByName(String name);
    CookingRecipe getById(int id);
    List<CookingRecipe> getAll();
    List<CookingRecipe> getbyIngredient(String ingredient);
    String deleteByName(String name);
    String deleteById(int id);
    String insertRow(String recipe);

}
