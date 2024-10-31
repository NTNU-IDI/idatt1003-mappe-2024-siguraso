package edu.ntnu.idi.idatt.Interface;

import java.util.Scanner;

public class DialogOptionCreator {

  // constructor
  public DialogOptionCreator() {
  }

  // general methods:

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // method for the different types of dialog options

  /**
   * Gives a dialog window that returns either y/Y or n/N based on the users input.
   *
   * @param dialogMessage Message to display above the user input.
   * @param sc            Scanner used for user input
   * @return either y/Y or n/N as a String.
   */
  public String yesNoOption(Scanner sc, String dialogMessage) {
    while (true) {
      System.out.println(dialogMessage + " (Y/N)?");

      try {
        String yesNoSc = sc.nextLine();

        //if the user inputs either y or n, it returns the input
        if (yesNoSc.equalsIgnoreCase("Y") || yesNoSc.equalsIgnoreCase("N")) {
          clearScreen();
          return dialogMessage;
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
}
