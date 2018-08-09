package ru.sbt.school.day25.impl;

import ru.sbt.school.day25.dao.RecipeDao;
import ru.sbt.school.day25.model.CookingRecipe;

import java.util.List;

public class CookingRecipeService {
    RecipeDao recipeDao = new RecipeDaoImpl();

    public CookingRecipeService() {
    }

    public List<CookingRecipe> getByName(String name) {
        return recipeDao.getByName(name);
    }

    public CookingRecipe getById(int id) {
        return recipeDao.getById(id);
    }
    public List<CookingRecipe> getAll(){
        List<CookingRecipe> list = recipeDao.getAll();
        return list;
    }
    public List<CookingRecipe> getbyIngredient(String ingredient){
        List<CookingRecipe> list = recipeDao.getbyIngredient(ingredient);
        return list;
    }

    public String insertRow(String recipe){
        return recipeDao.insertRow(recipe);
    }

    public String deleteRowById(int id){
        return recipeDao.deleteById(id);
    }
    public String deleteRowByName(String name){
        return recipeDao.deleteByName(name);
    }

}
