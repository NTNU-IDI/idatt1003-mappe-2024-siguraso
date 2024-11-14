package edu.ntnu.idi.idatt.Grocery;

import java.util.ArrayList;
import java.util.Comparator;

public class FoodStorage {

  // variables
  ArrayList<GroceryInstance> groceryInstances;
  ArrayList<GroceryType> groceryTypes;


  /**
   * A class that stores multiple instances of the GroceryInstance class. Used to view how much of
   * each grocery the user has.
   *
   * @param groceryInstances An ArrayList that takes instances of the GroceryInstance datatype.
   */
  public FoodStorage(ArrayList<GroceryInstance> groceryInstances,
      ArrayList<GroceryType> groceryTypes) {
    this.groceryInstances = groceryInstances;
    this.groceryTypes = groceryTypes;
  }

  // Get-methods

  /**
   * Fetches an instance of GroceryInstance from the food storage, based on what index is given.
   */
  public GroceryInstance getSpecificInstance(int index) {
    return this.groceryInstances.get(index - 1);
  }

  /**
   * Fetches an instance of GroceryType from the GroceryTypes ArrayList based on what index is
   * given.
   */
  public GroceryType getSpecificType(int index) {
    return this.groceryTypes.get(index - 1);
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
    for (GroceryInstance grocery : this.groceryInstances) {
      // if the name of grocery of the index i contains the search term, add it to the results list.
      if (grocery.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
        searchResults.add(grocery);
      }
    }

    return searchResults;
  }


  /**
   * Removes an instance of GroceryInstance from the food storage, based on the index given.
   *
   * @param index the index of the instance of GroceryÍnstance that the user wants to remove.
   */
  public void removeInstance(int index) {
    //here, index - 1 is used so that the lists displayed go from 1 - (n + 1), and not 0 - n
    //which is less user-friendly.
    this.groceryInstances.remove(index - 1);
  }

  /**
   * removes an instance of GroceryType from the food storage, based on the index given.
   *
   * @param index the index of the intance of GroceryType that the user wants to remove.
   */
  public void removeType(int index) {
    this.groceryTypes.remove(index - 1);
  }

  /**
   * Adds an instance of GroceryInstance to the food storage.
   */
  public void addInstance(GroceryInstance grocery) {
    this.groceryInstances.add(grocery);
  }

  /**
   * Adds an instance of GroceryType to the food storage.
   */
  public void addType(GroceryType groceryType) {
    this.groceryTypes.add(groceryType);
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
    this.groceryInstances.get(index - 1).removeAmount(amount);
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
      sum += this.groceryInstances.get(j - 1).getPrice();
    }

    return sum;
  }

  /**
   * Finds the total value of all items in food storage.
   */
  public double getTotalValue() {
    double sum = 0;

    for (GroceryInstance grocery : this.groceryInstances) {
      sum += grocery.getPrice();
    }

    return sum;
  }

  /**
   * Returns an ArrayList that contains all out of date items in the food storage.
   */
  public ArrayList<GroceryInstance> getOutOfDateInstances() {
    ArrayList<GroceryInstance> outdatedInstances = new ArrayList<>();

    for (GroceryInstance grocery : this.groceryInstances) {
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
    double sum = 0;

    for (GroceryInstance outdatedInstance : this.getOutOfDateInstances()) {
      sum += outdatedInstance.getPrice();
    }

    return sum;
  }

  /**
   * Gets all instances of GroceryInstance.
   *
   * @return An ArrayList containing all instances of GroceryInstance.
   */
  public ArrayList<GroceryInstance> getAllGroceryInstances() {
    // sorts the list of grocery instances based on what the best before date is, and then comparing
    // the name alphabetically
    this.groceryInstances.sort(Comparator.comparing(GroceryInstance::getBestBeforeDate)
        .thenComparing(GroceryInstance::getName));

    return this.groceryInstances;
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
