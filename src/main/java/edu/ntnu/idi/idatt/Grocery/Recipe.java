package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;

public class Recipe {

  String name;
  String description;
  ArrayList<String> instructions;
  ArrayList<GroceryInstance> ingredients;

  /**
   * A food recipe, complete with a name, a short description, instructions, and ingredients.
   *
   * @param name         Name of the recipe.
   * @param description  A short description of the end product.
   * @param instructions An ArrayList that explains the steps in the process of making this recipe.
   *                     The steps are in the same order as they are appended in the ArrayList.
   * @param ingredients  An ArrayList that contains instances of GroceryInstance that explain which
   *                     ingredients are needed and how much is needed.
   */
  public Recipe(String name, String description, ArrayList<String> instructions,
      ArrayList<GroceryInstance> ingredients) {
    this.name = name;
    this.instructions = instructions;
    this.ingredients = ingredients;
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

  // set-methods
  public void setName(String name) {
    this.name = name;
  }


}
