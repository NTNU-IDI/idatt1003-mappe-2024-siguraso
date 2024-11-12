package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;

public class Cookbook {

  ArrayList<Recipe> recipes;

  /**
   * A collection of recipes.
   *
   * @param recipes An ArrayList containing instances of the Recipe class.
   */
  public Cookbook(ArrayList<Recipe> recipes) {
    this.recipes = recipes;
  }

  // get-methods
  public Recipe getSpecificRecipe(int index) {
    return this.recipes.get(index - 1);
  }

  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  //other methods

  public void addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
  }

  /**
   * Gives recommendations for recipes based on what food is stored in the food storage. If there
   * are three (or more) groceries in the food storage that are also ingredients in a recipe, it is
   * suggested.
   *
   * @param foodStorage      Food storage that contains all of the available groceries.
   * @param includeOutOfDate boolean true or false which lets the user decide weather or not to
   *                         include out of date food.
   * @return an ArrayList consisting of recipes that are suggested.
   */
  public ArrayList<Recipe> recipeSuggestion(FoodStorage foodStorage, boolean includeOutOfDate) {
    ArrayList<Recipe> suggestions = new ArrayList<>();

    for (Recipe recipe : this.recipes) {
      int counter = 0;
      for (GroceryInstance ingredient : recipe.getIngredients()) {
        for (GroceryInstance availableGrocery : foodStorage.getAllGroceryInstances()) {
          if (ingredient.getName().equals(availableGrocery.getName())
              && !availableGrocery.isOutOfDate()) {
            counter += 1;
          }
          if (ingredient.getName().equals(availableGrocery.getName())
              && availableGrocery.isOutOfDate() && includeOutOfDate) {
            counter += 1;
          }
        }
      }

      if ((double) counter >= ((double) 1 / 2) * ((double) recipe.getIngredients().size())) {
        suggestions.add(recipe);
      }
    }

    return suggestions;
  }

}
