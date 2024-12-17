package models;

/**
 * The Recipe class represents a recipe with an ID, user ID, and content.
 */
public class Recipe {
    private int recipeID;
    private long userID;
    private String content;

    /**
     * Constructs a Recipe with the specified details.
     *
     * @param recipeID the ID of the recipe
     * @param userID the ID of the user who created the recipe
     * @param content the content of the recipe
     */
    public Recipe(int recipeID, long userID, String content) {
        this.recipeID = recipeID;
        this.userID = userID;
        this.content = content;
    }

    /**
     * Gets the ID of the recipe.
     *
     * @return the ID of the recipe
     */
    public long getRecipeID() {
        return recipeID;
    }

    /**
     * Sets the ID of the recipe.
     *
     * @param recipeID the new ID of the recipe
     */
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    /**
     * Gets the content of the recipe.
     *
     * @return the content of the recipe
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the recipe.
     *
     * @param content the new content of the recipe
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the ID of the user who created the recipe.
     *
     * @return the ID of the user who created the recipe
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Returns a string representation of the Recipe.
     *
     * @return a string representation of the Recipe
     */
    @Override
    public String toString() {
        return "Patient: " + userID + "\nRecipeID: " + recipeID + "\nAvailable prescriptions: " + content;
    }
}