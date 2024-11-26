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

      utils.clearScreen();
      switch (foodStorageChoice) {
        // add grocery
        case 1 -> {
          addGroceryInstance();
          foodStorage.sortGroceryTypes();
        }

        // remove grocery
        case 2 -> {
          String removeConfirmation = uiYesNoOption(
              "Do you really wish to remove a grocery from the food storage? (Y/n)");

          if (removeConfirmation.equals("y")) {
            removeGroceryInstance();
          }
        }

        // search groceries
        case 3 -> searchInFoodStorage();

        // display out of date food
        case 4 -> displayOutOfDate();

        // edit grocery
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
        case 1 -> {
          addGroceryType();
          foodStorage.sortGroceryTypes();
        }

        // remove grocery type
        case 2 -> {
          utils.clearScreen();
          String removeConfirmation = uiYesNoOption(
              "Do you really wish to remove a grocery class? (Y/n");

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

  // methods for adding, removing and editing the different types of objects to the
  // food storage/cookbook.

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

    String keepCurrentType = uiYesNoOption("Is this ok? (Y/n) \n\nName: " + foodStorage
        .getAllGroceryTypes().getLast().getName() + "\nUnit: " + foodStorage.getAllGroceryTypes()
        .getLast().getMeasurementUnit());

    if (keepCurrentType.equals("n")) {
      //if the user doesnt want to keep it, remove it from the list.
      foodStorage.getAllGroceryTypes().removeLast();
    }
  }

  /**
   * This method takes a user input that decides which {@link GroceryType} to remove from the
   * {@link FoodStorage} based on a given index.
   */
  private void removeGroceryType() {
    utils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(
          utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nPlease enter the number of the grocery type you would like to remove "
              + "(See Num on the above table).", foodStorage.getAllGroceryTypes().size());

      utils.clearScreen();

      String curRemoved = foodStorage.getAllGroceryTypes().get(removeIndex).getName();

      foodStorage.removeType(removeIndex);

      System.out.println("Successfully removed grocery type: " + curRemoved + "."
          + "\n\nPress ENTER to continue.");

      utils.getInput();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      utils.getInput();
    }
  }

  /**
   * This method takes a series of user inputs that decides how to edit a given
   * {@link GroceryType}.
   */
  private void editGroceryType() {
    utils.clearScreen();

    try {
      int editIndex = chooseValidListIndex(
          utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nPlease enter the number of the grocery type you would like to edit "
              + "(See Num on the above table).", foodStorage.getAllGroceryTypes().size());

      utils.clearScreen();

      boolean hasEnteredValidName = false;

      while (!hasEnteredValidName) {
        try {
          System.out.println(
              "What do you wish to change the name to? (from '" + foodStorage.getSpecificType(
                      editIndex)
                  .getName() + "') (leave empty if you want it unchanged.) \n(max 30 characters).");

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

      utils.clearScreen();

      boolean hasEnteredValidUnit = false;

      while (!hasEnteredValidUnit) {
        try {
          System.out.println("What do you wish to change the measurement unit to? (from '"
              + foodStorage.getSpecificType(editIndex).getMeasurementUnit()
              + "') (leave empty if you want it unchanged.) (max 16 characters)");

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

      foodStorage.sortGroceryTypes();
    } catch (IllegalArgumentException e) {
      utils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");

      utils.getInput();
    }
  }

  /**
   * This method takes a series of inputs that lets the user define a new instance of
   * {@link GroceryInstance} to add to the {@link FoodStorage}
   */
  private void addGroceryInstance() {
    try {
      int typeIndex;

      String makeNewType = uiYesNoOption(utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
          + "\nWould you like to add a grocery based on an existing grocery class? (Y/n)");

      if (makeNewType.equals("n")) {
        utils.clearScreen();
        addGroceryType();
        // gets the size of the arraylist, so that it always gets the last element (the newest one).
        typeIndex = foodStorage.getAllGroceryTypes().size();
      } else {
        utils.clearScreen();
        typeIndex = chooseValidListIndex(utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
                + "\nPlease enter the number of the grocery class you wish to select (See Num in the above table)",
            foodStorage.getAllGroceryTypes().size());
      }

      foodStorage.addInstance(new GroceryInstance(foodStorage.getSpecificType(typeIndex), 0.1,
          0.1, null));

      boolean hasEnteredValidAmount = false;

      while (!hasEnteredValidAmount) {
        try {
          utils.clearScreen();

          double amount = utils.doubleOption("Please enter the amount of '" +
              foodStorage.getSpecificType(typeIndex).getMeasurementUnit() + "' of '" +
              foodStorage.getSpecificType(typeIndex).getName() +
              "' that you would like to add to the food storage. (0.0 - 999.9)");

          foodStorage.getAllGroceryInstances().getLast().setAmount(amount);

          hasEnteredValidAmount = true;
        } catch (Exception e) {
          System.out.println(e.getMessage() + "\n\n");
        }
      }

      boolean hasEnteredValidPrice = false;

      while (!hasEnteredValidPrice) {
        try {
          double pricePerUnit = utils.doubleOption("Please enter the price per '" + foodStorage
              .getSpecificType(typeIndex).getMeasurementUnit() +
              "' (enter a decimal number 0.0 - 99999.9).");

          foodStorage.getAllGroceryInstances().getLast().setPricePerUnit(pricePerUnit);

          hasEnteredValidPrice = true;
        } catch (Exception e) {
          System.out.println(e.getMessage() + "\n\n");
        }
      }

      boolean hasEnteredValidBestBefore = false;

      while (!hasEnteredValidBestBefore) {
        System.out.println("Please enter the best before date of the grocery (DD.MM.YYYY)");

        String bestBefore = utils.getInput();

        try {
          foodStorage.getAllGroceryInstances().getLast().setBestBeforeDate(bestBefore);
          hasEnteredValidBestBefore = true;

        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage() + "\n\n");
        }
      }
      utils.clearScreen();

      String isInstanceOK = uiYesNoOption(
          "Name: " + foodStorage.getSpecificType(typeIndex).getName() +
              "\nMeasurement Unit: " + foodStorage.getSpecificType(typeIndex).getMeasurementUnit() +
              "\nAmount: " + foodStorage.getAllGroceryInstances().getLast().getAmount() + " "
              + foodStorage.getSpecificType(typeIndex).getMeasurementUnit() +
              "\nPrice per " + foodStorage.getSpecificType(typeIndex).getMeasurementUnit() + ": "
              + foodStorage.getAllGroceryInstances().getLast().getPricePerUnit() +
              "\nBest before: " + foodStorage.getAllGroceryInstances().getLast()
              .getBestBeforeString() + "\n\nIs this OK? (Y/n)");

      if (isInstanceOK.equals("n")) {
        foodStorage.getAllGroceryInstances().removeLast();
      }

      foodStorage.sortGroceryTypes();
    } catch (IllegalArgumentException e) {

      utils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      utils.getInput();
    }
  }

  /**
   * This method takes a user input that decides which {@link GroceryInstance} to remove from the
   * food storage.
   */
  private void removeGroceryInstance() {
    utils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(
          utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nPlease enter the number of the grocery type you would like to remove "
              + "(See Num on the above table).", foodStorage.getAllGroceryInstances().size());

      utils.clearScreen();

      foodStorage.removeInstance(removeIndex);
    } catch (Exception e) {
      utils.clearScreen();

      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      utils.getInput();
    }
  }

  /**
   * This method takes a user input that defines a search term, which then gets searched for, which
   * then displays a {@link TUI} table that displays the search results.
   */
  private void searchInFoodStorage() {
    System.out.println("Please enter your search term below:");
    String searchTerm = utils.getInput();

    utils.clearScreen();

    System.out.println(
        "Search restults for the term '" + searchTerm + "': \n\n" + utils.foodStorageTable(
            new ArrayList<>(foodStorage.groceryInstanceSearch(searchTerm)))
            + "\n\nPress ENTER to continue...");

    utils.getInput();
  }

  /**
   * Method that more or less prints out all of the out of date food in the food storage, as well as
   * the cumulative value of said groceries.
   */
  private void displayOutOfDate() {
    System.out.println(
        "All out of date food:\n\n" + utils.foodStorageTable(foodStorage.getOutOfDateInstances())
            + "\n\nTotal Value: " + foodStorage.getOutOfDateValue()
            + "\n\nPress ENTER to continue...");

    utils.getInput();
  }

  // methods entering specific elements

  /**
   * This method creates a menu that makes sure the user picks an integer within a given lists
   * index.
   *
   * @param message      dialog message as a {@link String} that displays above the user input.
   * @param lengthOfList the length of a given list.
   * @return the index of the given {@link GroceryType} that the user wants to initiate.
   * @throws IllegalArgumentException id the given list length is empty.
   */
  private int chooseValidListIndex(String message, int lengthOfList)
      throws IllegalArgumentException {
    if (lengthOfList == 0) {
      throw new IllegalArgumentException(
          "Cannot fetch any groceries, since there are none!");
    } else {
      boolean hasEnteredValidIndex = false;
      int index = 0;

      while (!hasEnteredValidIndex) {
        try {
          index = utils.integerOption(message);

          if (index < 1 || index > lengthOfList) {
            utils.clearScreen();
            System.out.println(
                "Please enter an integer 1 - " + lengthOfList + ".\n");
          } else {
            hasEnteredValidIndex = true;
          }
        } catch (NumberFormatException e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
      utils.clearScreen();

      return index;
    }
  }

  /**
   * This method is used to display a dialog option that lets the user pick a double within a given
   * intervall. If the given double is outside of this interval, the user gets instructed to try
   * again until they enter a valid double.
   *
   * @param message dialog message given as a {@link String} that is shown above the user input.
   * @param start   start of the interval (the lowest value the user can input) (given as an int).
   * @param end     end of the interval (the highest value the user can input) (given as an int).
   * @return a valid double defined by the user.
   */
  private double doubleOptionParameter(String message, double start, double end) {
    boolean hasEnteredValidDouble = false;
    double value = 0;
    while (!hasEnteredValidDouble) {
      try {
        value = utils.doubleOption(message +
            "(Please enter a decimal number " + start + " - " + end + ".");

        if (value >= start && value <= end) {
          hasEnteredValidDouble = true;
        } else {
          utils.clearScreen();
          System.out.println(
              "Please enter a decimal number between " + start + " and " + end + ".\n");
        }
      } catch (NumberFormatException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n");
      }
    }

    return value;
  }

  /**
   * This method is a UI method that uses the {@link TUI} method yesNoOption, and makes it more user
   * friendly by, for instance, catching the errors that method throws.
   *
   * @param message dialog message given as a {@link String} that displays above the user input.
   * @return a string containing either "y" or "n" (lowercase) that indicates yes or no from the
   * user.
   */
  private String uiYesNoOption(String message) {
    boolean hasEnteredYOrN = false;
    String yesNoChoice = "";

    while (!hasEnteredYOrN) {
      try {
        yesNoChoice = utils.yesNoOption(message);
        hasEnteredYOrN = true;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    return yesNoChoice;
  }

}
