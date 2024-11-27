package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;

/**
 * A class that takes a collection of recipes and manages them.
 */
public class Cookbook {

  ArrayList<Recipe> recipes = new ArrayList<>();

  /**
   * A collection of recipes.
   */
  public Cookbook() {
  }

  // get-methods

  /**
   * Gets a specific recipe from the recipes in the cookbook.
   *
   * @param index the index of the recipe that the user wants to access.
   * @return the recipe the user wants to access.
   */
  public Recipe getSpecificRecipe(int index) {
    return this.recipes.get(index - 1);
  }

  /**
   * Gets all the recipes of a given cookbook.
   *
   * @return An ArrayList of instances of Recipe.
   */
  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  //other methods

  /**
   * Allows the user to add a recipe to the cookbook.
   *
   * @param recipe an instance of Recipe that the user wants to add to the cookbook.
   */
  public void addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
  }

  /**
   * Removes a user-specified recipe from the cookbook.
   *
   * @param index the index of the recipe that the user wants to remove from the cookbook.
   */
  public void removeRecipe(int index) {
    this.recipes.remove(index - 1);
  }

  /**
   * Gives recommendations for recipes based on what food is stored in the food storage. If the food
   * storage contains half or more of the ingredients in the recipe, it will be a suggested recipe.
   *
   * @param foodStorage      Food storage that contains all the available groceries.
   * @param includeOutOfDate boolean true or false which lets the user decide weather or not to
   *                         include out of date food.
   * @return an ArrayList consisting of recipes that are suggested.
   */
  public ArrayList<Recipe> recipeSuggestion(FoodStorage foodStorage, boolean includeOutOfDate) {
    ArrayList<Recipe> suggestions = new ArrayList<>();

    for (Recipe recipe : this.recipes) {
      // counter used to count the amount of shared groceries in the food storage and the recipe
      int counter = 0;
      for (GroceryInstance ingredient : recipe.getIngredients()) {
        for (GroceryInstance availableGrocery : foodStorage.getAllGroceryInstances()) {
          if (ingredient.getName().equals(availableGrocery.getName())
              && !availableGrocery.isOutOfDate()) {
            //if the grocery in the food storage has the same name as the one in the recipe
            //and it isnt the out of date, add it to the counter..
            counter += 1;
          }
          if (ingredient.getName().equals(availableGrocery.getName())
              && availableGrocery.isOutOfDate() && includeOutOfDate) {
            // if the grocery matches names, and is out of date, and the user wants to include out
            // of date groceries, add it to the counter.
            counter += 1;
          }
        }
      }

      if ((double) counter >= ((double) 1 / 2) * ((double) recipe.getIngredients().size())) {
        // if the food storage has half or more of the amount of ingredients in the recipe
        // it will be suggested.
        suggestions.add(recipe);
      }
    }

    return suggestions;
  }

}
