package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;

/**
 * A class that takes a collection of recipes and manages them.
 *
 * @author siguraso
 * @version 3.0
 * @since 0.1
 */
public class Cookbook {

  ArrayList<Recipe> recipes = new ArrayList<>();

  /**
   * A collection of recipes. The class is used to manage the recipes in the collection.
   */
  public Cookbook() {
  }

  // get-methods

  /**
   * Returns a specific recipe from the recipes in the cookbook.
   *
   * @param index the index of the recipe that the user wants to access.
   * @return the recipe the user wants to access.
   */
  public Recipe getSpecificRecipe(int index) {
    return this.recipes.get(index - 1);
  }

  /**
   * Returns all the recipes in the cookbook in an {@link ArrayList}.
   *
   * @return An {@link ArrayList} of instances of Recipe.
   */
  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  //other methods

  /**
   * Allows the user to add a recipe to the cookbook.
   *
   * @param recipe an instance of {@link Recipe} that the user wants to add to the cookbook.
   */
  public void addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
  }

  /**
   * Removes a {@link Recipe} from the cookbook based on what index is given.
   *
   * @param index the index of the {@link Recipe} that the user wants to remove from the cookbook.
   */
  public void removeRecipe(int index) {
    this.recipes.remove(index - 1);
  }

  /**
   * Gives recommendations for recipes based on what food is stored in the food storage. If the food
   * storage contains half or more of the ingredients in the recipe, it will be a suggested recipe.
   *
   * @param foodStorage      {@link ArrayList} of {@link GroceryInstance} that contains the food
   *                         from food storage
   * @param includeOutOfDate boolean true or false which lets the user decide weather or not to
   *                         include out of date food.
   * @return an ArrayList consisting of recipes that are suggested.
   * @throws IllegalStateException if no recommendations are found.
   */
  public ArrayList<Recipe> recipeSuggestion(ArrayList<GroceryInstance> foodStorage,
      boolean includeOutOfDate) throws IllegalStateException {
    ArrayList<Recipe> suggestions = new ArrayList<>();

    this.recipes.forEach(recipe -> {
      // wrapper for the counter to count the amount of shared groceries in the food storage and the
      // recipe
      var counterWrapper = new Object() {
        int counter = 0;
      };

      recipe.getIngredients().forEach(ingredient ->
          foodStorage.forEach(availableGrocery -> {
            if (ingredient.getName().equals(availableGrocery.getName())
                && !availableGrocery.isOutOfDate()) {
              //if the grocery in the food storage has the same name as the one in the recipe
              //and it isnt the out of date, add it to the counter..
              counterWrapper.counter++;
            }
            if (ingredient.getName().equals(availableGrocery.getName())
                && availableGrocery.isOutOfDate() && includeOutOfDate) {
              // if the grocery matches names, and is out of date, and the user wants to include out
              // of date groceries, add it to the counter.
              counterWrapper.counter++;
            }
          })
      );

      if ((double) counterWrapper.counter >= ((double) 1 / 2) * ((double) recipe.getIngredients()
          .size())) {
        // if the food storage has half or more of the amount of ingredients in the recipe
        // it will be suggested.
        suggestions.add(recipe);
      }
    });

    if (suggestions.isEmpty()) {
      throw new IllegalStateException("Found no recommendations, try adding more groceries to the "
          + "food storage!");
    }
    return suggestions;
  }

}
