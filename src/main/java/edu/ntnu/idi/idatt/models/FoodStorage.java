package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * An instance of FoodStorage is used to store multiple instances of the {@link GroceryInstance}
 * class as well as multiple instances of the {@link GroceryType} class. The FoodStorage class is
 * used to more or less manage the groceries that the user has in their food storage.
 */
public class FoodStorage {

  // variables
  private final ArrayList<GroceryInstance> groceryInstances = new ArrayList<>();
  private final ArrayList<GroceryType> groceryTypes = new ArrayList<>();


  /**
   * A class that stores multiple instances of the {@link GroceryInstance} class as well as multiple
   * instances of the {@link GroceryType} class. The FoodStorage class is used to manage the
   * groceries that the user has in their food storage.
   */
  public FoodStorage() {
  }

  // Get-methods

  /**
   * Fetches an instance of {@link GroceryInstance}from the food storage, based on what index is
   * given.
   */
  public GroceryInstance getSpecificInstance(int index) {
    return this.groceryInstances.get(index - 1);
  }

  /**
   * Fetches an instance of {@link GroceryType} from the food storage, based on what index is
   * given.
   */
  public GroceryType getSpecificType(int index) {
    return this.groceryTypes.get(index - 1);
  }

  /**
   * Finds the total value of multiple specific items in the food storage based on the indexes
   * given.
   *
   * @param index a table of integers containing the indexes of the groceries the user wants to
   *              calculate the total value of.
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
   * Finds the total value of all instances of {@link GroceryInstance} in the food storage.
   */
  public double getTotalValue() {
    double sum = 0;

    for (GroceryInstance grocery : this.groceryInstances) {
      sum += grocery.getPrice();
    }

    return sum;
  }

  /**
   * Fetches an {@link ArrayList} containing all the instances of {@link GroceryInstance} that are
   * out of date.
   */
  public ArrayList<GroceryInstance> getOutOfDateInstances() {
    ArrayList<GroceryInstance> outdatedInstances = new ArrayList<>();

    this.groceryInstances.forEach(grocery -> {
      if (grocery.isOutOfDate()) {
        outdatedInstances.add(grocery);
      }
    });

    return outdatedInstances;
  }

  /**
   * Fetches the total value of all the instances of {@link GroceryInstance} that are out of date.
   */
  public double getOutOfDateValue() {
    var sumWrapper = new Object() {
      double sum = 0;
    };

    this.getOutOfDateInstances().forEach(grocery -> sumWrapper.sum += grocery.getPrice());

    return sumWrapper.sum;
  }

  /**
   * Fetches an {@link ArrayList} containing all the instances of {@link GroceryInstance} that are
   * in the food storage.
   *
   * @return An ArrayList containing all instances of GroceryInstance.
   */
  public ArrayList<GroceryInstance> getAllGroceryInstances() {
    this.removeEmptyInstances();

    return this.groceryInstances;
  }

  /**
   * Fetches an {@link ArrayList} containing all the instances of {@link GroceryInstance} that are
   * in the food storage.
   *
   * @return An {@link ArrayList} containing all instances of GroceryType.
   */
  public ArrayList<GroceryType> getAllGroceryTypes() {
    return this.groceryTypes;
  }

  /**
   * Sorts the {@link ArrayList} of {@link GroceryInstance} alphabetically, then based on best
   * before date.
   */
  public void sortGroceryInstances() {
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
   * Merges duplicate instances of {@link GroceryInstance} in the food storage based on the name,
   * best before date, price per unit and measurement unit.
   */
  public void mergeDuplicateInstances() {
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
   * Removes all instances of {@link GroceryInstance} that have an amount of 0.
   */
  private void removeEmptyInstances() {
    this.groceryInstances.removeIf(gi -> gi.getAmount() == 0);
  }

  private boolean isSameType(GroceryInstance g1, GroceryInstance g2) {
    return g1.getName().equals(g2.getName()) && g1.getMeasurementUnit()
        .equals(g2.getMeasurementUnit());
  }

  private boolean isSameInstance(GroceryInstance g1, GroceryInstance g2) {
    return isSameType(g1, g2) && g1.getBestBeforeString()
        .equals(g2.getBestBeforeString()) && g1.getPricePerUnit() == g2.getPricePerUnit();
  }

  /**
   * Searches for a specific {@link GroceryInstance} in the food storage based on the search term
   * given by the user.
   *
   * @param searchTerm The search term specified by the user.
   * @return Returns an ArrayList of all the items that matched the users search term.
   */
  public List<GroceryInstance> groceryInstanceSearch(String searchTerm) {

    return this.groceryInstances.stream()
        .filter(gi -> gi.getName().toLowerCase().contains(searchTerm.toLowerCase())).toList();
  }

  // add/remove methods

  /**
   * Removes an instance of {@link GroceryInstance} from the food storage, based on the index
   * given.
   *
   * @param index the index of the instance of {@link GroceryInstance} that the user wants to
   *              remove.
   */
  public void removeInstance(int index) {
    //here, index - 1 is used so that the lists displayed go from 1 - (n + 1), and not 0 - n
    //which is less user-friendly.
    this.groceryInstances.remove(index - 1);
  }

  /**
   * Removes an instance of {@link GroceryType} from the food storage, based on the index given.
   *
   * @param index the index of the instance of {@link GroceryType} that the user wants to remove.
   */
  public void removeType(int index) {
    this.groceryTypes.remove(index - 1);
  }

  /**
   * Adds an instance of {@link GroceryInstance} to the food storage.
   */
  public void addInstance(GroceryInstance grocery) {
    //if the grocery has been added and is equal to zero, it will be removed next time the program
    //gets the groceryInstances ArrayList.
    this.groceryInstances.add(grocery);
  }

  /**
   * Adds an instance of {@link GroceryType} to the food storage.
   */
  public void addType(GroceryType groceryType) {
    this.groceryTypes.add(groceryType);
  }


  /**
   * Removes a specific amount of a specific {@link GroceryInstance} from the food storage. The
   * specific grocery is specified by the given index.
   *
   * @param index  Specifies which grocery the user wants to remove from.
   * @param amount Specifies the amount of the grocery the user wants to remove (based on the
   *               measurement unit defined in the groceryType object).
   */
  public void removeInstanceAmount(int index, double amount) {
    this.groceryInstances.get(index - 1).removeAmount(amount);
  }
}
