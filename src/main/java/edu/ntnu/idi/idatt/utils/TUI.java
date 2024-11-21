package edu.ntnu.idi.idatt.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

  /**
   * Clears the terminal window.
   */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Gives a dialog window that checks if the user enters an input containing an integer (the
   * primitive type "int").
   *
   * @param message The dialog message that will be displayed.
   * @return a user-defined valid integer (the primitive type "int").
   */
  public int integerOption(String message) {

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
   */
  public double doubleOption(String message) {
    System.out.println(message);

    try {
      return Double.parseDouble(this.getInput());
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Please enter a valid decimal number!");
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

    String choicesString = message + "\n\n" + choicesList.stream()
        .map(Object::toString)
        .collect(Collectors.joining("\n")) + "\n";

    boolean hasEnteredValidInteger = false;
    int input = 0;

    do {
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
        System.out.println(e.getMessage());
      }
    } while (!hasEnteredValidInteger);

    return input;
  }


}
