package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;

public class Recipe {

  String name;
  String description;
  ArrayList<String> instructions;
  ArrayList<GroceryInstance> ingredients;
  ArrayList<String> approximations;

  /**
   * A food recipe, complete with a name, a short description, instructions, and ingredients.
   *
   * @param name           Name of the recipe.
   * @param description    A short description of the end product.
   * @param instructions   An ArrayList that explains the steps in the process of making this
   *                       recipe. The steps are in the same order as they are appended in the
   *                       ArrayList.
   * @param ingredients    An ArrayList that contains instances of GroceryInstance that explain
   *                       which ingredients are needed and how much is needed.
   * @param approximations An ArrayList that contains strings with corresponding indexes with
   *                       ingredients which can give approximations of how much of what ingredient
   *                       to use (for example one tablespoon instead of using an SI unit).
   */
  public Recipe(String name, String description, ArrayList<String> instructions,
      ArrayList<GroceryInstance> ingredients, ArrayList<String> approximations) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.ingredients = ingredients;
    this.approximations = approximations;
  }

  // get-methods
  public String getName() {
    return this.name;
  }

  public ArrayList<GroceryInstance> getIngredients() {
    return this.ingredients;
  }

  public String getDescription() {
    return this.description;
  }

  public ArrayList<String> getInstructions() {
    return this.instructions;
  }

  public ArrayList<String> getApproximations() {
    return this.approximations;
  }

  // set-methods
  public void setName(String name) {
    this.name = name;
  }
  // other methods

  /**
   * Checks weather or not you can make a certain recipe based on the available groceries in food
   * storage. Automatically leaves out out-of-date groceries.
   *
   * @param foodStorage      The Food Storage containing the available groceries.
   * @param includeOutOfDate if you want to include out of date items, leave this as true.
   * @return Boolean true or false weather or not you can make the recipe or not
   */
  public boolean canMakeRecipe(FoodStorage foodStorage, boolean includeOutOfDate) {

    int counter = 0;

    for (GroceryInstance ingredient : this.getIngredients()) {
      //does a search to find is there are any matching elements
      ArrayList<GroceryInstance> matchingElements = foodStorage.groceryInstanceSearch(
          ingredient.getName());

      double totalAmount = 0;

      // find the total amount
      for (GroceryInstance instance : matchingElements) {
        // count it out if it is out of date, since you dont really wanna make food thats outta date
        // also checks if it has the same measurement unit, since they can share names as well as
        // having different measurement units.

        if (!instance.isOutOfDate() &&
            ingredient.getMeasurementUnit().equals(instance.getMeasurementUnit())) {
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
    return counter >= this.getIngredients().size();

  }

}
