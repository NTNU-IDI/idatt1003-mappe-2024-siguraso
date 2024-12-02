package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * an instance of FoodStorage contains both GroceryInstances and GroceryTypes that are both
 * pre-defined and/or defined by the user. FoodStorage object's main goals are to more or less
 * manage these objects.
 */
public class FoodStorage {

  // variables
  ArrayList<GroceryInstance> groceryInstances = new ArrayList<>();
  ArrayList<GroceryType> groceryTypes = new ArrayList<>();


  /**
   * A class that stores multiple instances of the {@link GroceryInstance} class. Used to view how
   * much of each grocery the user has.
   */
  public FoodStorage() {
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
  public double getMultipleSpecificValue(int[] index) {
    var sumWrapper = new Object() {
      double sum = 0;
    };

    // streams the indexes and adds the price of the grocery instances to the sumWrapper object.
    Arrays.stream(index).forEach(i -> sumWrapper.sum += this.getSpecificInstance(i).getPrice());

    return sumWrapper.sum;
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
    var sumWrapper = new Object() {
      double sum = 0;
    };

    this.getOutOfDateInstances().forEach(grocery -> sumWrapper.sum += grocery.getPrice());

    return sumWrapper.sum;
  }

  /**
   * Gets all instances of GroceryInstance.
   *
   * @return An ArrayList containing all instances of GroceryInstance.
   */
  public ArrayList<GroceryInstance> getAllGroceryInstances() {
    this.removeEmptyInstances();
    this.mergeDuplicateInstances();
    this.sortGroceryInstances();

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

  /**
   * Sorts the {@link ArrayList} of {@link GroceryInstance} alphabetically, then based on best
   * before date.
   */
  private void sortGroceryInstances() {
    // sorts the list of grocery instances based on what the best before date is, and then comparing
    // the name alphabetically
    this.groceryInstances.sort(Comparator.comparing(GroceryInstance::getNameLowerCase)
        .thenComparing(GroceryInstance::getBestBeforeDate));

  }

  /**
   * Sorts the {@link ArrayList} of {@link GroceryType} alphabetically.
   */
  public void sortGroceryTypes() {
    this.groceryTypes.sort(Comparator.comparing(GroceryType::getNameLowerCase));
  }

  /**
   * Merges duplicate instances of GroceryInstance in the food storage. If the total amount of the
   * two instances is greater than 999.9, the rest will be added to a separate instance.
   */
  private void mergeDuplicateInstances() {
    // goes through the list of groceries...
    for (int i = 0; i < this.groceryInstances.size(); i++) {
      // ...and compares them to every other grocery in the list...
      for (int j = i + 1; j < this.groceryInstances.size(); j++) {
        if (isSameInstance(this.groceryInstances.get(i), this.groceryInstances.get(j))) {
          // ...if they are the same, they will be merged together.
          if (this.groceryInstances.get(i).getAmount() + this.groceryInstances.get(j).getAmount()
              > 999.9) {
            // if the total amount is bigger than 999.9, the rest will be added to the first grocery
            // instance, and the second will be set to 999.9...
            double restToAdd =
                999.9 - this.groceryInstances.get(i).getAmount() + this.groceryInstances.get(j)
                    .getAmount();
            this.groceryInstances.get(i).setAmount(999.9);
            this.groceryInstances.get(j).removeAmount(restToAdd);
          } else {
            // ...if not, they will be added together and the second grocery will be set to 0, and
            // promptly removed.
            this.groceryInstances.get(i).addAmount(this.groceryInstances.get(j).getAmount());
            this.groceryInstances.get(j).setAmount(0);
          }
        }
      }
    }
  }

  /**
   * Removes all elements from the groceries ArrayList that are equal to zero.
   */
  private void removeEmptyInstances() {
    this.groceryInstances.removeIf(GI -> GI.getAmount() == 0);
  }


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
}
