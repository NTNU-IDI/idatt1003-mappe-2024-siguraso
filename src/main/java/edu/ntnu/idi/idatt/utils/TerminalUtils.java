package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.GroceryInstance;
import edu.ntnu.idi.idatt.models.GroceryType;
import edu.ntnu.idi.idatt.models.Recipe;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text-User-Interface class that manages the input/output of the program. The class contains
 * different methods that are used to validate user input, print out tables, and clear the terminal
 * window.
 */
public class TerminalUtils {

  private final Scanner sc = new Scanner(System.in);

  /**
   * An instance of the TerminalUtils class handles all of the different dialog messages and user
   * inputs so that the user can access the different functionalities of the program.
   */
  public TerminalUtils() {
  }

  // user input:

  /**
   * Used to get the user input. All inputs are returned as an instance of {@link String}, and have
   * to be parsed to the required types. Here, the input is method is used by the help of a
   * {@link Scanner}.
   * <p>The menu has a failsafe just in case the user doesnt input anything, where it returns an
   * empty {@link String}</p>
   *
   * @return an instance of {@link String} containing the user input
   */
  public String getInput() {
    if (sc.hasNextLine()) {
      return sc.nextLine();
    }

    return "";
  }

  /**
   * Closes the user-input {@link Scanner} to avoid memory leaks.
   */
  public void closeInput() {
    sc.close();
  }

  // misc. utilities:

  /**
   * Clears the terminal window.
   */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // input validation:

  /**
   * Gives a dialog window that checks if the user enters an input containing an integer (the
   * primitive type "int").
   *
   * @param message The dialog message that will be displayed.
   * @return a user-defined valid integer (the primitive type "int").
   * @throws NumberFormatException if the {@link String} from the given input cannot be parsed to an
   *                               integer (of the primitive type "int").
   */
  public int integerOption(String message) throws NumberFormatException {

    System.out.println(message);

    try {
      return Integer.parseInt(this.getInput());
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Please enter a valid integer!");
    }
  }

  /**
   * Gives a dialog window where the user enters an input containing a double (primitive type).
   *
   * @param message dialog message that appears above the
   * @return a valid double (primitive type) defined by the user.
   * @throws NumberFormatException if the the {@link String} given from the input cannot be parsed
   *                               to a double (of the primitive type).
   */
  public double doubleOption(String message) throws NumberFormatException {
    System.out.println(message);

    try {
      return Double.parseDouble(this.getInput());
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Please enter a valid decimal number!");
    }
  }

  /**
   * Gives a dialog window where the user enters an input that's either Y or N (ignores case),
   * letting the program know no or yes.
   *
   * @return a {@link String} containing either "y" or "n"
   * @throws IllegalArgumentException if the user enters anything other than y or n (ignores case).
   */
  public String yesNoOption() throws IllegalArgumentException {
    String yesNoChoice = getInput();
    if (yesNoChoice.equalsIgnoreCase("y") || yesNoChoice.equalsIgnoreCase("n")) {
      return yesNoChoice.toLowerCase();
    } else {
      throw new IllegalArgumentException("Please input y/Y if yes, or n/N if no.");
    }
  }

  // tables:

  /**
   * Creates a table that's 45 characters long, where it takes an ArrayList of instances of
   * GroceryType and prints them out in a table-format. The table displays the name of the grocery
   * class, and the measurement unit associated with the grocery class.
   *
   * @param groceryTypes Instances of GroceryType in an ArrayList.
   * @return String that contains a table that displays a list of GroceryTypes.
   */
  public String groceryTypeTable(ArrayList<GroceryType> groceryTypes) {
    if (groceryTypes.isEmpty()) {
      // if there are no grocery types to display, return a string saying exactly that.
      return "=========NO GROCERY CLASSES TO DISPLAY!========";
    } else {

      ArrayList<String> rows = new ArrayList<>();

      // appends the header of the table to the string
      // character mapping: total: 40 chars: 3 chars num, 16 chars name, 16 chars measurement unit
      rows.add("+─────+───────────────GROCERY CLASSES──+──────────────────+");
      rows.add("│ Num │ Name                           │ Measurement Unit │");
      rows.add("+─────+────────────────────────────────+──────────────────+");

      // appends the grocery type numbers to the format of the table.
      groceryTypes.forEach(type -> rows.add(
          String.format("│ %-3d │ %-30s │ %-16s │", (groceryTypes.indexOf(type) + 1),
              type.getName(), type.getMeasurementUnit())));

      //prints out bottom of table
      rows.add("+─────+────────────────────────────────+──────────────────+");

      return String.join("\n", rows);
    }
  }

  /**
   * Creates a table that's 63 characters long, where it takes an ArrayList of instances of
   * GroceryInstance and prints them out in a table-format. The table displays the name of the
   * grocery, the amount of the grocery available, the measurement unit associated with the grocery,
   * the total price of the grocery, how much it costs per measurement unit, and its best before
   * date.
   *
   * @param groceryInstances Instances of GroceryInstances in an ArrayList.
   * @return a {@link String} that contains a table of GroceryInstances.
   */
  public String foodStorageTable(ArrayList<GroceryInstance> groceryInstances) {
    if (groceryInstances.isEmpty()) {
      //tells the user that there is no items in food storage if there is none.
      return
          "======================================NO GROCERIES TO DISPLAY!=========================="
              + "===============";
    } else {
      ArrayList<String> rows = new ArrayList<>();

      // adds the top of the table (the header) to the rows ArrayList
      // character mapping: total: 93 chars: 3 chars num, 30 chars name, 9 chars amount,
      //                           9 Chars unit, 7 chars Price, 10 chars Price/unit DD.MM.YYYY
      rows.add(
          "+─────+────────────────────────────────+────────GROCERIES──────────────────────+────────"
              + "────+────────────+");
      rows.add(
          "│ Num │ Name                           │ Amount    │ Unit      │ Total Price   │ Price/U"
              + "nit │ BestBefore │");
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+───────────────+────────"
              + "────+────────────+");

      groceryInstances.forEach(instance -> rows.add(
          String.format("│ %-3d │ %-30s │ %,-9.1f │ %-9s │ %,-13.2f │ %,-10.2f │ %-10s │",
              groceryInstances.indexOf(instance) + 1, instance.getName(), instance.getAmount(),
              instance.getMeasurementUnit(), instance.getPrice(), instance.getPricePerUnit(),
              instance.getBestBeforeString())));

      // prints out bottom of table
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+───────────────+────────"
              + "────+────────────+");

      return String.join("\n", rows);
    }
  }

  /**
   * Creates a table, where it takes an ArrayList of instances of Recipe, and prints them out in a
   * table-format. The table displays the name of a given recipe, how many ingredients there are,
   * weather the user can make the given recipe based on the food in the food storage with and
   * without including out of date food.
   *
   * @param recipes     Instances of recipe in an ArrayList.
   * @param foodStorage FoodStorage, here used to check if the user can make the recipe with the
   *                    available GroceryInstances in the food storage.
   * @return a {@link String} that contains ta table of
   */
  public String cookbookTable(ArrayList<Recipe> recipes, FoodStorage foodStorage) {
    if (recipes.isEmpty()) {
      return
          "==========================================NO RECIPES TO DISPLAY!========================"
              + "===================";
    } else {
      ArrayList<String> rows = new ArrayList<>();

      // adds the top of the table (the header) to the list of rows
      // character mapping: total: 104 chars: 3 chars num, 45 chars name, 11 chars amount of
      // ingredients, 8 chars can make it without using out of date food, 21 chars can make it with
      // out of date food.
      rows.add(
          "+─────+──────────────────────────────────────────RECIPES────────────+──────────+────────"
              + "───────────────+");
      rows.add(
          "│ Num │ Name                                          │ Ingredients │ CanMake? │ CanMake"
              + "InclOutOfDate? │");
      rows.add(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+────────"
              + "───────────────+");

      recipes.forEach(recipe -> rows.add(
          String.format("│ %-3d │ %-45s │ %-11d │ %-8s │ %-21s │", recipes.indexOf(recipe) + 1,
              recipe.getName(), recipe.getIngredients().size(), recipe.canMakeRecipe(foodStorage,
                  false), recipe.canMakeRecipe(foodStorage, true))));

      rows.add(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+────────"
              + "───────────────+");

      return String.join("\n", rows);
    }
  }

  /**
   * Creates a table, where it takes an ArrayList of instances of GroceryInstance and an ArrayList
   * of strings that contain approximations of the size of the above GroceryInstances, and prints
   * them out in a table-format.
   *
   * @param ingredients    the {@link ArrayList} of GroceryInstances that, here, are used as
   *                       ingredients in a recipe.
   * @param approximations the ArrayList of Strings which contain approximations of how much of each
   *                       of the above ingredients are in the
   * @return A {@link String} that contains the table.
   */
  public String ingredientsTable(ArrayList<GroceryInstance> ingredients,
      ArrayList<String> approximations) {
    if (ingredients.isEmpty()) {
      return "=========================NO INGREDIENTS TO DISPLAY!=========================";
    } else {

      // num, name, amount, measurement unit, appproximation (if needed)
      String tableFormat = "";

      ArrayList<String> rows = new ArrayList<>();

      //adds the header to the list of rows.
      rows.add(
          "+─────+────────────────────────────────INGREDIENTS─+───────────+────────────────────────"
              + "+");
      rows.add(
          "│ Num │ Name                           │ Amount    │ Unit      │ Approximation          "
              + "│");
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+────────────────────────"
              + "+");

      ingredients.forEach(ingredient -> rows.add(String.format(
          "│ %-3d │ %-30s │ %,-9.1f │ %-9s │ ~ %-20s │", ingredients.indexOf(ingredient) + 1,
          ingredient.getName(), ingredient.getAmount(), ingredient.getMeasurementUnit(),
          approximations.get(ingredients.indexOf(ingredient)))));

      //prints out bottom of the table.
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+────────────────────────"
              + "+");

      return String.join("\n", rows);
    }
  }


}
