package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.GroceryInstance;
import edu.ntnu.idi.idatt.modules.GroceryType;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Text-User-Interface class that manages the different dialog options that are needed to create the
 * text-based user interface.
 */
public class TUI {

  private final Scanner sc = new Scanner(System.in);

  /**
   * An instance of the TUI class handles all of the different dialog messages and user inputs so
   * that the user can access the
   */
  public TUI() {
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

  // dialog options

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
   * @param message message displayed above the user input (given as a {@link String}.
   * @return a {@link String} containing either "y" or "n"
   * @throws IllegalArgumentException if the user enters anything other than y or n (ignores case).
   */
  public String yesNoOption(String message) throws IllegalArgumentException {
    System.out.println(message);

    String yesNoChoice = getInput();
    if (yesNoChoice.equalsIgnoreCase("y") || yesNoChoice.equalsIgnoreCase("n")) {
      return yesNoChoice.toLowerCase();
    } else {
      throw new IllegalArgumentException("Please input y/Y if yes, or n/N if no.");
    }
  }

  /**
   * Creates a dialog window that lets the user pick between different choices, and returns an
   * integer (the primitive type "int") based on what function the user wants to initiate. The
   * choices are displayed in the same order as given in the choices {@link String} table (see
   * params).
   * <p>The choice window will look something like this:</p>
   * <p>[1] Choice 1
   * <p>[2] Choice 2
   * <p>[3] Choice 3
   * <p>...</p>
   * <p>Please enter an integer 1 - *number of choices*.</p>
   *
   * @param choices a table of {@link String} that contains the
   * @return the integer (the primitive type "int") that the user has entered.
   */
  public int choiceWindow(String[] choices, String message) {
    List<String> choicesList = Arrays.asList(choices);

    choicesList.forEach(choice -> {
      int currentIndex = choicesList.indexOf(choice);

      choicesList.set(currentIndex, "[" + (currentIndex + 1) + "] " + choice);
    });

    String choicesString =
        message + " (Please enter an integer 1 - " + choices.length + ")\n\n" + String.join("\n",
            choicesList);

    boolean hasEnteredValidInteger = false;
    int input = 0;

    do {
      System.out.println(choicesString);
      try {
        input = Integer.parseInt(this.getInput());
        if (input >= 1 && input <= choicesList.size()) {
          hasEnteredValidInteger = true;
        } else {
          this.clearScreen();
          System.out.println("Please enter a valid integer!");
        }
      } catch (NumberFormatException e) {
        this.clearScreen();
        System.out.println("Please enter a valid integer!");
      }
    } while (!hasEnteredValidInteger);

    return input;
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
      return "=========NO GROCERY TYPES TO DISPLAY!========";
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-30s │ %-16s │";

      ArrayList<String> rows = new ArrayList<>();

      // appends the header of the table to the string
      // character mapping: total: 40 chars: 3 chars num, 16 chars name, 16 chars measurement unit
      rows.add("+─────+────────────────GROCERY TYPES───+──────────────────+");
      rows.add("│ Num │ Name                           │ Measurement Unit │");
      rows.add("+─────+────────────────────────────────+──────────────────+");

      groceryTypes.forEach(type -> rows.add(
          String.format(tableFormat, (groceryTypes.indexOf(type) + 1), type.getName(),
              type.getMeasurementUnit())));

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
      return "======================================NO GROCERIES TO DISPLAY!=========================================";
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-30s │ %,-9.1f │ %-9s │ %,-13.2f │ %,-10.2f │ %-10s │";

      ArrayList<String> rows = new ArrayList<>();

      // adds the top of the table (the header) to the rows ArrayList

      // character mapping: total: 93 chars: 3 chars num, 17 chars name, 9 chars amount, 9 Chars unit,
      //                                     7 chars Price, 10 chars Price/unit DD.MM.YYYY
      rows.add(
          "+─────+────────────────────────────────+────────GROCERIES──────────────────────+────────────+────────────+");
      rows.add(
          "│ Num │ Name                           │ Amount    │ Unit      │ Total Price   │ Price/Unit │ BestBefore │");
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+───────────────+────────────+────────────+");

      groceryInstances.forEach(instance -> rows.add(String.format(tableFormat,
          groceryInstances.indexOf(instance) + 1, instance.getName(), instance.getAmount(),
          instance.getMeasurementUnit(), instance.getPrice(), instance.getPricePerUnit(),
          instance.getBestBeforeString())));

      // prints out bottom of table
      rows.add(
          "+─────+────────────────────────────────+───────────+───────────+───────────────+────────────+────────────+");

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
          "==========================================NO RECIPES TO DISPLAY!===========================================";
    } else {
      // creates the format for the table.
      String tableFormat = "│ %-3d │ %-45s │ %-11d │ %-8s │ %-21s │";

      ArrayList<String> rows = new ArrayList<>();

      // adds the top of the table (the header) to the list of rows
      // character mapping: total: 104 chars: 3 chars num, 45 chars name, 11 chars amount of
      // ingredients, 8 chars can make it without using out of date food, 21 chars can make it with
      // out of date food.
      rows.add(
          "+─────+──────────────────────────────────────────RECIPES────────────+──────────+───────────────────────+");
      rows.add(
          "│ Num │ Name                                          │ Ingredients │ CanMake? │ CanMakeInclOutOfDate? │");
      rows.add(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+───────────────────────+");

      recipes.forEach(recipe -> rows.add(
          String.format(tableFormat, recipes.indexOf(recipe) + 1, recipe.getName(),
              recipe.getIngredients().size(), recipe.canMakeRecipe(foodStorage, false),
              recipe.canMakeRecipe(foodStorage, true))));

      rows.add(
          "+─────+───────────────────────────────────────────────+─────────────+──────────+───────────────────────+");

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
      String tableFormat = "│ %-3d │ %-17s │ %,-9.1f │ %-9s │ ~ %-20s │";

      ArrayList<String> rows = new ArrayList<>();

      //adds the header to the list of rows.
      rows.add(
          "+─────+───────────────────+──────INGREDIENTS──────+────────────────────────+");
      rows.add(
          "│ Num │ Name              │ Amount    │ Unit      │ Approximation          │");
      rows.add(
          "+─────+───────────────────+───────────+───────────+────────────────────────+");

      ingredients.forEach(ingredient -> rows.add(String.format(tableFormat,
          ingredients.indexOf(ingredient), ingredient.getName(), ingredient.getAmount(),
          ingredient.getMeasurementUnit(), approximations.get(ingredients.indexOf(ingredient)))));

      //prints out bottom of the table.
      rows.add(
          "+─────+───────────────────+───────────+───────────+────────────────────────+");

      return String.join("\n", rows);
    }
  }


}
