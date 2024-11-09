package edu.ntnu.idi.idatt.Interface;

import edu.ntnu.idi.idatt.Grocery.GroceryInstance;
import edu.ntnu.idi.idatt.Grocery.GroceryType;

import java.util.ArrayList;

public class TableCreator {

  // constuctor

  /**
   * Create different types of tables for different types of classes.
   */
  public TableCreator() {
  }

  /**
   * Creates a table that's 24 characters long where it takes all the instances of GroceryType and
   * prints them out in a table-format.
   *
   * @param groceryTypes All instances of GroceryTypes.
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

      // character mapping: total: 67 chars: 3 chars num, 17 chars name, 9 chars amount, 9 Chars unit,
      //                                     7 chars Price, 10 chars Price/unit DD.MM.YYYY
      System.out.format(
          "+─────+───────────────────+────────+──GROCERY INSTANCES───────────+────────────+────────────+%n");
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

}
