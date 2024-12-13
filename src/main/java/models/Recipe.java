package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe {
    private int recipeID;
    private long userID;
    private String content;

    public Recipe(int recipeID, long userID, String content) {
        this.recipeID = recipeID;
        this.userID = userID;
        this.content = content;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Patient: " + userID + "\nRecipeID: " + recipeID + "\nAvailable prescriptions: " + content;
    }
}

