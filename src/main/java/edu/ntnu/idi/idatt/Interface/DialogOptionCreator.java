package edu.ntnu.idi.idatt.Interface;


import java.util.Scanner;
import edu.ntnu.idi.idatt.Grocery.FoodStorage;

public class DialogOptionCreator {

  // constructor
  public DialogOptionCreator() {
  }

  // general methods:

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  //checks if a date is valid based on the date format used throughout the program.
  public static boolean isValidDate(String dateString) {
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

  /**
   * Gives a dialog menu that takes the user input to determine what grocery type the user wants to
   * initiate.
   *
   * @param sc            Scanner for user input.
   * @param dialogMessage dialog message that displays over the user input.
   * @param foodStorage   food storage that contains the available GroceryTypes.
   * @param tableCreator  used to create a table to display all the available GroceryTypes.
   * @return an integer that determines the index of what GroceryType the user wants to initiate.
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

  // methods for adding specific values for GroceryInstance and GroceryTypes:

  // for Grocery Types:

  /**
   * Gives a dialog tailor made for defining a GroceryType's measurement unit.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog message that displays over the user input.
   * @return a String containing the measurement unit defined by the user.
   */
  public String validMeasurementUnitOption(Scanner sc, String dialogMessage) {
    while (true) {
      System.out.println(dialogMessage + " (enter a measurement unit, e.g. 'kg', 'pcs.', etc., "
          + "max 9 characters.)?");

      try {
        String unitName = sc.nextLine();

        // if the measurement unit is 9 characters or less it is valid.
        if (unitName.length() <= 9) {
          clearScreen();
          return unitName;
        } else {
          clearScreen();
          System.out.println("Please enter a measurement unit that's max 9 characters!\n\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println("Please enter a measurement unit that's max 9 characters!\n\n");
      }

    }
  }

  /**
   * Gives a dialog tailor made for defining a GroceryType's name.
   *
   * @param sc            Scanner used for the user input.
   * @param dialogMessage Dialog message that displays over the user input
   * @return a string tha contains the name of the GroceryType.
   */
  public String validTypeNameOption(Scanner sc, String dialogMessage) {
    while (true) {
      System.out.println(dialogMessage + " (max 17 characters)?");

      try {
        String typeName = sc.nextLine();

        // if the name given is 17 characters or shorter, it is valid.
        if (typeName.length() <= 17) {
          clearScreen();
          return typeName;
        } else {
          clearScreen();
          System.out.println("Please enter a type that's max 17 characters!\n\n");
        }
      } catch (Exception e) {
        clearScreen();
        System.out.println("Please enter a type that's max 17 characters!\n\n");
      }
    }
  }

  // for GroceryInstances:

  /**
   * Gives a dialog window that returns a double that is in the given character limit determined by
   * the GroceryInstance table.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog message that diplays above the user input.
   * @return A valid double for price per unit.
   */
  public double validPricePerUnitOption(Scanner sc, String dialogMessage) {
    while (true) {
      try {
        System.out.println(dialogMessage + " (0 - 999999)?");

        double pricePerUnit = sc.nextDouble();

        // if it is a valid input (meaning not a negative number or a number above 999999)
        // it breaks the loop and continues.
        if (pricePerUnit > 0 && pricePerUnit < 1000000) {
          return pricePerUnit;
        }
        // if it isnt a valid input, restart the loop, and encourage the user
        // to input a valid number.
        else {
          clearScreen();
          System.out.println("Please enter a price 0 - 999999\n\n");
        }

      } catch (Exception e) {
        // if the user inputs something other than an integer,
        // restart the loop and enourage the user again.
        clearScreen();
        System.out.println("Please enter a price 0 - 999999\n\n");
      }
    }
  }

  /**
   * Gives a dialog tailor made to define a GroceryInstance's amount.
   *
   * @param sc            Scanner used for the user input.
   * @param dialogMessage Dialog message that displays above the user input.
   * @return a double that defines the amount of a GroceryInstance
   */
  public double validAmountOption(Scanner sc, String dialogMessage) {
    while (true) {
      System.out.println(dialogMessage + " (0 - 999)?");

      try {
        double amount = sc.nextDouble();

        // if it is a valid input (meaning not a negative number or a number above 999)
        // it breaks the loop and continues.
        if (amount > 0 && amount < 999) {
          return amount;
        }
        // if it isnt a valid input, restart the loop, and encourage the user
        // to input a valid number.
        else {
          clearScreen();
          System.out.println("Please enter a price 0 - 999\n\n");
        }

      } catch (Exception e) {
        // if the user inputs something other than an integer,
        // restart the loop and enourage the user again.
        clearScreen();
        System.out.println("Please enter a price 0 - 999\n\n");

      }
    }
  }

  /**
   * Gives a dialog tailor made for a GroceryInstance's best before date.
   *
   * @param sc            Scanner used for user input.
   * @param dialogMessage Dialog message that displays above the user input.
   * @return the date in a valid string.
   */
  public String validBestBeforeDateOption(Scanner sc, String dialogMessage) {
    while (true) {
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
