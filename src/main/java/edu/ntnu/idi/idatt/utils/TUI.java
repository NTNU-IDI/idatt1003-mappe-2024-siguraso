package edu.ntnu.idi.idatt.utils;

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
   * An instance of the TUI class handles a all of the
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
    String input = sc.nextLine();
    if (sc.hasNextLine()) {
      return input;
    }

    return "";
  }

  /**
   * Closes the user-input {@link Scanner} to avoid memory leaks.
   */
  public void closeInput() {
    sc.close();
  }

  public int get

  public int choiceWindow(String[] choices) {
    List<String> choicesList = Arrays.asList(choices);

    choicesList.forEach(choice -> System.out.println("[" + (choicesList.indexOf(choice) + 1) + "] "
        + choice));

    int choice = Integer.parseInt(this.getInput());
  }

}
