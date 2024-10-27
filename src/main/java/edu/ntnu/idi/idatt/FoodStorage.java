package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class FoodStorage {

  ArrayList<GroceryInstance> groceries = new ArrayList<>();

  /**
   * A class that stores multiple instances of the GroceryInstance class. Used to view how much of
   * each grocery the user has.
   *
   * @param groceries An ArrayList that takes instances of the GroceryInstance datatype.
   */
  FoodStorage(ArrayList<GroceryInstance> groceries) {
    this.groceries = groceries;
  }

  // Get-methods

  /**
   * Fetches an instance of a GroceryInstance based on what index is given.
   *
   * @param index Index of the groceries list.
   */
  GroceryInstance getSpecificInstance(int index) {
    return this.groceries.get(index - 1);
  }


}
