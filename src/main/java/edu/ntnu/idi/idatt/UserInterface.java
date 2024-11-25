package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.utils.TUI;

/**
 * Class that creates the menus of the program, and handles the user input for what the user wants
 * to do
 */
public class UserInterface {

  // main menu and sub-menus

  /**
   * The main menu that branches out to the different other menus.
   * <p>The menu looks something like this:</p>
   * <p>[1] Manage food storage.</p>
   * <p>[2] Manage grocery classes.</p>
   * <p>[3] Manage cookbook/recipes.</p>
   * <p>[4] Quit.</p>
   *
   * @param utils used to create the menu, and handles the user input so that the user inputs a
   *              valid input
   * @return the integer (of the primitive "int" type) to know what the user wants to initiate.
   */
  public int mainMenu(TUI utils) {
    return utils.choiceWindow(
        new String[]{"Manage food storage.", "Manage grocery classes.", "Manage cookbook/recipes.",
            "Quit."}, "What do you wish to do?");
  }

  /**
   * The main menu that branches out to the different other menus.
   * <p>The menu looks something like this:</p>
   * <p>[1] Add grocery to storage.</p>
   * <p>[2] Remove grocery from storage.</p>
   * <p>[3] Search in food storage.</p>
   * <p>[4] Display out of date food only.</p>
   * <p>[5] Edit specific grocery (e.g. edit amount, best before date and price per unit).</p>
   * <p>[6] Check the value of multiple groceries..</p>
   * <p>[7] Remove amount from specific grocery</p>
   * <p>[8] Return to main menu.</p>
   *
   * @param utils used to create the menu, and handles the user input so that the user inputs a
   *              valid input
   * @return the integer (of the primitive "int" type) to know what the user wants to initiate.
   */
  public int foodStorageMenu(TUI utils) {
    return utils.choiceWindow(new String[]{"Add grocery to storage.", "Remove grocery from storage",
        "Search in food storage.", "Display out of date food only",
        "Edit specific grocery (e.g. edit amount, best before date and price per unit).",
        "Check the "
            + "value of multiple groceries.", "Return to main menu."}, "Food storage:");
  }


}
