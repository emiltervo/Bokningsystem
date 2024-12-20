package controllers;

import views.RecipeView;

public class RecipeViewController {
    private RecipeView view;

    public RecipeViewController(RecipeView view) {
        this.view = view;
    }
}

    /*public void displayRecipe(long userID) {
        Recipe recipe = models.recipes.Recipe.fetchRecipe(userID);
        if (recipe != null) {
            view.setRecipeContent(recipe.getContent());
        } else {
            view.setRecipeContent("No recipe found for this user.");
        }
    }*/

