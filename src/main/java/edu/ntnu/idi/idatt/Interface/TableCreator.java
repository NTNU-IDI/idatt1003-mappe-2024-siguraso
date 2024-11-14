package edu.ntnu.idi.idatt.Interface;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.GroceryInstance;
import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Grocery.Recipe;

import java.util.ArrayList;

/**
 * Class of object that creates different types of tables based on different types of classes using
 * void methods.
 */
public class TableCreator {

  // constuctor

  /**
   * Create different types of tables for different types of classes.
   */
  public TableCreator() {
  }

  /**
   * Creates a table that's 45 characters long, where it takes an ArrayList of instances of
   * GroceryType and prints them out in a table-format.
   *
   * @param groceryTypes Instances of GroceryType in an ArrayList.
   */
  public void groceryTypeTable(ArrayList<GroceryType> groceryTypes) {
    if (groceryTypes.isEmpty()) {
      // if there are no grocery types to display, show a dialog explaining the situation.
      System.out.println("=========NO GROCERY TYPES TO DISPLAY!========");
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-16s │ %-16s │%n";

      // prints out the top of the table. (the header)
      // character mapping: total: 40 chars: 3 chars num, 16 chars name, 16 chars measurement unit
      System.out.format("+─────+─────────GROCERY TYPES───────────────+%n");
      System.out.format("│ Num │ Name             │ Measurement Unit │%n");
      System.out.format("+─────+──────────────────+──────────────────+%n");

      for (int i = 0; i < groceryTypes.size(); i++) {
        // takes the format given for each line, and prints it our with the number, name
        // and measurement unit defined by the grocery type the loop is currently on.
        System.out.format(tableFormat, i + 1, groceryTypes.get(i).getName(),
            groceryTypes.get(i).getMeasurementUnit());
      }

      //prints out bottom of table
      System.out.format("+─────+──────────────────+──────────────────+%n");
    }
  }

  /**
   * Creates a table that's 63 characters long, where it takes an ArrayList of instances of
   * GroceryInstance and prints them out in a table-format.
   *
   * @param instances Instances of GroceryInstances in an ArrayList.
   */
  public void groceryInstanceTable(ArrayList<GroceryInstance> instances) {
    if (instances.isEmpty()) {
      //tells the user that there is no items in food storage if there is none.
      System.out.println(
          "============================="
              + "NOTHING TO DISPLAY IN FOOD STORAGE!"
              + "=============================");
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-17s │ %,-9.1f │ %-9s │ %,-13.2f │ %,-10.2f │ %-10s │%n";

      // prints out the top of the table. (the header)

      // character mapping: total: 93 chars: 3 chars num, 17 chars name, 9 chars amount, 9 Chars unit,
      //                                     7 chars Price, 10 chars Price/unit DD.MM.YYYY
      System.out.format(
          "+─────+───────────────────+───────────GROCERY INSTANCES───────────+────────────+────────────+%n");
      System.out.format(
          "│ Num │ Name              │ Amount    │ Unit      │ Total Price   │ Price/Unit │ BestBefore │%n");
      System.out.format(
          "+─────+───────────────────+───────────+───────────+───────────────+────────────+────────────+%n");

      for (int i = 0; i < instances.size(); i++) {
        // takes the format given for each line, and prints it out with the all the info
        System.out.format(tableFormat, i + 1, instances.get(i).getName(),
            instances.get(i).getAmount(), instances.get(i).getMeasurementUnit(),
            instances.get(i).getPrice(), instances.get(i).getPricePerUnit(),
            instances.get(i).getBestBeforeString());
      }

      // prints out bottom of table
      System.out.format(
          "+─────+───────────────────+───────────+───────────+───────────────+────────────+────────────+%n");
    }
  }

  /**
   * Creates a table, where it takes an ArrayList of instances of Recipe, and prints them out in a
   * table-format.
   *
   * @param recipes Instances of recipe in an ArrayList.
   */
  public void recipeTable(ArrayList<Recipe> recipes, FoodStorage foodStorage) {
    if (recipes.isEmpty()) {
      System.out.format(
          "==========================================NO RECIPES TO DISPLAY!===========================================%n");
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-45s │ %-11d │ %-8s │ %-21s │%n";

      // prints out the top of the table. (the header)
      // character mapping: total: 104 chars: 3 chars num, 45 chars name, 11 chars amount of
      // ingredients, 8 chars can make it without using out of date food, 21 chars kan make it with
      // out of date food.
      System.out.format(
          "+─────+──────────────────────────────────────────RECIPES────────────+──────────+───────────────────────+%n");
      System.out.format(
          "│ Num │ Name                                          │ Ingredients │ CanMake? │ CanMakeWithOutOfDate? │%n");
      System.out.format(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+───────────────────────+%n");

      for (int i = 0; i < recipes.size(); i++) {
        String canMakeNoOutOfDate;
        String canMakeWithOutOfDate;

        // Checks weather or not the current recipe can be made or not without including out of date
        // groceries.
        if (recipes.get(i).canMakeRecipe(foodStorage, false)) {
          canMakeNoOutOfDate = "Yes";
        } else {
          canMakeNoOutOfDate = "No";
        }

        // Checks weather or not the current recipe can be made while including out of date
        // groceries.
        if (recipes.get(i).canMakeRecipe(foodStorage, true)) {
          canMakeWithOutOfDate = "Yes";
        } else {
          canMakeWithOutOfDate = "No";
        }

        System.out.format(tableFormat, i + 1, recipes.get(i).getName(),
            recipes.get(i).getIngredients().size(), canMakeNoOutOfDate, canMakeWithOutOfDate);
      }

      System.out.format(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+───────────────────────+%n");
    }
  }

  public void ingredientsTable(ArrayList<GroceryInstance> instances,
      ArrayList<String> approximations) {
    // num, name, amount, measurement unit, appproximation (if needed)
    String format = "│ %-3d │ %-17s │ %,-9.1f │ %-9s │ ~ %-20s │ %n";

    //prints out top of table:
    System.out.format(
        "+─────+───────────────────+──────INGREDIENTS──────+────────────────────────+%n");
    System.out.format(
        "│ Num │ Name              │ Amount    │ Unit      │ Approximation          │%n");
    System.out.format(
        "+─────+───────────────────+───────────+───────────+────────────────────────+%n");

    for (int i = 0; i < instances.size(); i++) {
      System.out.format(format, i + 1, instances.get(i).getName(), instances.get(i).getAmount(),
          instances.get(i).getMeasurementUnit(), approximations.get(i));
    }

    //prints out bottom of the table.
    System.out.format(
        "+─────+───────────────────+───────────+───────────+────────────────────────+%n");
  }

}
