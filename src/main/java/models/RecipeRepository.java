package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeRepository {
    public static ArrayList<Recipe> recipeList = new ArrayList<>();

    public static void getAllRecipes() {
        String sql = "SELECT recipeid, userid, content FROM recipes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int recipeID = resultSet.getInt("recipeID");
                long userID = resultSet.getLong("userid");
                String content = resultSet.getString("content");

                recipeList.add(RecipeFactory.createRecipe(recipeID, userID, content));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Recipe getRecipeByUserID(long userID) {
        for (Recipe recipe : recipeList) {
            if (recipe.getUserID() == userID) {
                return recipe;
            }
        }
        return null; // Return null if no user is found with the given userID
    }
}