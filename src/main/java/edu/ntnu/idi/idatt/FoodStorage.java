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
   * Fetches an instance of a GroceryInstance from the food storage, based on what index is given.
   */
  GroceryInstance getSpecificInstance(int index) {
    return this.groceries.get(index - 1);
  }

  /**
   * Goes through the groceries ArrayList and checks if a user-specified search term matches any of
   * the names of the items in the ArrayList.
   *
   * @param searchTerm The search term specified by the user.
   * @return Returns an ArrayList of all the items that matched the users search term.
   */
  public ArrayList<GroceryInstance> groceryInstanceSearch(String searchTerm) {
    ArrayList<GroceryInstance> searchResults = new ArrayList<>();

    // for-loop to check if any of the instances of GroceryInstance contains the search term string.
    for (int i = 0; i < this.groceries.size(); i++) {
      // if the name of grocery of the index i contains the search term, add it to the results list.
      if (this.groceries.get(i).getName().toLowerCase().contains(searchTerm.toLowerCase())) {
        searchResults.add(this.groceries.get(i));
      }
    }

    return searchResults;
  }


  /**
   * Removes an instance of GroceryInstance from the food storage, based on the index given.
   */
  public void removeInstance(int index) {
    this.groceries.remove(index - 1);
  }

  /**
   * Removes a specific amount from a given grocery from the groceries ArrayList based on the index
   * given.
   *
   * @param index  Specifies which grocery the user wants to remove from.
   * @param amount Specifies the amount of the grocery the user wants to remove (based on the
   *               measurement unit defined in the groceryType object).
   */
  public void removeInstanceAmount(int index, double amount) {
    this.groceries.get(index - 1).removeAmount(amount);
  }

  /**
   * Finds the total value of specified GroceryInstances.
   *
   * @param index The indexes the user wants to find the combined value of.
   */
  public double getSpecificValue(int[] index) {
    double sum = 0;

    // for-loop for each item in the index table.
    for (int i = 0; i < index.length; i++) {
      sum += this.groceries.get(index[i]).getPrice();
    }

    return sum;
  }

  public double getTotalValue() {
    double sum = 0;

    for (GroceryInstance grocery : this.groceries) {
      sum += grocery.getPrice();
    }

    return sum;
  }


}
