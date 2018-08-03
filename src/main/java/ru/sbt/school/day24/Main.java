package ru.sbt.school.day24;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import ru.sbt.school.day24.config.Config;
import ru.sbt.school.day24.dao.RecipeDao;
import ru.sbt.school.day24.model.CookingRecipe;

import javax.sql.DataSource;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DataAccessException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        RecipeDao recipeDao = ctx.getBean(RecipeDao.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        recipeDao.setDataSource(dataSource);

//        List<CookingRecipe> list1 = recipeDao.getbyIngredient("pepper");
//        list1.stream().forEach(System.out::println);
//        System.out.println("");
//
//        List<CookingRecipe> list =  recipeDao.getAll();
//        list.stream().forEach(System.out::println);
//        System.out.println("");
//
//        CookingRecipe recipe = recipeDao.getByName("salad");
//        System.out.println(recipe);
//
//        System.out.println("");
//
//        System.out.println(recipeDao.deleteById(5));
        String lines = "salad, potatoes:250; pineapple:100;chicken:50;mayonnaise:10,I";

        System.out.println(recipeDao.insertRow(lines));

        List<CookingRecipe> list =  recipeDao.getAll();
        list.stream().forEach(System.out::println);
        System.out.println("");



    }

}
