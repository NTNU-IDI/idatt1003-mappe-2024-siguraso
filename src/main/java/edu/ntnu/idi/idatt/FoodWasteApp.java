package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.views.UserInterface;

/**
 * The main class of the FoodWasteApp. This class is responsible for starting the application. This
 * class is more or less a wrapper for the main static method.
 *
 * @author siguraso
 * @version 3.0
 * @since 0.1
 */
public class FoodWasteApp {

  /**
   * Main method that runs the initiation and start process in the {@link UserInterface} class.
   *
   * @param args the arguments that are passed to the main method.
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();

    ui.init();
    ui.start();
  }
}
