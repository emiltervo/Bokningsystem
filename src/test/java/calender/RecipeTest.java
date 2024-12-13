package calender;
import models.Recipe;
import org.junit.jupiter.api.Test;

public class RecipeTest {
    @Test
    public void testRecipe() {
        Recipe recipe;
        recipe = Recipe.fetchRecipe(198512158432L);
        assert recipe.getRecipeID() == 3;
        assert recipe.getContent().equals("Recipe entry for Michael Johnson");
        Recipe recipe1 = Recipe.fetchRecipe(128512158453L);
        assert recipe1 == null;
    }


}
