package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class TestRecipe {

  Recipe recipe;

  @BeforeEach
  /**
   * Sets up a new recipe object before each test.
   */
  void setUp() {
    recipe = new Recipe("name", "description", new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>());
  }

  @Nested
  @DisplayName("Negative tests for the recipe class, checks if the recipe throws exceptions at the right times.")
  public class recipeThrowsExceptions {

    @Test
    @DisplayName("setName throws an exception when the name is more than 45 chars.")
    void verifySetNameThrowsException() {
      try {
        recipe.setName("This is a name that is way too long for the name of a recipe");
        fail(
            "verifySetNameThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Name cannot be longer than 45 characters!", e.getMessage());
      }
    }

    @Test
    @DisplayName(
        "addApproximation throws an exception when the approximation is more than 20 chars.")
    void verifyAddApproximationThrowsException() {
      try {
        recipe.addApproximation("This approximation is looooooooooooong...");
        fail(
            "verifyAddApproximationThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("An approximation cannot be longer than 20 characters!",
            e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName(
      "Positive tests for the recipe class, checks if the recipe throws exceptions when it "
          + "should not.")
  public class recipeDoesNotThrowExceptions {

    @Test
    @DisplayName("new recipe does not throw an exception when creating a new instance.")
    void newRecipeDoesNotThrowException() {
      try {
        Recipe newRecipe = new Recipe("name", "description", new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>());
      } catch (IllegalArgumentException e) {
        fail(
            "newRecipeDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Getter methods get the correct values.")
    void recipeGettersVerification() {
      try {
        assertEquals("name", recipe.getName());
        assertEquals("description", recipe.getDescription());
        assertEquals(0, recipe.getIngredients().size());
        assertEquals(0, recipe.getApproximations().size());
        assertEquals(0, recipe.getInstructions().size());
      } catch (Exception e) {
        fail(
            "verifyRecipeGetters() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("setName does not throw an exception when the name is less than 45 chars.")
    void recipeSetNameDoesNotThrowException() {
      try {
        recipe.setName("This name is short enough to fit :)");
      } catch (IllegalArgumentException e) {
        fail(
            "verifySetNameDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("setDesc does not throw an exception when set.")
    void recipeSetDescDoesNotThrowException() {
      try {
        recipe.setDescription("This is a description");
      } catch (IllegalArgumentException e) {
        fail(
            "verifySetDescDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("addApproximation does not throw an exception when the approximation is less than"
        + " 20 chars.")
    void recipeAddApproximationDoesNotThrowException() {
      try {
        recipe.addApproximation("Short approximation");
        assertEquals("Short approximation", recipe.getApproximations().getFirst());
      } catch (IllegalArgumentException e) {
        fail(
            "verifyAddApproximationDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("addIngredient does not throw an exception when adding a new ingredient.")
    void recipeAddIngredientDoesNotThrowException() {
      try {
        recipe.addIngredient(new GroceryInstance(new GroceryType("tomato", "kg"),
            1, 1, null));
      } catch (IllegalArgumentException e) {
        fail(
            "verifyAddIngredientDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("addInstruction does not throw an exception when adding a new instruction.")
    void recipeAddInstructionDoesNotThrowException() {
      try {
        recipe.addInstruction("This is an instruction");
        assertEquals("This is an instruction", recipe.getInstructions().getFirst());
      } catch (IllegalArgumentException e) {
        fail(
            "recipeAddInstructionDoesNotThrowException() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("canMakeRecipe returns true when the recipe can be made, and false when it cannot.")
    void recipeCanMakeRecipe() {
      FoodStorage foodStorage = new FoodStorage();
      foodStorage.addInstance(new GroceryInstance(new GroceryType("tomato", "kg"),
          1, 1, "12.31.9999"));

      try {
        recipe.addIngredient(new GroceryInstance(new GroceryType("tomato",
            "kg"), 1, 1, null));
        assertTrue(recipe.canMakeRecipe(foodStorage, true));
        assertTrue(recipe.canMakeRecipe(foodStorage, false));

        recipe.addIngredient(new GroceryInstance(new GroceryType("otherIngredient",
            "YEP"), 1, 1, null));

        assertFalse(recipe.canMakeRecipe(foodStorage, true));
        assertFalse(recipe.canMakeRecipe(foodStorage, false));

      } catch (IllegalArgumentException e) {
        fail(
            "recipeCanMakeRecipe() failed, since it threw an exception, "
                + "message: " + e.getMessage());
      }
    }
  }
}