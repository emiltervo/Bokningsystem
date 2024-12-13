package controllers;

import models.Recipe;
import views.RecipeView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeViewController {
    private RecipeView view;

    public RecipeViewController(RecipeView view) {
        this.view = view;
    }
}

    /*public void displayRecipe(long userID) {
        Recipe recipe = models.Recipe.fetchRecipe(userID);
        if (recipe != null) {
            view.setRecipeContent(recipe.getContent());
        } else {
            view.setRecipeContent("No recipe found for this user.");
        }
    }*/

