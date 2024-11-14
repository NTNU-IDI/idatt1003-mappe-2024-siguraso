package edu.ntnu.idi.idatt.Interface;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.Cookbook;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class that is used to create different dialog windows that check weather or not the user input
 * is a valid input.
 */
public class DialogOptionCreator {

  // constructor

  /**
   * A class that is used to create different dialog windows that check weather or not the user
   * input is a valid input.
   */
  public DialogOptionCreator() {
  }

  // general methods:

  /**
   * Clears the terminal window. Makes for a smoother user experience.
   */
  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Checks if a date is valid based on the date format used throughout the script.
   */
  private static boolean isValidDate(String dateString) {
    String[] dateParts = dateString.split("\\.");

    try {
      int day = Integer.parseInt(dateParts[0]);
      int month = Integer.parseInt(dateParts[1]);
      int year = Integer.parseInt(dateParts[2]);

      // if the different parts are as long as they should be and are in an amount that is
      // valid when it comes to date, it is a valid date.
      return
          (dateParts[0].length() == 2 && dateParts[1].length() == 2 && dateParts[2].length() == 4)
              &&
              (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 0 && year < 9999);

    } catch (NumberFormatException e) {
      // if it runs into an error when parsing the day, month and year, it isnt a valid date
      // because it probably contains a different datatype than integer.
      return false;
    }
  }

  // method for the different types of dialog options
  // general methods to clea up the program:

  /**
   * Gives a dialog window that returns either y/Y or n/N based on the users input.
   *
   * @param dialogMessage Message to display above the user input.
   * @param sc            Scanner used for user input
   * @return either y/Y or n/N as a String.
   */
  public String yesNoOption(Scanner sc, String dialogMessage) {
    while (true) {
      System.out.println(dialogMessage);

      try {
        String yesNoSc = sc.nextLine();

        //if the user inputs either y or n, it returns the input
        if (yesNoSc.equalsIgnoreCase("Y") || yesNoSc.equalsIgnoreCase("N")) {
          clearScreen();
          return yesNoSc.toLowerCase();
        } else {
          // if the user inputs an invalid string, it repeats the loop to the top.
          clearScreen();
          System.out.println("Please enter Y, if yes, or N, if no.\n\n");
        }

      } catch (Exception e) {
        // if the user inputs something other than a string it results in an error, which is here
        // caught, and the program kindly instructs to enter a valid input.
        clearScreen();
        System.out.println("Please enter Y, if yes, or N, if no.\n\n");
      }
    }
  }

  //more universal dialog options:

  /**
   * Gives a dialog message that lets the user pick a double in between a set of parameters.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog string that displays above the user input.
   * @param start         Start parameter
   * @param end           end parameter.
   * @return a double between start and end.
   */
  public double validDoubleOption(Scanner sc, String dialogMessage, double start, double end) {
    while (true) {
      clearScreen();
      System.out.println(dialogMessage + " (" + start + " - " + end + ").");

      try {
        double amount = sc.nextDouble();

        // if it is a valid input (meaning not a negative number or a number above 999)
        // it breaks the loop and continues.
        if (amount > start && amount <= end) {
          return amount;
        }
        // if it isnt a valid input, restart the loop, and encourage the user
        // to input a valid number.
        else {
          clearScreen();
          System.out.println("Please enter a price" + start + " - " + end + "\n\n");
          sc.nextLine();
        }

      } catch (Exception e) {
        // if the user inputs something other than an integer,
        // restart the loop and en encourage the user again.
        clearScreen();
        System.out.println("Please enter a price" + start + " - " + end + "\n\n");
        sc.nextLine();

      }
    }
  }

  /**
   * Gives a dialog message that lets the user enter a string within a given character limit.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Message that is displayed above the character limit.
   * @param maxChars      the maximum amount of characters.
   * @return a valid string within the max amount character limit.
   */
  public String validCharLimit(Scanner sc, String dialogMessage, int maxChars) {
    while (true) {
      clearScreen();
      System.out.println(dialogMessage + " (max " + maxChars + " characters).");

      try {
        String typeName = sc.nextLine();

        // if the name given is 17 characters or shorter, it is valid.
        if (typeName.length() <= maxChars) {
          clearScreen();
          return typeName;
        } else {
          clearScreen();
          System.out.println("Please enter a type that's max " + maxChars + " characters!\n\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println("Please enter a type that's max " + maxChars + " characters!\n\n");
      }
    }
  }

  /**
   * Gives a dialog message that lets the user pick an integer in between a set of parameters.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog string that displays above the user input.
   * @param start         Start parameter
   * @param end           end parameter.
   * @return a double between start and end.
   */
  public int validIntOption(Scanner sc, String dialogMessage, int start, int end) {
    while (true) {
      clearScreen();
      System.out.println(dialogMessage + " (" + start + " - " + end + ").");

      try {
        int amount = sc.nextInt();

        // if it is a valid input (meaning not a negative number or a number above end)
        // it breaks the loop and continues.
        if (amount > start && amount <= end) {
          return amount;
        }
        // if it isnt a valid input, restart the loop, and encourage the user
        // to input a valid number.
        else {
          clearScreen();
          System.out.println("Please enter a price" + start + " - " + end + "\n\n");
          sc.nextLine();
        }

      } catch (Exception e) {
        // if the user inputs something other than an integer,
        // restart the loop and en encourage the user again.
        clearScreen();
        System.out.println("Please enter a price" + start + " - " + end + "\n\n");
        sc.nextLine();

      }
    }
  }

  // methods to select different indexes

  /**
   * Gives a dialog menu that takes the user input to determine what grocery type the user wants to
   * initiate.
   *
   * @param sc            Scanner for user input.
   * @param foodStorage   Food storage that contains the available GroceryTypes.
   * @param tableCreator  Used to create a table to display all the available GroceryTypes.
   * @param dialogMessage Dialog message that displays above the user input.
   * @return An integer that determines the index of what GroceryType the user wants to initiate.
   */
  public int validGroceryTypeIndex(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, String dialogMessage) {
    //if there are no instances of GroceryType in food storage, throw an illegal argument exception.
    if (foodStorage.getAllGroceryTypes().isEmpty()) {
      System.out.println();
      throw new IllegalArgumentException(
          "Can't fetch grocery types, since there are none stored in "
              + "food storage!");
    }

    while (true) {
      try {
        tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());

        System.out.println(
            "\n" + dialogMessage + " (1 - " + foodStorage.getAllGroceryTypes().size() + "):");

        int groceryTypeIndex = sc.nextInt();

        // if it is a valid index, return the usr input.
        if (groceryTypeIndex > 0 && groceryTypeIndex <= foodStorage.getAllGroceryTypes().size()) {
          return groceryTypeIndex;
        } else {
          clearScreen();
          System.out.println(
              "Please enter an integer (1 - " + foodStorage.getAllGroceryTypes().size()
                  + "). \n\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println(
            "Please enter an integer (1 - " + foodStorage.getAllGroceryTypes().size() + ").\n\n");
      }

    }
  }

  /**
   * Gives a dialog menu that takes the user input to determine what grocery instance the user wants
   * to initiate, and makes sure it's a valid index based on the amount of grocery indexes in the
   * list...
   *
   * @param sc            Scanner used for user input
   * @param foodStorage   Food storage that contains the available GroceryInstances.
   * @param tableCreator  Used to create a table to display all the available GroceryInstances.
   * @param dialogMessage dialog message that displays above the user input.
   * @return A valid integer that determines the index of what Grocery Instance the user wants to
   * initiate.
   */
  public int validGroceryInstanceIndex(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, String dialogMessage) {
    // if there are no instances of GroceryInstance in food storage,
    // throw an illegal argument exception.
    if (foodStorage.getAllGroceryInstances().isEmpty()) {
      System.out.println();
      throw new IllegalArgumentException(
          "Can't fetch grocery instances, since there are none stored in "
              + "food storage!");
    }

    while (true) {
      try {
        tableCreator.groceryInstanceTable(foodStorage.getAllGroceryInstances());

        System.out.println(
            "\n" + dialogMessage + " (1 - " + foodStorage.getAllGroceryInstances().size() + "):");

        int groceryInstanceIndex = sc.nextInt();

        // if it is a valid index, return the usr input.
        if (groceryInstanceIndex > 0 &&
            groceryInstanceIndex <= foodStorage.getAllGroceryInstances().size()) {
          return groceryInstanceIndex;
        } else {
          clearScreen();
          System.out.println(
              "Please enter an integer (1 - " + foodStorage.getAllGroceryInstances().size()
                  + "). \n\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println(
            "Please enter an integer (1 - " + foodStorage.getAllGroceryInstances().size()
                + ").\n\n");
      }

    }
  }

  /**
   * Gives a dialog menu that takes the user input to determine what recipe the user wants to access
   * and makes sure it is a valid index based on the amount of recipes in a given cookbook.
   *
   * @param sc           Scanner used for user input.
   * @param tableCreator used to show a table of the available recipes.
   * @param cookBook     used to access the stored recipes.
   * @param foodStorage  used in correlation to table creation. (Checks weather or not a recipe can
   *                     be made with the available food in food storage)
   */
  public int validRecipeIndex(Scanner sc, TableCreator tableCreator, Cookbook cookBook,
      FoodStorage foodStorage,
      String dialogMessage) {
    // if there are no recipes in the cookbook, throw an illegalargumentexception
    if (cookBook.getRecipes().isEmpty()) {
      System.out.println();
      throw new IllegalArgumentException(
          "Can't fetch recipes, since there currently are none in the cookbook.");
    }

    while (true) {
      try {
        tableCreator.recipeTable(cookBook.getRecipes(), foodStorage);

        System.out.println(
            "\n" + dialogMessage + " (1 - " + cookBook.getRecipes().size() + "):");

        int recipeIndex = sc.nextInt();

        // if it is a valid index, return the usr input.
        if (recipeIndex > 0 && recipeIndex <= cookBook.getRecipes().size()) {
          return recipeIndex;
        } else {
          clearScreen();

          System.out.println(
              "Please enter an integer (1 - " + cookBook.getRecipes().size() + "). \n\n");
        }
      } catch (Exception e) {
        clearScreen();

        System.out.println(
            "Please enter an integer (1 - " + cookBook.getRecipes().size() + ").\n\n");
      }
    }

  }

  /**
   * Gives a dialog menu that takes the multiple user inputs to determine what GroceryInstances the
   * user wants to initiate.
   *
   * @param sc           Scanner used for user input.
   * @param foodStorage  Food Storage that contains the available GroceryInstances.
   * @param tableCreator Used to create a table to display all the available GroceryInstances.
   * @return An ArrayList containing Integers of indexes of GroceryInstance that the user wants to
   * initiate.
   */
  public ArrayList<Integer> multipleValidGroceryInstanceIndex(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator) {
    // is there arent any grocery instances in food Storage, throw an error.
    if (foodStorage.getAllGroceryInstances().isEmpty()) {
      throw new IllegalArgumentException(
          "Can't fetch grocery instances, since there are none stored in "
              + "food storage!");
    }

    ArrayList<Integer> groceryInstanceIndexes = new ArrayList<>();
    int currentIndex;

    // Adds one value, then checks if the user wants to add more values.
    // If yes, give the user an input and restart the
    // loop, if no return the values that are already in the groceryInstanceIndexes ArrayList.
    // The method also makes sure the user doesn't add the same index twice.
    while (true) {
      try {
        tableCreator.groceryInstanceTable(foodStorage.getAllGroceryInstances());

        System.out.println("\nCurrent added values: " + groceryInstanceIndexes);

        System.out.println(
            "Enter another value: (1 - " + foodStorage.getAllGroceryInstances().size() + ").");

        currentIndex = sc.nextInt();

        if (currentIndex > 0 && currentIndex <= foodStorage.getAllGroceryInstances().size()
            && !groceryInstanceIndexes.contains(currentIndex)) {
          groceryInstanceIndexes.add(currentIndex);
        } else {
          clearScreen();
          System.out.println("Please enter an integer (1 - " +
              foodStorage.getAllGroceryInstances().size()
              + "), that you haven't already added! \n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println("Please enter an integer (1 - " +
            foodStorage.getAllGroceryInstances().size() + "), that you haven't already added!\n");
      }

      tableCreator.groceryInstanceTable(foodStorage.getAllGroceryInstances());

      System.out.println("\nCurrent added values: " + groceryInstanceIndexes);

      sc.nextLine();
      String yesNoSc = yesNoOption(sc,
          "Would you like to add another value (Currently added values: " +
              groceryInstanceIndexes + ")? (Y/N)");

      if (yesNoSc.equals("n")) {
        return groceryInstanceIndexes;
      }
    }
  }

  // methods for adding specific values for GroceryInstance and GroceryTypes:
  // for GroceryInstances:

  /**
   * Gives a dialog tailor-made for a GroceryInstance's best before date.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog message that displays above the user input.
   * @return the date in a valid string.
   */
  public String validBestBeforeDateOption(Scanner sc, String dialogMessage) {
    while (true) {
      clearScreen();
      System.out.println(dialogMessage + " (DD.MM.YYYY)?");

      // if the date is valid, return the date string.
      try {
        String bestBeforeDate = sc.nextLine();
        if (isValidDate(bestBeforeDate)) {
          return bestBeforeDate;
        } else {
          clearScreen();
          System.out.println("Please enter a valid best before date! (DD.MM.YYY)\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println("Please enter a valid best before date! (DD.MM.YYY)\n");
      }
    }
  }


}
