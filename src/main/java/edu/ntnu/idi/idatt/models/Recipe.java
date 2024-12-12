package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of recipe more or less explains how to make a certain type of food. An instance of
 * the recipe object is complete with ingredients, instructions, a description as well as including
 * approximations for the amounts of the different ingredients to make it easier to actually make
 * the recipes.
 *
 * @author siguraso
 * @version 3.0
 * @since 0.1
 */
public class Recipe {

  String name;
  String description;
  ArrayList<String> instructions;
  ArrayList<GroceryInstance> ingredients;
  ArrayList<String> approximations;

  /**
   * A food recipe, complete with a name, a short description, instructions, ingredients and an
   * approximation corresponding to the ingredients.
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

  /**
   * Returns the name of the {@link Recipe}. Used in the UserInterface class to display the name of
   * the recipe name.
   *
   * @return a {@link String} that contains the name of the recipe.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Fetches the ingredients of the recipe.
   *
   * @return an {@link ArrayList} containing {@link GroceryInstance} explaining the ingredients of
   * the recipe.
   */
  public ArrayList<GroceryInstance> getIngredients() {
    return this.ingredients;
  }

  /**
   * Fetches the description of the recipe.
   *
   * @return a {@link String} containing the description of the recipe.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Fetches the instructions of the recipe.
   *
   * @return an {@link ArrayList} containing {@link String} that explains the instructions of the
   */
  public ArrayList<String> getInstructions() {
    return this.instructions;
  }

  /**
   * Fetches the corresponding approximations of the ingredients in the recipe.
   *
   * @return an {@link ArrayList} containing {@link String} that explains the approximations of the
   * ingredients in the recipe.
   */
  public ArrayList<String> getApproximations() {
    return this.approximations;
  }

  // set-methods

  /**
   * Sets the name of the recipe to a new name.
   *
   * @param newName a {@link String} that contains the new name of the {@link Recipe}.
   * @throws IllegalArgumentException if newName is longer than 45 characters.
   */
  public void setName(String newName) throws IllegalArgumentException {
    if (newName.length() > 45) {
      throw new IllegalArgumentException("Name cannot be longer than 45 characters!");
    }

    this.name = newName;
  }

  /**
   * Sets a new description for the recipe.
   *
   * @param newDescription a {@link String} that contains the new description.
   */
  public void setDescription(String newDescription) {
    this.description = newDescription;
  }

  // add methods

  /**
   * Adds another ingredient to the recipe. Used in the UserInterface when creating a new
   * {@link Recipe}.
   *
   * @param newIngredient the new ingredient to add, given as a {@link GroceryInstance}
   */
  public void addIngredient(GroceryInstance newIngredient) {
    this.ingredients.add(newIngredient);
  }

  /**
   * Adds another instruction to the recipe. Used in the UserInterface when creating a new recipe
   *
   * @param newInstruction the new instruction that to add to the recipe.
   */
  public void addInstruction(String newInstruction) {
    this.instructions.add(newInstruction);
  }

  /**
   * Adds an approximation that's used to approximate an ingredient of the corresponding index to
   * make the recipe more understandable and easier to make. this method is used in the
   * UserInterface class when creating a new recipe.
   *
   * @param newApproximation {@link String} containing the approximation for a corresponding
   *                         ingredient.
   * @throws IllegalArgumentException if the approximation is longer than the character limit of
   *                                  25.
   */
  public void addApproximation(String newApproximation) throws IllegalArgumentException {
    if (newApproximation.length() > 20) {
      throw new IllegalArgumentException("An approximation cannot be longer than 20 characters!");
    }

    this.approximations.add(newApproximation);
  }

  // other methods

  /**
   * Checks weather or not you can make a certain recipe based on the available groceries in food
   * storage. Automatically leaves out out-of-date groceries.
   *
   * @param foodStorage      The Food Storage containing the available groceries.
   * @param includeOutOfDate if you want to include out of date items, leave this as true.
   * @return {@link Boolean} true if you can make the recipe, false if you can't.
   */
  public boolean canMakeRecipe(FoodStorage foodStorage, boolean includeOutOfDate) {
    // a wrapper class to hold the variables
    var variableWrapper = new Object() {
      int counter = 0;
      double totalInstanceAmount = 0;
    };

    this.getIngredients().forEach(ingredient -> {
      // reset the variables
      variableWrapper.totalInstanceAmount = 0;

      //does a search to find is there are any matching elements
      List<GroceryInstance> matchingElements = foodStorage.groceryInstanceSearch(
          ingredient.getName());

      // find the total amount
      matchingElements.forEach(instance -> {
        // count it out if it is out of date, since you dont really wanna make food thats outta date
        // also checks if it has the same measurement unit, since they can share names as well as
        // having different measurement units.

        if (!instance.isOutOfDate() && ingredient.getMeasurementUnit()
            .equals(instance.getMeasurementUnit())) {
          variableWrapper.totalInstanceAmount += instance.getAmount();
        }

        // if the user wants to check out of date food as well, and it is out of date,
        // include the grocery in the amount anyways.
        if (instance.isOutOfDate() && includeOutOfDate && ingredient.getMeasurementUnit()
            .equals(instance.getMeasurementUnit())) {
          variableWrapper.totalInstanceAmount += instance.getAmount();
        }
      });

      // if there is enough of this ingredient, add it to the counter.
      if (variableWrapper.totalInstanceAmount >= ingredient.getAmount()) {
        variableWrapper.counter++;
      }
    });

    // if there are as many eligeble groceries in the storage as there are in the length of the
    // ingredients, you can make the recipe.
    return variableWrapper.counter >= this.getIngredients().size();

  }

}
