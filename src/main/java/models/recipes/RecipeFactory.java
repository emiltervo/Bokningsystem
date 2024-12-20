package models.recipes;


/**
 * The RecipeFactory class provides a method to create Recipe objects.
 */
public class RecipeFactory {

    /**
     * Creates a Recipe object with the specified details.
     *
     * @param recipeID the ID of the recipe
     * @param userID the ID of the user who created the recipe
     * @param content the content of the recipe
     * @return a new Recipe object with the specified details
     */
    public static Recipe createRecipe(int recipeID, long userID, String content) {
        return new Recipe(recipeID, userID, content);
    }
}