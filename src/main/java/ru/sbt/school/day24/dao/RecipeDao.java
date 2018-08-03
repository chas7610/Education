package ru.sbt.school.day24.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ru.sbt.school.day24.model.CookingRecipe;
import ru.sbt.school.day24.model.Ingredient;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeDao {
    private DataSource dataSource;


    public RecipeDao() {
    }


    public RecipeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CookingRecipe> getAll(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<CookingRecipe> list= jdbcTemplate.query("select * from cookingrecipe", new RecipeRowMapper());
        return list;
    }

    public CookingRecipe getByName(String name){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<CookingRecipe> list = jdbcTemplate.query("select * from cookingrecipe where name=?", new PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, name);
            }
        }, new RecipeRowMapper());
        return list!=null && list.size()>0?list.get(0):null;
    }

    public List<CookingRecipe> getbyIngredient(String ingredient){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<CookingRecipe> list = jdbcTemplate.query("select * from cookingrecipe where ingredient like ?", new PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, '%'+ ingredient + '%');
            }
        }, new RecipeRowMapper());
        return list==null && list.isEmpty()? null: list;
    }

    public String deleteByName(String name){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int row = jdbcTemplate.update("delete FROM COOKINGRECIPE where name=?",new  PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, name);
            }});
        return "Delete " + row + " row(s) from table";
    }

    public String deleteById(int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int row = jdbcTemplate.update("delete FROM COOKINGRECIPE where id=?",new  PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }});
        return "Delete " + row + " row(s) from table";
    }

    public String insertRow(String recipe){
        String []lines = "salad, potatoes:250; pineapple:100;chicken:50;mayonnaise:10,I}".split(",");
        CookingRecipe cookingRecipe = loadRecipeFromLine(recipe);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int row = jdbcTemplate.update("INSERT INTO cookingrecipe(name,ingredient, author) VALUES (?,?,?)",new  PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, cookingRecipe.getName());
                preparedStatement.setString(2, cookingRecipe.getIngredientsList().toString());
                preparedStatement.setString(3, cookingRecipe.getAuthor());
            }});

        return "Add "+ row + " row to table";
    }
    private CookingRecipe loadRecipeFromLine(String resipe){
        CookingRecipe cookingRecipe = new CookingRecipe();
        String [] lines = resipe.split(",");
        cookingRecipe.setName(lines[0]);
        String []ingredients = lines[1].split(";");
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String items : ingredients){
            String [] item = items.split(":");
            ingredientList.add(new Ingredient(item[0], Integer.parseInt(item[1].trim())));
        }
        cookingRecipe.setIngredientsList(ingredientList);
        cookingRecipe.setAuthor(lines[2]);
        return cookingRecipe;
    }


    class RecipeRowMapper implements RowMapper<CookingRecipe>{
        @Override
        public CookingRecipe mapRow(ResultSet resultSet, int i) throws SQLException {
            CookingRecipe recipe = new CookingRecipe();
            recipe.setId(resultSet.getInt("id"));
            recipe.setName(resultSet.getString("name"));
            String [] ingredients = resultSet.getString("ingredient").split(";");
            List<Ingredient> ingredientList = new ArrayList<>();
            for (String items : ingredients){
                String [] item = items.split(":");
                ingredientList.add(new Ingredient(item[0], Integer.parseInt(item[1].trim())));
            }
            recipe.setIngredientsList(ingredientList);
            recipe.setAuthor(resultSet.getString("author"));
            return recipe;
        }
    }
}
