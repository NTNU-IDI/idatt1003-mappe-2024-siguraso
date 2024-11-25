package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.modules.Cookbook;
import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.GroceryInstance;
import edu.ntnu.idi.idatt.modules.GroceryType;
import edu.ntnu.idi.idatt.modules.Recipe;
import edu.ntnu.idi.idatt.utils.TUI;
import java.util.ArrayList;

/**
 * Class that creates the menus of the program, and handles the user input for what the user wants
 * to do
 */
public class UserInterface {

  private final FoodStorage foodStorage = new FoodStorage();
  private final Cookbook cookBook = new Cookbook();
  private final TUI utils = new TUI();

  // init and start methods.

  /**
   * Initializes the program by adding a standard library of {@link GroceryType} as well as a demo
   * of a {@link Recipe} to the {@link Cookbook}.
   */
  public void init() {

    foodStorage.addType(new GroceryType("Tomato", "kg"));
    foodStorage.addType(new GroceryType("Milk", "L"));
    foodStorage.addType(new GroceryType("Pepsi", "L"));
    foodStorage.addType(new GroceryType("Bread", "pcs."));
    foodStorage.addType(new GroceryType("Union", "pcs."));
    foodStorage.addType(new GroceryType("Butter", "pcs."));
    foodStorage.addType(new GroceryType("Salt - Regular container", "pcs."));
    foodStorage.addType(new GroceryType("Oregano - Regular container", "pcs."));
    foodStorage.addType(new GroceryType("Garlic - 2 Pack", "pcs."));

    ArrayList<GroceryInstance> marinaraIngredients = new ArrayList<>();
    ArrayList<String> approx = new ArrayList<>();
    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(1),
        0.4, 0.0, null));
    approx.add("400 grams of tomato");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(6), 1,
        0.0, null));
    approx.add("2 Tablespoons");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(8), 1,
        0.0, null));
    approx.add("2 Teaspoons");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(9), 1,
        0.0, null));
    approx.add("A 2 pack of garlic");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(7), 1,
        0.0, null));
    approx.add("1 Teaspoon");

    ArrayList<String> instructions = new ArrayList<>();
    instructions.add(
        "Melt butter in a casserole on medium heat, meanwhile, cook the garlic in a separate pan");
    instructions.add("Once the butter is melted, add your garlic, oregano, and tomatoes.");
    instructions.add("Slow boil for around 15-20 minutes.");
    instructions.add("Once boiled, add your salt, and serve!");

    cookBook.addRecipe(new Recipe("Marinara Sauce",
        "4 servings of classic marinara sauce, perfect for italian dishes.", instructions,
        marinaraIngredients, approx));
  }

  public void start() {
    utils.clearScreen();
    mainMenu();
    // When the main menu tells the program to quit, close the scanner from the TUI, and close the
    // finish the script.

    utils.closeInput();
    utils.clearScreen();
  }

  // main menu and sub-menus Initiaters:

  /**
   * Displays the main menu that branches out to the different other menus.
   * <p>The menu looks something like this:</p>
   * <p>[1] Manage food storage.</p>
   * <p>[2] Manage grocery classes.</p>
   * <p>[3] Manage cookbook/recipes.</p>
   * <p>[4] Quit.</p>
   *
   * @return the integer (of the primitive "int" type) to know what the user wants to initiate.
   */
  private int initiateMainMenuAction() {
    utils.clearScreen();
    return utils.choiceWindow(
        new String[]{"Manage food storage.", "Manage grocery classes.", "Manage cookbook/recipes.",
            "Quit."}, "What do you wish to do?");
  }

  /**
   * Displays the food storage menu which gets the user input for what actions the user wants to
   * initiate that correlates to the {@link FoodStorage}.
   * <p>The menu looks something like this:</p>
   * <p>[1] Add grocery to storage.</p>
   * <p>[2] Remove grocery from storage.</p>
   * <p>[3] Search in food storage.</p>
   * <p>[4] Display out of date food only.</p>
   * <p>[5] Edit specific grocery (e.g. edit amount, best before date and price per unit).</p>
   * <p>[6] Check the value of multiple groceries..</p>
   * <p>[7] Remove amount from specific grocery</p>
   * <p>[8] Return to main menu.</p>
   */
  private int initiateFoodStorageAction() {
    return utils.choiceWindow(
        new String[]{"Add grocery to storage.", "Remove grocery from storage",
            "Search in food storage.", "Display out of date food only",
            "Edit specific grocery (e.g. edit amount, best before date and price per unit).",
            "Check the value of multiple groceries.", "Remove amount from specific grocery",
            "Return to main menu."}, utils.foodStorageTable(foodStorage.getAllGroceryInstances())
            + "\n\nTotal Value of food storage: " + foodStorage.getTotalValue()
            + "\n\nFood storage menu:");
  }

  /**
   * Displays the actions that the user can do in the grocery class menu. Here, the user can select
   * what they want to do by entering an integer.
   * <p>The menu will look something like this:</p>
   * <p>[1] Add a grocery class.</p>
   * <p>[2] Remove a grocery class.</p>
   * <p>[3] Edit a grocery class (e.g. change name or unit)</p>
   * <p>[4] Return to main menu.</p>
   *
   * @return an integer (of the primitive type "int") that tells the program what the user wants to
   * do.
   */
  private int initiateGroceryTypeAction() {
    return utils.choiceWindow(new String[]{"Add a grocery class.", "Remove a grocery class.",
            "Edit grocery class (change name or measurement unit).", "Return to main menu."},
        utils.groceryTypeTable(foodStorage.getAllGroceryTypes()) + "\nManage grocery classes:");
  }

  /**
   * Displays the actions that the user can do in the {@link Cookbook} menu. Here, the user can
   * select what action they would like to do by entering the corresponding integer as shown in the
   * menu.
   * <p>The menu will look something like this:</p>
   * <p>[1] View recipe.</p>
   * <p>[2] Add recipe.</p>
   * <p>[3] Remove recipe.</p>
   * <p>[4] View suggested recipes.</p>
   * <p>[5] Return to main menu.</p>
   *
   * @return an integer (of the primitive type "int") that tells the program what action the user
   * wants to initiate.
   */
  private int initiateCookbookAction() {
    return utils.choiceWindow(new String[]{"View recipe",
            "Add recipe (!Make sure to add the relevant grocery classes beforehand!).",
            "Remove recipe.",
            "View suggested recipes (based on what you have available in the food storage).",
            "Return to main menu."},
        utils.cookbookTable(cookBook.getRecipes(), foodStorage) + "\n\nManage cookbook:");
  }

  // menus that take the initiation values, and does the action.

  /**
   * Takes the user input for what sub-menu the user wants to access, and initiates the given menu.
   */
  private void mainMenu() {
    boolean quitProgram = false;

    do {
      utils.clearScreen();
      int mainMenuChoice = initiateMainMenuAction();
      switch (mainMenuChoice) {
        case 1 -> foodStorageMenu();
        case 2 -> groceryTypeMenu();
        case 3 -> cookbookMenu();
        case 4 -> quitProgram = true;
      }
    } while (!quitProgram);

  }

  /**
   * Takes the user input for what the user wants to do to the {@link FoodStorage} and actually
   * initiates and  does the action in a user friendly way.
   */
  private void foodStorageMenu() {
    boolean returnToMainMenu = false;

    do {
      utils.clearScreen();
      int foodStorageChoice = initiateFoodStorageAction();

      switch (foodStorageChoice) {
        // add grocery
        case 1 -> {

        }
        case 2 -> {
        }
        case 3 -> {
        }
        case 4 -> {
        }
        case 5 -> {
        }
        case 6 -> {
        }
        case 7 -> {
        }
        case 8 -> returnToMainMenu = true;
      }
    } while (!returnToMainMenu);
  }

  /**
   * Takes the user input for what the user wants to do in the compendium of {@link GroceryType},
   * and actually initiates and does the action in a user friendly way..
   */
  private void groceryTypeMenu() {
    boolean returnToMainMenu = false;

    do {
      utils.clearScreen();

      int groceryTypeChoice = initiateGroceryTypeAction();
      switch (groceryTypeChoice) {
        // add grocery type
        case 1 -> addGroceryType();

        // remove grocery type
        case 2 -> {
          utils.clearScreen();
          boolean hasEnteredYOrN = false;
          String removeConfirmation = "";

          while (!hasEnteredYOrN) {
            try {
              removeConfirmation = utils.yesNoOption(
                  "Do you really wish to remove a grocery class? (Y/n)");
              hasEnteredYOrN = true;
            } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage() + "\n\n");
            }
          }

          if (removeConfirmation.equals("y")) {
            removeGroceryType();
          }

        }

        // edit grocery type
        case 3 -> editGroceryType();

        //return to main menu.
        case 4 -> returnToMainMenu = true;
      }
    } while (!returnToMainMenu);
  }

  /**
   * Takes the user input for what the user wants to do in the {@link Cookbook}, and actually does
   * it by initializing different actions in a user-friendly way.
   */
  private void cookbookMenu() {

    boolean returnToMainMenu = false;
    do {
      utils.clearScreen();

      int cookbookChoice = initiateCookbookAction();
      switch (cookbookChoice) {
        case 1 -> {
        }
        case 2 -> {
        }
        case 3 -> {
        }
        case 4 -> {
        }
        case 5 -> returnToMainMenu = true;
      }
    } while (!returnToMainMenu);
  }

  // methods for adding and removing the different types of objects to the food storage/cookbook.

  /**
   * This method takes a series of user inputs, and puts them into a single {@link GroceryType},
   * which is promptly added to the {@link FoodStorage}.
   */
  private void addGroceryType() {
    utils.clearScreen();

    boolean hasEnteredValidName = false;

    foodStorage.addType(new GroceryType(null, null));

    while (!hasEnteredValidName) {
      try {
        System.out.println(
            "What is the name of the grocery class you would like to add? (max 30 characters)");
        String TypeName = utils.getInput();
        foodStorage.getAllGroceryTypes().getLast().setName(TypeName);

        hasEnteredValidName = true;
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    boolean hasEnteredValidUnit = false;

    utils.clearScreen();

    while (!hasEnteredValidUnit) {
      try {
        System.out.println(
            "What is the measurement unit most associated with '" + foodStorage.getAllGroceryTypes()
                .getLast().getName() + "' (e.g. kg, pcs., L, etc.)?");
        String UnitName = utils.getInput();
        foodStorage.getAllGroceryTypes().getLast().setMeasurementUnit(UnitName);

        hasEnteredValidUnit = true;
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    utils.clearScreen();

    // returns either y or n.

    boolean hasEnteredYOrN = false;
    String keepCurrentType = "";

    while (!hasEnteredYOrN) {
      try {
        keepCurrentType = utils.yesNoOption(
            "Is this ok? \n\nName: " + foodStorage.getAllGroceryTypes().getLast().getName() +
                "\nUnit: " + foodStorage.getAllGroceryTypes().getLast().getMeasurementUnit());

        hasEnteredYOrN = true;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    if (keepCurrentType.equals("n")) {
      //if the user doesnt want to keep it, remove it from the list.
      foodStorage.getAllGroceryTypes().removeLast();
    }

    foodStorage.sortGroceryTypes();
  }

  /**
   * This method takes a user input that decides which {@link GroceryType} to remove from the
   * {@link FoodStorage} based on a given index.
   */
  private void removeGroceryType() {
    utils.clearScreen();

    boolean hasEnteredValidIndex = false;
    int removeIndex = 0;

    while (!hasEnteredValidIndex) {
      try {
        removeIndex = utils.integerOption(utils.groceryTypeTable(foodStorage.getAllGroceryTypes()) +
            "\n\nPlease enter the number of the grocery class you wish to remove "
            + "(see 'Num' on the above table).");

        if (removeIndex < 1 || removeIndex > foodStorage.getAllGroceryTypes().size()) {
          utils.clearScreen();
          System.out.println(
              "Please enter an integer 1 - " + foodStorage.getAllGroceryTypes().size() + ".\n");
        } else {
          hasEnteredValidIndex = true;
        }
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n");
      }
    }
    utils.clearScreen();

    foodStorage.removeType(removeIndex);
  }

  /**
   * This method takes a series of user inputs that decides how to edit a given
   * {@link GroceryType}.
   */
  private void editGroceryType() {
    utils.clearScreen();

    boolean hasEnteredValidIndex = false;
    int editIndex = 0;

    while (!hasEnteredValidIndex) {
      try {
        editIndex = utils.integerOption(utils.groceryTypeTable(foodStorage.getAllGroceryTypes()) +
            "\n\nPlease enter the number of the grocery class you wish to edit "
            + "(see 'Num' on the above table).");

        if (editIndex < 1 || editIndex > foodStorage.getAllGroceryTypes().size()) {
          utils.clearScreen();
          System.out.println(
              "Please enter an integer 1 - " + foodStorage.getAllGroceryTypes().size() + ".\n");
        } else {
          hasEnteredValidIndex = true;
        }
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n");
      }
    }

    boolean hasEnteredValidName = false;

    while (!hasEnteredValidName) {
      try {
        System.out.println(
            "What do you wish to change the name to? (from '" + foodStorage.getSpecificType(
                    editIndex)
                .getName() + "') (leave empty if you want it unchanged.) (max 30 characters)");

        String newName = utils.getInput();

        if (!newName.isEmpty()) {
          foodStorage.getSpecificType(editIndex).setName(newName);
        }
        hasEnteredValidName = true;
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    boolean hasEnteredValidUnit = false;

    while (!hasEnteredValidUnit) {
      try {
        System.out.println("What do you wish to change the measurement unit to? (from '"
            + foodStorage.getSpecificType(editIndex).getMeasurementUnit()
            + "') (leave empty if you want it unchanged.) (max 30 characters)");

        String newUnit = utils.getInput();

        if (!newUnit.isEmpty()) {
          foodStorage.getSpecificType(editIndex).setMeasurementUnit(newUnit);
        }
        hasEnteredValidUnit = true;
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }
  }
}
