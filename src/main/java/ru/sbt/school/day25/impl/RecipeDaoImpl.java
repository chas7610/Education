package ru.sbt.school.day25.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sbt.school.day24.model.Ingredient;
import ru.sbt.school.day25.dao.HibernateSessionFactoryUtil;
import ru.sbt.school.day25.dao.RecipeDao;
import ru.sbt.school.day25.model.CookingRecipe;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDaoImpl implements RecipeDao {
    @Override
    public List<CookingRecipe> getByName(String name) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CookingRecipe where name = :name");
        query.setParameter("name", name);
        List<CookingRecipe> list = query.list();
        return list;
    }

    @Override
    public CookingRecipe getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CookingRecipe.class, id);
    }

    @Override
    public List<CookingRecipe> getAll() {
        List<CookingRecipe> list = (List<CookingRecipe>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CookingRecipe").list();
        return list;
    }

    @Override
    public List<CookingRecipe> getbyIngredient(String ingredient) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CookingRecipe where ingredient like concat('%',:ingredient,'%')");
        query.setParameter("ingredient", ingredient);
        List<CookingRecipe> list = query.list();
        return list;
    }

    @Override
    public String deleteByName(String name) {
        List<CookingRecipe> list = getByName(name);
        int cout = list.size();
        for (CookingRecipe recipe : list){
            deleteById(recipe.getId());
        }
        return "Delete " + cout + " row(s)";
    }

    @Override
    public String deleteById(int id) {
        CookingRecipe cookingRecipe = getById(id);
        if (cookingRecipe == null)
            return "no row with id " + id;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cookingRecipe);
        tx1.commit();
        return tx1.getStatus().name();
    }

    @Override
    public String insertRow(String recipe) {
        CookingRecipe cookingRecipe = new CookingRecipe();
        cookingRecipe = loadRecipeFromLine(recipe);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cookingRecipe);
        tx1.commit();
        return tx1.getStatus().name();

    }
    private CookingRecipe loadRecipeFromLine(String resipe){
        CookingRecipe cookingRecipe = new CookingRecipe();
        String [] lines = resipe.split(",");
        cookingRecipe.setName(lines[0]);
        String []ingredients = lines[1].split(";");
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String items : ingredients){
            String [] item = items.split(":");
            ingredientList.add(new Ingredient(item[0].trim(), Integer.parseInt(item[1].trim())));
        }
        cookingRecipe.setIngredientsList(ingredientList.stream().map(i -> i.toString()).collect(Collectors.joining()));
        cookingRecipe.setAuthor(lines[2]);
        return cookingRecipe;
    }
}
