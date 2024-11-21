package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * an instance of FoodStorage contains both GroceryInstances and GroceryTypes that are both
 * pre-defined and defined by the user. FoodStorage object's main goals are to more or less manage
 * these objects.
 */
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
    this.groceryInstances.sort(Comparator.comparing(GroceryInstance::getName)
        .thenComparing(GroceryInstance::getBestBeforeDate));

    this.removeEmptyInstances();

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

  // other methods

  /**
   * Goes through the groceries ArrayList and checks if a user-specified search term matches any of
   * the names of the items in the ArrayList.
   *
   * @param searchTerm The search term specified by the user.
   * @return Returns an ArrayList of all the items that matched the users search term.
   */
  public List<GroceryInstance> groceryInstanceSearch(String searchTerm) {

    return this.groceryInstances.stream()
        .filter(GI -> GI.getName().toLowerCase().contains(searchTerm.toLowerCase())).toList();
  }

  // add/remove methods

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
   * @param index the index of the instance of GroceryType that the user wants to remove.
   */
  public void removeType(int index) {
    this.groceryTypes.remove(index - 1);
  }

  /**
   * Adds an instance of GroceryInstance to the food storage.
   */
  public void addInstance(GroceryInstance grocery) {
    this.groceryInstances.forEach(GI -> {
      // if there is a grocery with the same best before date, add the amount to the same Grocery
      // instance.
      if (this.isSameInstance(GI, grocery)) {
        GI.addAmount(grocery.getAmount());
        // set it to 0, so that it isn't added later on.
        grocery.setAmount(0);
      }
    });

    //if the grocery has been added and is equal to zero, it will be removed next time the program
    //gets the groceryInstances ArrayList.
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
   * Removes all elements from the groceries ArrayList that are equal to zero.
   */
  public void removeEmptyInstances() {
    this.groceryInstances.forEach(GI -> {
      if (GI.getAmount() == 0) {
        this.groceryInstances.remove(GI);
      }
    });
  }

  // boolean methods

  /**
   * Checks if two GroceryInstances are of the same GroceryType
   *
   * @return true is they are the same GroceryType, false if they aren't.
   */
  private boolean isSameType(GroceryInstance G1, GroceryInstance G2) {
    return G1.getName().equals(G2.getName()) && G1.getMeasurementUnit()
        .equals(G2.getMeasurementUnit());
  }

  /**
   * Checks if two GroceryInstances can be melted together if they essentially are the same
   * GroceryInstance.
   *
   * @return true if they can be melted together, false if not.
   */
  private boolean isSameInstance(GroceryInstance G1, GroceryInstance G2) {
    return isSameType(G1, G2) && G1.getBestBeforeString()
        .equals(G2.getBestBeforeString()) && G1.getPricePerUnit() == G2.getPricePerUnit();
  }

}
