package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;

public class FoodStorage {

  ArrayList<GroceryInstance> groceries;
  ArrayList<GroceryType> groceryTypes = new ArrayList<>();


  /**
   * A class that stores multiple instances of the GroceryInstance class. Used to view how much of
   * each grocery the user has.
   *
   * @param groceries An ArrayList that takes instances of the GroceryInstance datatype.
   */
  public FoodStorage(ArrayList<GroceryInstance> groceries) {
    this.groceries = groceries;

    // adds a bunch of basic and common GroceryTypes, so that the user doesn't have to.
    this.groceryTypes.add(new GroceryType("Tomato", "kg"));
    this.groceryTypes.add(new GroceryType("Milk", "L"));
    this.groceryTypes.add(new GroceryType("Bread", "pcs."));
    this.groceryTypes.add(new GroceryType("Apple", "kg"));
    this.groceryTypes.add(new GroceryType("Potato", "kg"));
    this.groceryTypes.add(new GroceryType("Butter", "pcs."));
    this.groceryTypes.add(new GroceryType("Cucumber", "pcs."));
  }

  // Get-methods

  /**
   * Fetches an instance of a GroceryInstance from the food storage, based on what index is given.
   */
  public GroceryInstance getSpecificInstance(int index) {
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
    for (GroceryInstance grocery : this.groceries) {
      // if the name of grocery of the index i contains the search term, add it to the results list.
      if (grocery.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
        searchResults.add(grocery);
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
    for (int j : index) {
      sum += this.groceries.get(j - 1).getPrice();
    }

    return sum;
  }

  /**
   * Finds the total value of all items in food storage.
   */
  public double getTotalValue() {
    double sum = 0;

    for (GroceryInstance grocery : this.groceries) {
      sum += grocery.getPrice();
    }

    return sum;
  }

  /**
   * Returns an ArrayList that contains all out of date items in the food storage.
   */
  public ArrayList<GroceryInstance> getOutOfDateInstances() {
    ArrayList<GroceryInstance> outdatedInstances = new ArrayList<>();

    for (GroceryInstance grocery : this.groceries) {
      if (grocery.isOutOfDate()) {
        outdatedInstances.add(grocery);
      }
    }

    return outdatedInstances;
  }

  /**
   * Returns the total value of all out of date items.
   */
  public double getOutOfDateValue() {
    ArrayList<GroceryInstance> outdatedInstances = this.getOutOfDateInstances();
    double sum = 0;

    for (GroceryInstance outdatedInstance : outdatedInstances) {
      sum += outdatedInstance.getPrice();
    }

    return sum;
  }

  /**
   * Gets all instances of GroceryInstance.
   *
   * @return An ArrayList containing all instances of GroceryInstance.
   */
  public ArrayList<GroceryInstance> getAllGroceries() {
    return this.groceries;
  }

  /**
   * Gets all instances of GroceryType
   *
   * @return An ArrayList containing all instances of GroceryType.
   */
  public ArrayList<GroceryType> getAllGroceryTypes() {
    return this.groceryTypes;
  }
}
