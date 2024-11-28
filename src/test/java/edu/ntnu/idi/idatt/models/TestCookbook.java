package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCookbook {

  Cookbook cookbook;

  /**
   * Makes an example cookbook with that includes one example recipe.
   */
  @BeforeEach
  public void setUp() {
    cookbook = new Cookbook();
    Recipe recipe = new Recipe("name", "description", new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>());
    recipe.addIngredient(new GroceryInstance(new GroceryType("tomato", "kg"),
        1, 1, null));

    cookbook.addRecipe(recipe);
  }

  @Nested
  @DisplayName("Negative tests for the cookbook class, checks if the cookbook throws exceptions at "
      + "the right times.")
  public class CookbookThrowsExceptions {

    @Test
    @DisplayName("Test if the cookbook throws an exception when trying to get suggestions without "
        + "any food in the food storage arraylist..")
    void verifyNoSuggestionsFoundDialog() {
      try {
        // runs both true and false to see if we can catch the exception in both cases.
        // these should throw an exception since there are no items in the food storage arraylist.
        cookbook.recipeSuggestion(new ArrayList<>(), true);
        cookbook.recipeSuggestion(new ArrayList<>(), false);
        fail(
            "verifyNoSuggestionsFoundDialog() failed, since it didnt return an "
                + "IllegalStateException.");
      } catch (IllegalStateException e) {
        assertEquals("Found no recommendations, try adding more groceries to the "
            + "food storage!", e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for the cookbook class, checks if the cookbook throws exceptions "
      + "when it should not.")
  public class CookbookDoesNotThrowExceptions {

    @Test
    @DisplayName("Test if the cookbook does not throw any exceptions when creating a new instance.")
    void newCookbookDoesNotThrowException() {
      try {
        Cookbook testCookbook = new Cookbook();
        assertNotNull(testCookbook);
      } catch (Exception e) {
        fail("newCookbookDoesNotThrowException() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("Cookbook getter methods fetches a recipe, test the getters.")
    void testCookbookGetters() {
      try {
        assertEquals(1, cookbook.getRecipes().size());
        assertEquals("name", cookbook.getSpecificRecipe(1).getName());
        assertEquals("description", cookbook.getSpecificRecipe(1).getDescription());
        assertEquals(1, cookbook.getSpecificRecipe(1).getIngredients().size());
      } catch (Exception e) {
        fail("testCookbookGetters() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("removeRecipe method actually the test recipe, leaving the cookbook empty.")
    void testRemoveRecipe() {
      try {
        cookbook.removeRecipe(1);
        assertTrue(cookbook.getRecipes().isEmpty());
      } catch (Exception e) {
        fail("testRemoveRecipe() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("addRecipe method adds a recipe to the cookbook.")
    void testAddRecipe() {
      try {
        Recipe recipe = new Recipe("name", "description", new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>());
        recipe.addIngredient(new GroceryInstance(new GroceryType("tomato", "kg"),
            1, 1, null));
        cookbook.addRecipe(recipe);
        assertEquals(2, cookbook.getRecipes().size());
      } catch (Exception e) {
        fail("testAddRecipe() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("recipeSuggestion method returns a recipe when the food storage contains the "
        + "ingredients for the recipe.")
    void testRecipeSuggestion() {
      try {
        ArrayList<GroceryInstance> groceryInstances = new ArrayList<>();
        groceryInstances.add(new GroceryInstance(new GroceryType("tomato",
            "kg"), 1, 1, "12.31.9999"));

        ArrayList<Recipe> suggestions = cookbook.recipeSuggestion(groceryInstances,
            true);

        assertEquals(1, suggestions.size());
      } catch (IllegalStateException e) {
        fail("testRecipeSuggestion() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }
  }
}
