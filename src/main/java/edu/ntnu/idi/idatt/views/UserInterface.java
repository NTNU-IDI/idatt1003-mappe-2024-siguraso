package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.GroceryInstance;
import edu.ntnu.idi.idatt.models.GroceryType;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.SoundPlayer;
import edu.ntnu.idi.idatt.utils.TerminalUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that creates the menus of the program, and handles the user input for what the user wants
 * to do.
 *
 * @author siguraso
 * @version 3.0
 * @since 0.1
 */
public class UserInterface {

  private final FoodStorage foodStorage = new FoodStorage();
  private final Cookbook cookBook = new Cookbook();
  private final TerminalUtils terminalUtils = new TerminalUtils();
  private final SoundPlayer soundPlayer = new SoundPlayer();

  // init and start methods.

  /**
   * Initializes the program by adding a standard library of {@link GroceryType} as well as a demo
   * of a {@link Recipe} to the {@link Cookbook}.
   */
  public void init() {

    foodStorage.addType(new GroceryType("Tomato - Loose weight", "kg"));
    foodStorage.addType(new GroceryType("Milk - 1L", "pcs."));
    foodStorage.addType(new GroceryType("Bread", "pcs."));
    foodStorage.addType(new GroceryType("Union - Loose weight", "kg"));
    foodStorage.addType(new GroceryType("Butter - 400g container", "pcs."));
    foodStorage.addType(new GroceryType("Salt - 100g container", "pcs."));
    foodStorage.addType(new GroceryType("Oregano - 100g container", "pcs."));
    foodStorage.addType(new GroceryType("Garlic - 2 Pack", "pcs."));

    foodStorage.addInstance(
        new GroceryInstance(foodStorage.getSpecificType(1), 0.5, 23.0, "01.01.2025"));
    foodStorage.addInstance(
        new GroceryInstance(foodStorage.getSpecificType(5), 1, 15.0, "10.12.2024"));
    foodStorage.addInstance(
        new GroceryInstance(foodStorage.getSpecificType(6), 1, 10.0, "01.01.2026"));
    foodStorage.addInstance(
        new GroceryInstance(foodStorage.getSpecificType(7), 1, 10.0, "11.12.2024"));
    foodStorage.addInstance(
        new GroceryInstance(foodStorage.getSpecificType(8), 1, 10.0, "02.01.2025"));

    //example recipe marinara sauce
    ArrayList<GroceryInstance> marinaraIngredients = new ArrayList<>();
    ArrayList<String> approx = new ArrayList<>();
    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(1),
        0.4, 0.0, null));
    approx.add("400 grams of tomato");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(5), 1,
        0.0, null));
    approx.add("2 Tablespoons");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(7), 1,
        0.0, null));
    approx.add("2 Teaspoons");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(8), 1,
        0.0, null));
    approx.add("A 2 pack of garlic");

    marinaraIngredients.add(new GroceryInstance(foodStorage.getSpecificType(6), 1,
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

    foodStorage.sortGroceryTypes();
  }

  /**
   * Starts the program by clearing the screen, and then initiating the main menu. Once the user
   * initiates the quit action, the program will close the scanner from the Utils, and close the
   * program.
   */
  public void start() {
    terminalUtils.clearScreen();
    mainMenu();
    // When the main menu tells the program to quit, close the scanner from the Utils, and close the
    // finish the script.

    terminalUtils.closeInput();
    terminalUtils.clearScreen();
  }

  // main menu and sub-menus Initiators:

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
    String soundStatus = soundPlayer.isMuted() ? "Muted" : "Unmuted";

    terminalUtils.clearScreen();
    return choiceWindow(
        new String[]{"Manage food storage.", "Manage grocery classes.", "Manage cookbook/recipes.",
            "Toggle sound mute. (Currently: " + soundStatus + ")", "Quit."},
        "What do you wish to do?");
  }

  /**
   * Displays the food storage menu which gets the user input for what actions the user wants to
   * initiate that correlates to the {@link FoodStorage}.
   * <p>The menu looks something like this:</p>
   * <p>[1] Add grocery to storage.</p>
   * <p>[2] Remove grocery from storage.</p>
   * <p>[3] Search in food storage.</p>
   * <p>[4] Display expired food only.</p>
   * <p>[5] Edit specific grocery (e.g. edit amount, best before date and price per unit).</p>
   * <p>[6] Check the value of multiple groceries..</p>
   * <p>[7] Remove amount from specific grocery</p>
   * <p>[8] Return to main menu.</p>
   */
  private int initiateFoodStorageAction() {
    String valueFormat = "Total value of food storage: %.2f kr";

    return choiceWindow(
        new String[]{"Add grocery to storage.", "Remove grocery from storage",
            "Search in food storage.", "Display expired food only",
            "Edit specific grocery (e.g. edit amount, best before date and price per unit).",
            "Check the value of multiple groceries.", "Remove amount from specific grocery",
            "Return to main menu."},
        terminalUtils.foodStorageTable(foodStorage.getAllGroceryInstances())
            + "\n\n" + String.format(valueFormat, foodStorage.getTotalValue())
            + "\n\nManage food storage:");
  }

  /**
   * Displays the actions that the user can do in the grocery class menu. Here, the user can select
   * what they want to do by entering an integer.
   * <p>The menu will look something like this:</p>
   * <p>[1] Add a grocery class.</p>
   * <p>[2] Remove a grocery class.</p>
   * <p>[3] Edit a grocery class (e.g. change name or unit)</p>
   * <p>[4] Return to main menu.</p>
   */
  private int initiateGroceryTypeAction() {
    return choiceWindow(
        new String[]{"Add a grocery class.",
            "Remove a grocery class.",
            "Edit grocery class (change name or measurement unit).", "Return to main menu."},
        terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
            + "\nManage grocery classes:"
    );
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
   */
  private int initiateCookbookAction() {
    return choiceWindow(
        new String[]{"View recipe",
            "Add recipe (!Make sure to add the relevant grocery classes beforehand!).",
            "Remove recipe.",
            "View suggested recipes (based on what you have available in the food storage).",
            "Return to main menu."},
        terminalUtils.cookbookTable(cookBook.getRecipes(), foodStorage) + "\n\nManage cookbook:");
  }

  // menus that take the initiation values, and does the action.

  private void mainMenu() {
    boolean quitProgram = false;

    do {
      terminalUtils.clearScreen();
      int mainMenuChoice = initiateMainMenuAction();
      switch (mainMenuChoice) {
        case 1 -> foodStorageMenu();

        case 2 -> groceryTypeMenu();

        case 3 -> cookbookMenu();

        case 4 -> soundPlayer.toggleSoundMute();

        case 5 -> quitProgram = true;

        default -> {
          terminalUtils.clearScreen();
          System.out.println("Invalid input, please try again.\n\nPress ENTER to continue...");
          terminalUtils.getInput();
          soundPlayer.playInputSound();
        }
      }
    } while (!quitProgram);

  }

  private void foodStorageMenu() {
    boolean returnToMainMenu = false;

    do {
      terminalUtils.clearScreen();
      int foodStorageChoice = initiateFoodStorageAction();

      terminalUtils.clearScreen();
      switch (foodStorageChoice) {
        // add grocery
        case 1 -> {
          addGroceryInstance();
          foodStorage.mergeDuplicateInstances();
          foodStorage.sortGroceryInstances();
          foodStorage.sortGroceryTypes();
        }

        // remove grocery
        case 2 -> removeGroceryInstance();

        // search groceries
        case 3 -> searchInFoodStorage();

        // display out of date food
        case 4 -> displayOutOfDate();

        // edit grocery
        case 5 -> {
          editGroceryInstance();
          foodStorage.mergeDuplicateInstances();
          foodStorage.sortGroceryInstances();
        }

        // find cumulative value of many groceries.
        case 6 -> valueOfMultipleGroceries();

        // remove amount from specific grocery
        case 7 -> removeAmountFromSpecificGrocery();

        //return to main menu
        case 8 -> returnToMainMenu = true;

        default -> {
          terminalUtils.clearScreen();
          System.out.println("Invalid input, please try again.\n\nPress ENTER to continue...");
          terminalUtils.getInput();
          soundPlayer.playInputSound();
        }
      }
    } while (!returnToMainMenu);
  }

  private void groceryTypeMenu() {
    boolean returnToMainMenu = false;

    do {
      terminalUtils.clearScreen();

      int groceryTypeChoice = initiateGroceryTypeAction();
      switch (groceryTypeChoice) {
        // add grocery type
        case 1 -> {
          addGroceryType();
          foodStorage.sortGroceryTypes();
        }

        // remove grocery type
        case 2 -> removeGroceryType();

        // edit grocery type
        case 3 -> {
          editGroceryType();
          foodStorage.sortGroceryTypes();
        }

        //return to main menu.
        case 4 -> returnToMainMenu = true;

        default -> {
          terminalUtils.clearScreen();
          System.out.println("Invalid input, please try again.\n\nPress ENTER to continue...");
          terminalUtils.getInput();
          soundPlayer.playInputSound();
        }
      }
    } while (!returnToMainMenu);
  }

  private void cookbookMenu() {

    boolean returnToMainMenu = false;
    do {
      terminalUtils.clearScreen();

      int cookbookChoice = initiateCookbookAction();
      switch (cookbookChoice) {
        // view recipe
        case 1 -> {
          terminalUtils.clearScreen();
          try {
            int viewIndex = chooseValidListIndex(terminalUtils.cookbookTable(cookBook
                    .getRecipes(), foodStorage) + "\nEnter the number of the recipe you would like "
                    + "to view (See Num on the above table).",
                cookBook.getRecipes().size());

            viewRecipe(viewIndex);
          } catch (Exception e) {
            terminalUtils.clearScreen();
            System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");

            terminalUtils.getInput();
          }
        }

        // create recipe
        case 2 -> createRecipe();

        // remove recipe
        case 3 -> removeRecipe();

        // view suggested recipes
        case 4 -> viewSuggestedRecipes();

        // return to main menu.
        case 5 -> returnToMainMenu = true;

        default -> {
          terminalUtils.clearScreen();
          System.out.println("Invalid input, please try again.\n\nPress ENTER to continue...");
          terminalUtils.getInput();
          soundPlayer.playInputSound();
        }
      }
    } while (!returnToMainMenu);
  }

  // methods for adding, removing and editing the different types of objects to the
  // food storage/cookbook.

  // grocery class menu

  private void addGroceryType() {
    terminalUtils.clearScreen();

    boolean hasEnteredValidName = false;

    foodStorage.addType(new GroceryType(null, null));

    while (!hasEnteredValidName) {
      try {
        System.out.println(
            "What is the name of the grocery class you would like to add? (max 30 characters)");
        String typeName = terminalUtils.getInput();
        foodStorage.getAllGroceryTypes().getLast().setName(typeName);

        hasEnteredValidName = true;
      } catch (IllegalArgumentException e) {
        terminalUtils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    soundPlayer.playConfirmSound();

    boolean hasEnteredValidUnit = false;

    terminalUtils.clearScreen();

    while (!hasEnteredValidUnit) {
      try {
        System.out.println(
            "What is the measurement unit most associated with '" + foodStorage.getAllGroceryTypes()
                .getLast().getName() + "' (e.g. kg, pcs., L, etc.)?");
        String unitName = terminalUtils.getInput();
        foodStorage.getAllGroceryTypes().getLast().setMeasurementUnit(unitName);

        hasEnteredValidUnit = true;
      } catch (IllegalArgumentException e) {
        terminalUtils.clearScreen();
        System.out.println(e.getMessage() + "\n\n");
      }
    }

    soundPlayer.playConfirmSound();

    terminalUtils.clearScreen();

    // returns either y or n.

    String keepCurrentType = uiYesNoOption("Is this ok? (Y/n) \n\nName: " + foodStorage
        .getAllGroceryTypes().getLast().getName() + "\nUnit: " + foodStorage.getAllGroceryTypes()
        .getLast().getMeasurementUnit());

    if (keepCurrentType.equals("n")) {
      //if the user doesnt want to keep it, remove it from the list.
      foodStorage.getAllGroceryTypes().removeLast();
    } else {
      System.out.println("Successfully added grocery class: " + foodStorage.getAllGroceryTypes()
          .getLast().getName() + ".\nPress ENTER to continue...");

      terminalUtils.getInput();

      soundPlayer.playAddSound();
    }
  }

  private void removeGroceryType() {
    terminalUtils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(
          terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nPlease enter the number of the grocery type you would like to remove "
              + "(See Num on the above table).", foodStorage.getAllGroceryTypes().size());

      terminalUtils.clearScreen();

      String curRemoved = foodStorage.getSpecificType(removeIndex).getName();

      String removeConfirm = uiYesNoOption(
          "Do you really wish to remove the grocery class: " + curRemoved + "? (Y/n)");

      if (removeConfirm.equals("y")) {
        foodStorage.removeType(removeIndex);

        terminalUtils.clearScreen();

        System.out.println("Successfully removed grocery class: " + curRemoved + "."
            + "\nPress ENTER to continue.");

        terminalUtils.getInput();

        soundPlayer.playRemoveSound();
      }

      terminalUtils.clearScreen();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      terminalUtils.getInput();
      soundPlayer.playInputSound();
    }
  }

  private void editGroceryType() {
    terminalUtils.clearScreen();

    try {
      int editIndex = chooseValidListIndex(
          terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nPlease enter the number of the grocery type you would like to edit "
              + "(See Num on the above table).", foodStorage.getAllGroceryTypes().size());

      terminalUtils.clearScreen();

      boolean hasEnteredValidName = false;

      while (!hasEnteredValidName) {
        try {
          System.out.println(
              "What do you wish to change the name to? (from '" + foodStorage.getSpecificType(
                      editIndex)
                  .getName() + "') (leave empty if you want it unchanged.) \n(max 30 characters).");

          String newName = terminalUtils.getInput();

          //if the string isnt empty, set the new name.
          if (!newName.isEmpty()) {
            foodStorage.getSpecificType(editIndex).setName(newName);
          }
          hasEnteredValidName = true;

          soundPlayer.playConfirmSound();
        } catch (IllegalArgumentException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }

      terminalUtils.clearScreen();

      boolean hasEnteredValidUnit = false;

      while (!hasEnteredValidUnit) {
        try {
          System.out.println("What do you wish to change the measurement unit to? (from '"
              + foodStorage.getSpecificType(editIndex).getMeasurementUnit()
              + "') (leave empty if you want it unchanged.) (max 16 characters)");

          String newUnit = terminalUtils.getInput();

          if (!newUnit.isEmpty()) {
            foodStorage.getSpecificType(editIndex).setMeasurementUnit(newUnit);
          }
          hasEnteredValidUnit = true;

          soundPlayer.playConfirmSound();
        } catch (IllegalArgumentException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }
    } catch (IllegalArgumentException e) {
      terminalUtils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");

      terminalUtils.getInput();
    }
  }

  //grocerey instance menu

  private void addGroceryInstance() {
    try {
      int typeIndex = 0;

      boolean hasEnteredValidAmount = false;
      boolean hasEnteredValidPrice = false;
      boolean hasEnteredValidBestBefore = false;

      // this boolean is only updated if the user decides to go back to the main menu.
      boolean createNewInstance = true;

      String useOldType = uiYesNoOption(
          terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
              + "\nWould you like to add a grocery based on an existing grocery class? (Y/n)");

      if (useOldType.equals("n")) {
        terminalUtils.clearScreen();

        String makeNewType = uiYesNoOption(
            "Would you like to add a new grocery class? (Y/n)");

        // if user wants to make a new type, make it now, if not return to previous menu.
        if (makeNewType.equals("y")) {
          addGroceryType();

          typeIndex = foodStorage.getAllGroceryTypes().size();

          foodStorage.addInstance(
              new GroceryInstance(foodStorage.getSpecificType(typeIndex), 0.1,
                  0.1, null));
        } else {
          hasEnteredValidAmount = true;
          hasEnteredValidPrice = true;
          hasEnteredValidBestBefore = true;
          createNewInstance = false;
        }

      } else {
        terminalUtils.clearScreen();
        typeIndex = chooseValidListIndex(
            terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
                + "\nPlease enter the number of the grocery class you wish to select "
                + "(See 'Num' in the above table)", foodStorage.getAllGroceryTypes().size());

        foodStorage.addInstance(new GroceryInstance(foodStorage.getSpecificType(typeIndex), 0.1,
            0.1, null));
      }

      terminalUtils.clearScreen();

      while (!hasEnteredValidAmount) {
        try {
          double amount = terminalUtils.doubleOption("Please enter the amount of '"
              + foodStorage.getSpecificType(typeIndex).getMeasurementUnit() + "' of '"
              + foodStorage.getSpecificType(typeIndex).getName()
              + "' that you would like to add to the food storage. (0.0 - 999.9)");

          foodStorage.getAllGroceryInstances().getLast().setAmount(amount);

          hasEnteredValidAmount = true;

          soundPlayer.playConfirmSound();
        } catch (Exception e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }

      while (!hasEnteredValidPrice) {
        try {
          double pricePerUnit = terminalUtils.doubleOption(
              "Please enter the price per '" + foodStorage
                  .getSpecificType(typeIndex).getMeasurementUnit()
                  + "' (enter a decimal number 0.0 - 99999.9).");

          foodStorage.getAllGroceryInstances().getLast().setPricePerUnit(pricePerUnit);

          hasEnteredValidPrice = true;

          soundPlayer.playConfirmSound();
        } catch (Exception e) {
          System.out.println(e.getMessage() + "\n\n");
        }
      }

      while (!hasEnteredValidBestBefore) {
        System.out.println("Please enter the best before date of the grocery (DD.MM.YYYY)");

        String bestBefore = terminalUtils.getInput();

        try {
          foodStorage.getAllGroceryInstances().getLast().setBestBeforeDate(bestBefore);
          hasEnteredValidBestBefore = true;

          soundPlayer.playConfirmSound();
        } catch (IllegalArgumentException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }
      terminalUtils.clearScreen();

      // if user didnt want to create a new type, ignore the code below.
      if (createNewInstance) {

        String isInstanceOk = uiYesNoOption(
            "Name: " + foodStorage.getSpecificType(typeIndex).getName()
                + "\nMeasurement Unit: " + foodStorage.getSpecificType(typeIndex)
                .getMeasurementUnit() + "\nAmount: " + foodStorage.getAllGroceryInstances()
                .getLast().getAmount() + " " + foodStorage.getSpecificType(typeIndex)
                .getMeasurementUnit() + "\nPrice per " + foodStorage.getSpecificType(typeIndex)
                .getMeasurementUnit() + ": " + foodStorage.getAllGroceryInstances().getLast()
                .getPricePerUnit() + "\nBest before: " + foodStorage.getAllGroceryInstances()
                .getLast().getBestBeforeString() + "\n\nIs this OK? (Y/n)");

        if (isInstanceOk.equals("n")) {
          foodStorage.getAllGroceryInstances().removeLast();
        } else {
          terminalUtils.clearScreen();
          System.out.println("Successfully added grocery to the food storage."
              + "\nPress ENTER to continue...");

          terminalUtils.getInput();

          soundPlayer.playAddSound();
        }
      }
    } catch (IllegalArgumentException e) {

      terminalUtils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      terminalUtils.getInput();
      soundPlayer.playInputSound();
    }
  }

  private void removeGroceryInstance() {
    terminalUtils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(
          terminalUtils.foodStorageTable(foodStorage.getAllGroceryInstances())
              + "\nPlease enter the number of the grocery type you would like to remove "
              + "(See Num on the above table).", foodStorage.getAllGroceryInstances().size());

      terminalUtils.clearScreen();

      String curRemoved = foodStorage.getSpecificInstance(removeIndex).getName();

      String removeConfirm = uiYesNoOption(
          "Do you really wish to remove " + foodStorage.getSpecificInstance(removeIndex).getName()
              + " from the food storage? (Y/n)");

      if (removeConfirm.equals("y")) {
        terminalUtils.clearScreen();

        foodStorage.removeInstance(removeIndex);

        System.out.println("Successfully removed " + curRemoved + " from the food storage."
            + "\nPress ENTER to continue...");

        terminalUtils.getInput();

        soundPlayer.playRemoveSound();
      }

      terminalUtils.clearScreen();

    } catch (Exception e) {
      terminalUtils.clearScreen();

      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      terminalUtils.getInput();
      soundPlayer.playInputSound();
    }
  }

  private void searchInFoodStorage() {
    System.out.println("Please enter your search term below:");
    String searchTerm = terminalUtils.getInput();

    soundPlayer.playConfirmSound();

    terminalUtils.clearScreen();

    System.out.println(
        "Search restults for the term '" + searchTerm + "': \n\n" + terminalUtils.foodStorageTable(
            new ArrayList<>(foodStorage.groceryInstanceSearch(searchTerm)))
            + "\n\nPress ENTER to continue...");

    terminalUtils.getInput();

    soundPlayer.playInputSound();
  }

  private void displayOutOfDate() {
    String outOfDateValueFormat = "Total value of expired: %.2f kr";

    System.out.println(
        "All out of date food:\n\n" + terminalUtils.foodStorageTable(
            foodStorage.getOutOfDateInstances())
            + "\n\n" + String.format(outOfDateValueFormat, foodStorage.getOutOfDateValue())
            + "\n\nPress ENTER to continue...");

    terminalUtils.getInput();

    soundPlayer.playInputSound();
  }

  private void editGroceryInstance() {
    try {
      int editIndex = chooseValidListIndex(
          terminalUtils.foodStorageTable(foodStorage.getAllGroceryInstances())
              + "\nPlease enter the number of the grocery you would like to edit. "
              + "(See Num on the above table).", foodStorage.getAllGroceryInstances().size());

      boolean hasEnteredValidAmount = false;

      terminalUtils.clearScreen();

      while (!hasEnteredValidAmount) {
        try {
          double amount = terminalUtils.doubleOption(
              "Please enter the new amount you would like to change to. (Changing from "
                  + foodStorage.getSpecificInstance(editIndex).getAmount()
                  + " " + foodStorage.getSpecificInstance(editIndex).getMeasurementUnit()
                  + ".) (Enter a decimal number 0.0 - 999.9.) "
                  + "\n(Enter '0' if you don't want to change it.)");

          // Since the set method for the grocery instance class doesn't allow for an amount to be 0
          // we make the user input 0 if they don't want to change the value.
          if (amount != 0) {
            foodStorage.getSpecificInstance(editIndex).setAmount(amount);
          }
          hasEnteredValidAmount = true;

          soundPlayer.playConfirmSound();

        } catch (Exception e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      terminalUtils.clearScreen();
      boolean hasEnteredValidPrice = false;

      while (!hasEnteredValidPrice) {
        try {
          double pricePerUnit = terminalUtils.doubleOption(
              "Please enter the price per '" + foodStorage
                  .getSpecificInstance(editIndex).getMeasurementUnit()
                  + "' you would like to change to. (Changing from "
                  + foodStorage.getSpecificInstance(editIndex).getPricePerUnit()
                  + ".) (enter a decimal number 0.0 - 99999.9.)"
                  + "\n(Enter '0' if you dont want to change it.)");

          // Since the set method for the grocery instance class doesn't allow for an amount to be 0
          // we make the user input 0 if they don't want to change the value.
          if (pricePerUnit != 0) {
            foodStorage.getSpecificInstance(editIndex).setPricePerUnit(pricePerUnit);
          }
          hasEnteredValidPrice = true;

          soundPlayer.playConfirmSound();

        } catch (Exception e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      terminalUtils.clearScreen();
      boolean hasEnteredValidBestBefore = false;

      while (!hasEnteredValidBestBefore) {
        System.out.println(
            "Please the best before date you would like to change to (DD.MM.YYYY). (Changing from "
                + foodStorage.getSpecificInstance(editIndex).getBestBeforeString() + ".)"
                + "\n(Leave empty is you don't want to change it.)");

        String bestBefore = terminalUtils.getInput();

        try {
          if (!bestBefore.isEmpty()) {
            foodStorage.getSpecificInstance(editIndex).setBestBeforeDate(bestBefore);
          }
          hasEnteredValidBestBefore = true;

          soundPlayer.playConfirmSound();
        } catch (IllegalArgumentException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
      terminalUtils.clearScreen();

    } catch (Exception e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      terminalUtils.getInput();
      soundPlayer.playInputSound();
    }
  }

  private void valueOfMultipleGroceries() {
    try {
      int[] indexes = chooseMultipleValidListIndex(
          terminalUtils.foodStorageTable(foodStorage.getAllGroceryInstances())
              + "\n(See Num on the above table)", foodStorage.getAllGroceryInstances().size());

      ArrayList<GroceryInstance> combinedValueInstances = new ArrayList<>();

      Arrays.stream(indexes)
          .forEach(index -> combinedValueInstances.add(foodStorage.getSpecificInstance(index)));

      terminalUtils.clearScreen();

      System.out.println(
          terminalUtils.foodStorageTable(combinedValueInstances) + "\nCumulative value: "
              + foodStorage.getMultipleSpecificValue(indexes) + "\n\nPress ENTER to continue...");

      terminalUtils.getInput();

      soundPlayer.playInputSound();

    } catch (Exception e) {
      terminalUtils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
    }
  }

  private void removeAmountFromSpecificGrocery() {
    int removeAmountIndex = chooseValidListIndex(
        terminalUtils.foodStorageTable(foodStorage.getAllGroceryInstances())
            + "\n\nFrom which grocery do you wish to remove?",
        foodStorage.getAllGroceryInstances().size());

    double oldAmount = foodStorage.getSpecificInstance(removeAmountIndex).getAmount();
    double removeAmount = 0;

    boolean hasEnteredValidAmount = false;

    while (!hasEnteredValidAmount) {
      try {
        removeAmount = terminalUtils.doubleOption(
            "How much " + foodStorage.getSpecificInstance(removeAmountIndex).getMeasurementUnit()
                + " of " + foodStorage.getSpecificInstance(removeAmountIndex).getName()
                + " do you wish to remove? (0.0 - "
                + foodStorage.getSpecificInstance(removeAmountIndex).getAmount() + ")");

        foodStorage.removeInstanceAmount(removeAmountIndex, removeAmount);

        hasEnteredValidAmount = true;

        soundPlayer.playConfirmSound();

      } catch (Exception e) {
        terminalUtils.clearScreen();
        System.out.println(e.getMessage() + "\n");
      }
    }

    terminalUtils.clearScreen();

    String yesNoChoice = uiYesNoOption("Remove " + foodStorage.getSpecificType(removeAmountIndex)
        .getName() + " from the food storage?\n\n" + "From old amount: " + oldAmount
        + "\nTo new amount: " + (oldAmount - removeAmount)
        + "\n\nIs this OK? (Y/n)");

    terminalUtils.clearScreen();

    if (yesNoChoice.equalsIgnoreCase("n")) {
      foodStorage.getSpecificInstance(removeAmountIndex).setAmount(oldAmount);
    }
  }

  // cookbook menu

  private void viewRecipe(int viewIndex) {
    Recipe thisRecipe = cookBook.getSpecificRecipe(viewIndex);

    // create a deep copy so that it wont change the properties of the arraylist in the object
    ArrayList<String> instructions = new ArrayList<>(thisRecipe.getInstructions());

    instructions.forEach(instruction -> {
      int thisIndex = instructions.indexOf(instruction);

      instructions.set(thisIndex, (thisIndex + 1) + ": " + instruction);
    });

    System.out.println("Name: " + thisRecipe.getName() + "\nDescription: "
        + thisRecipe.getDescription() + "\n" + terminalUtils.ingredientsTable(
        thisRecipe.getIngredients(),
        thisRecipe.getApproximations()) + "\n\nInstructions:\n" + String.join("\n", instructions));

    System.out.println("\n\nPress ENTER to to go back...");

    terminalUtils.getInput();

    soundPlayer.playInputSound();
  }

  private void createRecipe() {
    // placeholder recipe to make it set-able, so that it can throw the errors at the right time.
    Recipe newRecipe = new Recipe("", "", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>());

    boolean hasEnteredValidName = false;

    while (!hasEnteredValidName) {
      terminalUtils.clearScreen();

      System.out.println("What is the name of the recipe? (max 45 characters)");
      String name = terminalUtils.getInput();
      try {
        newRecipe.setName(name);

        hasEnteredValidName = true;
        soundPlayer.playConfirmSound();
      } catch (IllegalArgumentException e) {
        terminalUtils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    // no need to try the description, since it has no character limit.
    terminalUtils.clearScreen();
    System.out.println("Enter a short description for this recipe.");
    String description = terminalUtils.getInput();
    soundPlayer.playConfirmSound();
    newRecipe.setDescription(description);

    boolean hasEnteredValidInteger = false;

    int amountOfIngredients = 0;

    while (!hasEnteredValidInteger) {
      terminalUtils.clearScreen();

      try {
        amountOfIngredients = terminalUtils.integerOption(
            "How many ingredients are there in this recipe?");

        hasEnteredValidInteger = true;

        soundPlayer.playInputSound();
      } catch (Exception e) {
        terminalUtils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    terminalUtils.clearScreen();

    for (int i = 0; i < amountOfIngredients; i++) {
      int typeIndex = 0;
      try {
        typeIndex = chooseValidListIndex("Ingredient " + (i + 1) + "\n\n"
            + terminalUtils.groceryTypeTable(foodStorage.getAllGroceryTypes())
            + "\nPlease choose which ingredient to add. (Enter the number 'Num' of the ingredient "
            + "you would like to add.)", foodStorage.getAllGroceryTypes().size());
        soundPlayer.playInputSound();
        terminalUtils.clearScreen();
      } catch (IllegalArgumentException e) {
        terminalUtils.clearScreen();
        System.out.println(e.getMessage() + "\n");

      }

      boolean hasEnteredValidAmount = false;

      // placeholder ingredient to add to the recipe so that it throws the exceptions in the right
      // places.
      GroceryInstance newIngredient = new GroceryInstance(foodStorage.getSpecificType(typeIndex),
          0.1, 0.1, null);

      while (!hasEnteredValidAmount) {
        try {
          double ingredientAmount = terminalUtils.doubleOption(
              "How many '" + newIngredient.getMeasurementUnit() + "' of '" + newIngredient.getName()
                  + "' is used in this recipe?");

          newIngredient.setAmount(ingredientAmount);

          soundPlayer.playConfirmSound();

          terminalUtils.clearScreen();
          hasEnteredValidAmount = true;
        } catch (Exception e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      newRecipe.addIngredient(newIngredient);

      terminalUtils.clearScreen();

      boolean hasEnteredValidApproximation = false;

      while (!hasEnteredValidApproximation) {

        System.out.println("What is an approximation for " + newIngredient.getAmount() + " "
            + newIngredient.getMeasurementUnit() + " of " + newIngredient.getName()
            + "? (e.g. 1 tablespoon, 1 teaspoon, cup, etc.)");

        String approximation = terminalUtils.getInput();

        soundPlayer.playInputSound();

        terminalUtils.clearScreen();

        try {
          newRecipe.addApproximation(approximation);
          hasEnteredValidApproximation = true;
        } catch (IllegalArgumentException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
    }

    hasEnteredValidInteger = false;

    int amountOfInstructions = 0;

    terminalUtils.clearScreen();

    while (!hasEnteredValidInteger) {
      try {
        amountOfInstructions = terminalUtils.integerOption(
            "How many steps are there in the instructions to this recipe?");

        hasEnteredValidInteger = true;

        soundPlayer.playInputSound();

        terminalUtils.clearScreen();
      } catch (Exception e) {
        terminalUtils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    terminalUtils.clearScreen();

    for (int i = 0; i < amountOfInstructions; i++) {
      System.out.println("\nEnter step number " + (i + 1) + " of " + amountOfInstructions);

      String currentStep = terminalUtils.getInput();

      newRecipe.addInstruction(currentStep);

      soundPlayer.playConfirmSound();
    }

    terminalUtils.clearScreen();

    System.out.println("\n\nName: " + newRecipe.getName() + "\n"
        + "Description: " + newRecipe.getDescription() + "\n\n"
        + terminalUtils.ingredientsTable(newRecipe.getIngredients(),
        newRecipe.getApproximations()) + "\n\nInstructions: ");

    newRecipe.getInstructions().forEach(instruction ->
        System.out.println(
            (newRecipe.getInstructions().indexOf(instruction) + 1) + ": " + instruction));

    String addRecipe = uiYesNoOption("\n\nIs this Recipe OK? (Y/n)");

    if (addRecipe.equalsIgnoreCase("y")) {
      cookBook.addRecipe(newRecipe);

      terminalUtils.clearScreen();

      System.out.println("Successfully added recipe to the cookbook."
          + "\nPress ENTER to continue...");

      terminalUtils.getInput();

      soundPlayer.playAddSound();
    }
  }

  private void removeRecipe() {
    terminalUtils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(
          terminalUtils.cookbookTable(cookBook.getRecipes(), foodStorage)
              + "\n\nPlease enter the number (See 'Num') of the recipe you would like to remove.",
          cookBook.getRecipes().size());

      terminalUtils.clearScreen();

      String removeConfirm = uiYesNoOption(
          "Do you really wish to remove the recipe: " + cookBook.getSpecificRecipe(removeIndex)
              .getName() + "? (Y/n)");

      String curRemoved = cookBook.getSpecificRecipe(removeIndex).getName();

      if (removeConfirm.equals("y")) {
        cookBook.removeRecipe(removeIndex);

        terminalUtils.clearScreen();
        System.out.println(
            "Successfully removed recipe: " + curRemoved + ".\n\nPress ENTER to continue...");

        terminalUtils.getInput();

        soundPlayer.playRemoveSound();
      }
    } catch (IllegalArgumentException e) {
      terminalUtils.clearScreen();

      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      terminalUtils.getInput();
      soundPlayer.playRemoveSound();
    }
  }

  private void viewSuggestedRecipes() {
    terminalUtils.clearScreen();
    String includeOutOfDateFood = uiYesNoOption(
        "Would you like to include out of date groceries into consideration for what ingredients "
            + "are currently available? (Y/n)");

    terminalUtils.clearScreen();

    try {
      System.out.println(
          "NOTE: Recipes are only suggested if you have half or more of the ingredients available "
              + "in the food storage.\n\n" + "Suggested Recipes:\n" + terminalUtils.cookbookTable(
              cookBook.recipeSuggestion(foodStorage.getAllGroceryInstances(),
                  includeOutOfDateFood.equals("y")),
              foodStorage) + "\n\nPress ENTER to go back...");

      terminalUtils.getInput();

      soundPlayer.playInputSound();
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to go back...");

      terminalUtils.getInput();

      soundPlayer.playInputSound();
    }
  }

  // methods for entering specific elements

  private int chooseValidListIndex(String message, int lengthOfList)
      throws IllegalArgumentException {
    if (lengthOfList == 0) {
      throw new IllegalArgumentException(
          "Cannot fetch any groceries, grocery classes/ or recipes, since there are none!");
    } else {
      boolean hasEnteredValidIndex = false;
      int index = 0;

      while (!hasEnteredValidIndex) {
        try {
          index = terminalUtils.integerOption(message);

          if (index < 1 || index > lengthOfList) {
            terminalUtils.clearScreen();
            System.out.println(
                "Please enter an integer 1 - " + lengthOfList + ".\n");
          } else {
            hasEnteredValidIndex = true;
            soundPlayer.playInputSound();
          }
        } catch (NumberFormatException e) {
          terminalUtils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
      terminalUtils.clearScreen();

      return index;
    }
  }

  private int[] chooseMultipleValidListIndex(String message, int lengthOfList)
      throws IllegalArgumentException {
    if (lengthOfList == 0) {
      throw new IllegalArgumentException(
          "Can't fetch grocery instances, since there are none stored in "
              + "food storage!");
    }

    ArrayList<Integer> indexes = new ArrayList<>();

    boolean hasQuit = false;

    while (!hasQuit) {
      try {
        terminalUtils.clearScreen();

        System.out.println(message);

        System.out.println("\nCurrent added values: " + indexes);

        System.out.println(
            "Enter another value: (1 - " + lengthOfList + ").");

        int currentIndex = terminalUtils.integerOption("");

        if (currentIndex > 0 && currentIndex <= lengthOfList && !indexes.contains(currentIndex)) {
          indexes.add(currentIndex);
          soundPlayer.playInputSound();
        } else {
          terminalUtils.clearScreen();
          System.out.println("Please enter an integer (1 - "
              + foodStorage.getAllGroceryInstances().size()
              + "), that you haven't already added! \n");
        }
      } catch (Exception e) {
        terminalUtils.clearScreen();
        System.out.println("Please enter an integer (1 - "
            + foodStorage.getAllGroceryInstances().size() + "), that you haven't already added!\n");
      }

      terminalUtils.clearScreen();

      System.out.println(message);

      System.out.println("\nCurrent added values: " + indexes);

      String continueAddingIndexes = uiYesNoOption("\nWould you like to add more values? (Y/n)");

      if (continueAddingIndexes.equals("n")) {
        hasQuit = true;
      }
    }
    return indexes.stream().mapToInt(i -> i).toArray();
  }

  private String uiYesNoOption(String message) {
    boolean hasEnteredYorN = false;
    String yesNoChoice = "";

    System.out.println(message);

    while (!hasEnteredYorN) {
      try {
        yesNoChoice = terminalUtils.yesNoOption();
        hasEnteredYorN = true;
        soundPlayer.playConfirmSound();
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    return yesNoChoice;
  }

  private int choiceWindow(String[] choices, String message) {
    List<String> choicesList = Arrays.asList(choices);

    choicesList.forEach(choice -> {
      int currentIndex = choicesList.indexOf(choice);

      choicesList.set(currentIndex, "[" + (currentIndex + 1) + "] " + choice);
    });

    String choicesString =
        message + " (Please enter an integer 1 - " + choices.length + ")\n\n" + String.join("\n",
            choicesList);

    boolean hasEnteredValidInteger = false;
    int input = 0;

    do {
      System.out.println(choicesString);
      try {
        input = Integer.parseInt(terminalUtils.getInput());
        if (input >= 1 && input <= choicesList.size()) {
          hasEnteredValidInteger = true;
        } else {
          terminalUtils.clearScreen();
          System.out.println("Please enter a valid integer!");
        }
      } catch (NumberFormatException e) {
        terminalUtils.clearScreen();
        System.out.println("Please enter a valid integer!");
      }
    } while (!hasEnteredValidInteger);

    soundPlayer.playInputSound();

    return input;
  }

}
