package ru.sbt.school.day25.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cookingrecipe")
public class CookingRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "ingredient")
    private String ingredientsList;
//    private List<Ingredient> ingredientsList;
    @Column(name = "author")
    private String author;


    public CookingRecipe() {
    }

    public CookingRecipe(String name, String ingredientsList, String author) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CookingRecipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredientsList='" + ingredientsList + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
