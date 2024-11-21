package edu.ntnu.idi.idatt.utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An instance of the ChoiceWindow class' main goal is to return an integer that corresponds to
 * different actions and functions in the program. The integer is used to define what the user wants
 * to do with a given set of options. Once the instance initiates the 'choiceSequence()' method, it
 * creates a user-friendly choice window that avoids any kind of error message so that the user can
 * safely operate the program without having to worry about running into any errors when deciding
 * what to do.
 */
public class ChoiceWindow {

  // variables
  private final ArrayList<String> choices = new ArrayList<>();

  // Contstructor

  /**
   * Class used to construct a terminal-based choice window formatted like this (here, with 3
   * choices as an example):
   * <p>**Dialog message** (Enter an integer 1 - 3)
   * <p>[1] *Choice1*
   * <p>[2] *Choice 2*
   * <p>[3] *Choice 3*
   * <p>The main goal of this class is to return an integer to enable the user to initiate the
   * choices displayed on-screen
   */
  public ChoiceWindow() {
  }

  // Methods

  /**
   * Creates the string shown on screen in the format:
   * <p>[1] *Choice1*
   * <p>[2] *Choice 2*
   * <p>[3] ...
   * <p>Only used privately within the class for the choiceSequence method.
   */
  private String getChoiceString() {
    StringBuilder choiceString = new StringBuilder();

    // for-loop that builds a string based on the choices given through the addChoice method.
    for (int i = 0; i < this.choices.size(); i++) {
      choiceString.append("\n[").append(i + 1).append("] ").append(this.choices.get(i));
    }

    return choiceString.toString();
  }

  /**
   * Clears the terminal window.
   */
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Adds a choice that will display in the final choice string shown to the user. Keep in mind that
   * the choices appended using this method gets displayed in the order you appended the choice
   * strings in, in other words, the choice strings get displayed in chronologically from the lowest
   * index to the highest.
   *
   * @param choice Functionality you want to add to the choice window, given as a string.
   */
  public void addChoice(String choice) {
    this.choices.add(choice);
  }

  /**
   * Displays the choices, based on what choices have been added using the addChoice method, and a
   * dialog message defined by the user.
   *
   * @param dialogMessage Dialog message that appears over the main choices, used to, for example,
   *                      further explain the choices shown below
   * @param sc            Scanner that will be used as the user's form of input.
   * @return The integer given by the user.
   */
  public int choiceSequence(String dialogMessage, Scanner sc) {
    // a while loop, so that if the program catches an error, the user is allowed to try again.
    while (true) {
      try {
        // prints the specified dialog message, as well as displaying instructions fot the user.
        System.out.println(dialogMessage + "(Enter an integer 1 - " + this.choices.size() + ")");
        // prints out the choiceString using the getChoiceString method.
        System.out.println(this.getChoiceString());

        int choice = sc.nextInt();

        // if the user inputs an invalid number, throw an illegal argument exception, if the input
        // is valid, clear the screen and return the integer specified by the user
        if (choice < 1 || choice > this.choices.size()) {
          throw new IllegalArgumentException("Enter an integer 1 - " + this.choices.size());
        } else {
          clearScreen();
          return choice;
        }
      } catch (Exception e) {
        // if the program runs into an error (e.g. the user inputs a string etc.) give the user a
        // friendly message with instructions.
        sc.nextLine();
        clearScreen();
        System.out.println("Enter an integer 1 - " + this.choices.size() + "\n");
      }

    }

  }

}
