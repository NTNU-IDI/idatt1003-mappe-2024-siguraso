package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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
  public Recipe getRecipe(int index) {
    return this.recipes.get(index);
  }

  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  //other methods

  /**
   * Checks weather or not you can make a certain recipe based on the available groceries in food
   * storage. Automatically leaves out out-of-date groceries.
   *
   * @param index            The index (in the ArrayList of recipes) of the recipe you want to
   *                         check.
   * @param foodStorage      The Food Storage containing the available groceries.
   * @param includeOutOfDate if you want to include out of date items, leave this as true.
   * @return Boolean true or false weather or not you can make the recipe or not
   */
  public boolean canMakeRecipe(int index, FoodStorage foodStorage, boolean includeOutOfDate) {
    Recipe recipe = this.getRecipe(index);

    int counter = 0;

    for (GroceryInstance ingredient : recipe.getIngredients()) {
      //does a search to find is there are any matching elements
      ArrayList<GroceryInstance> matchingElements = foodStorage.groceryInstanceSearch(
          ingredient.getName());

      double totalAmount = 0;

      // find the total amount
      for (GroceryInstance instance : matchingElements) {
        // count it out if it is out of date, since you dont really wanna make food thats outta date
        // also checks if it has the same measurement unit, since they can share names as well as
        // having different measurement units.

        if (!instance.isOutOfDate() && ingredient.getMeasurementUnit()
            .equals(instance.getMeasurementUnit())) {
          totalAmount += instance.getAmount();
        }

        // if the user wants to check out of date food as well, and it is out of date,
        // include the grocery in the amount anyways.
        if (instance.isOutOfDate() && includeOutOfDate && ingredient.getMeasurementUnit()
            .equals(instance.getMeasurementUnit())) {
          totalAmount += instance.getAmount();
        }
      }

      // if there is enough of this ingredient, add it to the counter.
      if (totalAmount >= ingredient.getAmount()) {
        counter++;
      }
    }

    // if there are as many eligeble groceries in the storage as there are in the length of the
    // ingredients, you can make the recipe.
    return counter == recipe.getIngredients().size();

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
      for (GroceryInstance ingredient : recipe.getIngredients()) {
        int counter = 0;
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
        if (counter >= 3) {
          suggestions.add(recipe);
        }
      }
    }

    return suggestions;
  }

}
