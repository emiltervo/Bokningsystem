package models;
import models.Recipe;

public class RecipeFactory {
    public static Recipe createRecipe(int recipeID, long userID, String content) {
        return new Recipe(recipeID, userID, content);
    }
}
