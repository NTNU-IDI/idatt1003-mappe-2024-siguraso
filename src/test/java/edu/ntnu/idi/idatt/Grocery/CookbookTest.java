package edu.ntnu.idi.idatt.Grocery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class CookbookTest {

  public ArrayList<Recipe> recipes() {
    GroceryType tomato = new GroceryType("Tomato", "kg");
    GroceryType milk = new GroceryType("Milk", "L");
    GroceryType apple = new GroceryType("Apple", "kg");
    GroceryType potato = new GroceryType("Potato", "kg");
    GroceryType butter = new GroceryType("Butter", "pcs.");

    ArrayList<GroceryInstance> ingredients1 = new ArrayList<>();

    ingredients1.add(new GroceryInstance(tomato, 2, 40, "10.11.2024"));
    ingredients1.add(new GroceryInstance(milk, 2, 40, "24.12.2024"));

    ArrayList<String> instructions1 = new ArrayList<>();

    instructions1.add("cook the tomatos or sum in the milk");
    instructions1.add("make sure to not burn yaself on the pan");
    instructions1.add("eat it");

    Recipe scuffedTomatoSoup = new Recipe("Tomato Soup",
        "very dubious looking tomato soup", instructions1, ingredients1, null);

    ArrayList<GroceryInstance> ingredients2 = new ArrayList<>();

    ingredients2.add(new GroceryInstance(butter, 2, 40, "12.10.2024"));
    ingredients2.add(new GroceryInstance(apple, 2, 40, "24.12.2024"));
    ingredients2.add(new GroceryInstance(potato, 2, 40, "24.12.2024"));

    ArrayList<String> instructions2 = new ArrayList<>();
    instructions2.add("uhhh put it all in a caserole");
    instructions2.add("cook allat in some water");
    instructions2.add(
        "try to not create a massive rift in space-time because you've created some of the most "
            + "disgusting food ever seen on the face of the earth...");

    Recipe momsHomemadeCaserole = new Recipe("Mom's classic homemade caserole",
        "Caserole, the way our mothers always used to cook (if they abolutely hated us)",
        instructions2, ingredients2, null);

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(scuffedTomatoSoup);
    recipes.add(momsHomemadeCaserole);
    return recipes;
  }

  public Cookbook cookbook() {
    return new Cookbook(recipes());
  }

  @Test
  void getSpecificRecipe() {
    assertEquals("Tomato Soup", cookbook().getSpecificRecipe(1).getName());
    assertEquals("Mom's classic homemade caserole",
        cookbook().getSpecificRecipe(2).getName());
  }

  @Test
  void getRecipes() {
    ArrayList<Recipe> recipes = cookbook().getRecipes();

    assertEquals(2, recipes.size());
    assertEquals("Tomato Soup", recipes.getFirst().getName());
    assertEquals("Mom's classic homemade caserole", recipes.get(1).getName());
  }

  @Test
  void recipeSuggestion() {
    ArrayList<GroceryInstance> ingredients2 = new ArrayList<>();

    GroceryType milk = new GroceryType("Milk", "L");
    GroceryType potato = new GroceryType("Potato", "kg");
    GroceryType butter = new GroceryType("Butter", "pcs.");

    ingredients2.add(new GroceryInstance(butter, 2, 40, "12.10.2024"));
    ingredients2.add(new GroceryInstance(potato, 2, 40, "24.12.2024"));

    ingredients2.add(new GroceryInstance(milk, 2, 40, "12.12.2024"));

    FoodStorage foodStorage = new FoodStorage(ingredients2, null);

    ArrayList<Recipe> suggestions = cookbook().recipeSuggestion(foodStorage, true);

    assertEquals(2, suggestions.size());

    ArrayList<Recipe> suggestions2 = cookbook().recipeSuggestion(foodStorage, false);

    assertEquals(1, suggestions2.size());
  }
}