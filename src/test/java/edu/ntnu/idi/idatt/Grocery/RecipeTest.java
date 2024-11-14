package edu.ntnu.idi.idatt.Grocery;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class RecipeTest {

  Recipe r() {
    GroceryType cakeMix = new GroceryType("Cake Mix", "pcs.");
    GroceryType egg = new GroceryType("Egg", "pcs.");

    GroceryInstance makeCix = new GroceryInstance(cakeMix, 1, 40, null);
    GroceryInstance lEgg = new GroceryInstance(egg, 1, 40, null);

    ArrayList<GroceryInstance> ingredients = new ArrayList<>();
    ingredients.add(makeCix);
    ingredients.add(lEgg);

    ArrayList<String> instructions = new ArrayList<>();
    instructions.add("make your cake");
    instructions.add("eat it too");

    return new Recipe("cake", "mmmm, cake", instructions, ingredients, null);
  }

  @Test
  void getName() {
    assertEquals("cake", r().getName());
  }

  @Test
  void getIngredients() {
    assertEquals("Cake Mix", r().getIngredients().get(0).getName());
    assertEquals("Egg", r().getIngredients().get(1).getName());
  }

  @Test
  void getDescription() {
    assertEquals("mmmm, cake", r().getDescription());
  }

  @Test
  void getInstructions() {
    assertEquals("make your cake", r().getInstructions().get(0));
    assertEquals("eat it too", r().getInstructions().get(1));
  }

  @Test
  void setName() {
    Recipe badCake = r();
    badCake.setName("Bad cake");
    assertEquals("Bad cake", badCake.getName());
  }

  @Test
  void canMakeRecipe() {
    GroceryType cakeMix = new GroceryType("Cake Mix", "pcs.");
    GroceryType egg = new GroceryType("Egg", "pcs.");

    ArrayList<GroceryInstance> ingredients2 = new ArrayList<>();

    ingredients2.add(new GroceryInstance(cakeMix, 0.5, 40, "10.10.2024"));
    ingredients2.add(new GroceryInstance(cakeMix, 0.5, 40, "12.12.2024"));
    ingredients2.add(new GroceryInstance(egg, 1, 40, "12.12.2024"));

    FoodStorage foodStorage = new FoodStorage(ingredients2, null);

    assertTrue(r().canMakeRecipe(foodStorage, true));

    assertFalse(r().canMakeRecipe(foodStorage, false));

  }
}