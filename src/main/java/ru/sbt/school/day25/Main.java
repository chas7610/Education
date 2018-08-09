package ru.sbt.school.day25;

import ru.sbt.school.day25.dao.HibernateSessionFactoryUtil;
import ru.sbt.school.day25.impl.CookingRecipeService;
import ru.sbt.school.day25.model.CookingRecipe;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CookingRecipeService service = new CookingRecipeService();
//        get all rows
        List<CookingRecipe> list = service.getAll();
        list.stream().forEach(System.out::println);
//        get row(s) by name
        service.getByName("salad_Young").stream().forEach(System.out::println);
//        get row by ingrediant %like%
        service.getbyIngredient("pineapple").stream().forEach(System.out::println);
//        insert row
        String lines = "salad_Young, red potatoes:250; grean pineapple:100; young chicken:50;mayonnaise:5,I";
        service.insertRow(lines);
        service.getAll().stream().forEach(System.out::println);
//        delete row by id
        service.deleteRowById(45);
        service.getAll().stream().forEach(System.out::println);
//        delete row(s) by name
        System.out.println(service.deleteRowByName("salad_Young"));
        service.getAll().stream().forEach(System.out::println);

        HibernateSessionFactoryUtil.close();
    }
}
