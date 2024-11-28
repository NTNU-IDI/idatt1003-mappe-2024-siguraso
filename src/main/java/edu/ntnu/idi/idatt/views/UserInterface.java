package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.GroceryInstance;
import edu.ntnu.idi.idatt.models.GroceryType;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that creates the menus of the program, and handles the user input for what the user wants
 * to do
 */
public class UserInterface {

  private final FoodStorage foodStorage = new FoodStorage();
  private final Cookbook cookBook = new Cookbook();
  private final Utils utils = new Utils();

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

    foodStorage.sortGroceryTypes();

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
    // When the main menu tells the program to quit, close the scanner from the Utils, and close the
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
    return choiceWindow(
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
    return choiceWindow(
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
    return choiceWindow(new String[]{"Add a grocery class.", "Remove a grocery class.",
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
    return choiceWindow(new String[]{"View recipe",
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
          foodStorage.sortGroceryInstances();
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
          editGroceryInstance();
          foodStorage.sortGroceryInstances();
        }

        // find cumulative value of many groceries.
        case 6 -> valueOfMultipleGroceries();

        // remove amount from specific grocery
        case 7 -> removeAmountFromSpecificGrocery();

        //return to main menu
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
        // view recipe
        case 1 -> {
          utils.clearScreen();
          try {
            int viewIndex = chooseValidListIndex(
                utils.cookbookTable(cookBook.getRecipes(), foodStorage) +
                    "\nEnter the number of the recipe you would like to view (See Num on the above table).",
                cookBook.getRecipes().size());

            viewRecipe(viewIndex);
          } catch (Exception e) {
            utils.clearScreen();
            System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");

            utils.getInput();
          }
        }

        // create recipe
        case 2 -> createRecipe();

        // remove recipe
        case 3 -> {
          utils.clearScreen();

          String removeConfirmation = uiYesNoOption(
              "Are you sure you wish to remove a recipe from the cookbook? (Y/n)");

          if (removeConfirmation.equals("y")) {
            removeRecipe();
          }
        }

        // view suggested recipes
        case 4 -> viewSuggestedRecipes();

        // return to main menu.
        case 5 -> returnToMainMenu = true;
      }
    } while (!returnToMainMenu);
  }

  // methods for adding, removing and editing the different types of objects to the
  // food storage/cookbook.

  // grocery class menu

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

      String curRemoved = "name" + foodStorage.getAllGroceryTypes().get(removeIndex).getName();

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

  //grocerey instance menu

  /**
   * This method takes a series of inputs that lets the user define a new instance of
   * {@link GroceryInstance} to add to the {@link FoodStorage}
   */
  private void addGroceryInstance() {
    try {
      int typeIndex = 0;

      boolean hasEnteredValidAmount = false;
      boolean hasEnteredValidPrice = false;
      boolean hasEnteredValidBestBefore = false;

      // this boolean is only updated if the user decides to go back to the main menu.
      boolean createNewInstance = true;

      String useOldType = uiYesNoOption(utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
          + "\nWould you like to add a grocery based on an existing grocery class? (Y/n)");

      if (useOldType.equals("n")) {
        utils.clearScreen();

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
        utils.clearScreen();
        typeIndex = chooseValidListIndex(utils.groceryTypeTable(foodStorage.getAllGroceryTypes())
                + "\nPlease enter the number of the grocery class you wish to select (See 'Num' in the above table)",
            foodStorage.getAllGroceryTypes().size());

        foodStorage.addInstance(new GroceryInstance(foodStorage.getSpecificType(typeIndex), 0.1,
            0.1, null));
      }

      utils.clearScreen();

      while (!hasEnteredValidAmount) {
        try {
          double amount = utils.doubleOption("Please enter the amount of '" +
              foodStorage.getSpecificType(typeIndex).getMeasurementUnit() + "' of '" +
              foodStorage.getSpecificType(typeIndex).getName() +
              "' that you would like to add to the food storage. (0.0 - 999.9)");

          foodStorage.getAllGroceryInstances().getLast().setAmount(amount);

          hasEnteredValidAmount = true;
        } catch (Exception e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }

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

      while (!hasEnteredValidBestBefore) {
        System.out.println("Please enter the best before date of the grocery (DD.MM.YYYY)");

        String bestBefore = utils.getInput();

        try {
          foodStorage.getAllGroceryInstances().getLast().setBestBeforeDate(bestBefore);
          hasEnteredValidBestBefore = true;

        } catch (IllegalArgumentException e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n\n");
        }
      }
      utils.clearScreen();

      // if user didnt want to create a new type, ignore the code below.
      if (createNewInstance) {

        String isInstanceOK = uiYesNoOption(
            "Name: " + foodStorage.getSpecificType(typeIndex).getName() +
                "\nMeasurement Unit: " + foodStorage.getSpecificType(typeIndex).getMeasurementUnit()
                +
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
      }
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
          utils.foodStorageTable(foodStorage.getAllGroceryInstances())
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
   * then displays a {@link Utils} table that displays the search results.
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

  /**
   * Method that lets the user edit a {@link GroceryInstance} by showing a user-friendly ui.
   */
  private void editGroceryInstance() {
    try {
      int editIndex = chooseValidListIndex(
          utils.foodStorageTable(foodStorage.getAllGroceryInstances())
              + "\nPlease enter the number of the grocery you would like to edit. "
              + "(See Num on the above table).", foodStorage.getAllGroceryInstances().size());

      boolean hasEnteredValidAmount = false;

      utils.clearScreen();

      while (!hasEnteredValidAmount) {
        try {
          double amount = utils.doubleOption(
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

        } catch (Exception e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      utils.clearScreen();
      boolean hasEnteredValidPrice = false;

      while (!hasEnteredValidPrice) {
        try {
          double pricePerUnit = utils.doubleOption("Please enter the price per '" + foodStorage
              .getSpecificInstance(editIndex).getMeasurementUnit() +
              "' you would like to change to. (Changing from " + foodStorage.getSpecificInstance(
              editIndex).getPricePerUnit() + ".) (enter a decimal number 0.0 - 99999.9.)"
              + "\n(Enter '0' if you dont want to change it.)");

          // Since the set method for the grocery instance class doesn't allow for an amount to be 0
          // we make the user input 0 if they don't want to change the value.
          if (pricePerUnit != 0) {
            foodStorage.getSpecificInstance(editIndex).setPricePerUnit(pricePerUnit);
          }
          hasEnteredValidPrice = true;

        } catch (Exception e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      utils.clearScreen();
      boolean hasEnteredValidBestBefore = false;

      while (!hasEnteredValidBestBefore) {
        System.out.println(
            "Please the best before date you would like to change to (DD.MM.YYYY). (Changing from "
                + foodStorage.getSpecificInstance(editIndex).getBestBeforeString() + ".)"
                + "\n(Leave empty is you don't want to change it.)");

        String bestBefore = utils.getInput();

        try {
          if (!bestBefore.isEmpty()) {
            foodStorage.getSpecificInstance(editIndex).setBestBeforeDate(bestBefore);
          }
          hasEnteredValidBestBefore = true;
        } catch (IllegalArgumentException e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
      utils.clearScreen();

    } catch (Exception e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
    }
  }

  /**
   * Method that lets the user pick numerous {@link GroceryInstance} and find the combined price of
   * said instances.
   */
  private void valueOfMultipleGroceries() {
    try {
      int[] indexes = chooseMultipleValidListIndex(
          utils.foodStorageTable(foodStorage.getAllGroceryInstances())
              + "\n(See Num on the above table)", foodStorage.getAllGroceryInstances().size());

      ArrayList<GroceryInstance> combinedValueInstances = new ArrayList<>();

      Arrays.stream(indexes)
          .forEach(index -> combinedValueInstances.add(foodStorage.getSpecificInstance(index)));

      utils.clearScreen();

      System.out.println(
          utils.foodStorageTable(combinedValueInstances) + "\nCumulative value: "
              + foodStorage.getSpecificValue(indexes) + "\n\nPress ENTER to continue...");

      utils.getInput();

    } catch (Exception e) {
      utils.clearScreen();
      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
    }
  }

  /**
   * Method that lets the user pick a {@link GroceryInstance} to remove a certain amount from.
   */
  private void removeAmountFromSpecificGrocery() {
    int removeAmountIndex = chooseValidListIndex(
        utils.foodStorageTable(foodStorage.getAllGroceryInstances())
            + "\n\nFrom which grocery do you wish to remove?",
        foodStorage.getAllGroceryInstances().size());

    double amountToRemove = doubleOptionParameter("How many '" + foodStorage.getSpecificInstance(
            removeAmountIndex).getMeasurementUnit() + "' of '" + foodStorage.getSpecificInstance(
            removeAmountIndex).getName() + "' do you wish to remove?", 0.0,
        foodStorage.getSpecificInstance(removeAmountIndex).getAmount());

    double oldAmount = foodStorage.getSpecificInstance(removeAmountIndex).getAmount();
    double newAmount = oldAmount - amountToRemove;

    utils.clearScreen();
    String yesNoChoice = uiYesNoOption(
        "From old amount: " + oldAmount + "\nTo new amount: " + newAmount
            + "\n\nIs this OK? (Y/n)");

    if (yesNoChoice.equalsIgnoreCase("y")) {
      foodStorage.getSpecificInstance(removeAmountIndex).setAmount(newAmount);
    }
  }

  // cookbook menu

  /**
   * Method that lets the user view a recipe of their choice.
   *
   * @param viewIndex the index of the recipe that the user would like to view.
   */
  private void viewRecipe(int viewIndex) {
    Recipe thisRecipe = cookBook.getSpecificRecipe(viewIndex);

    // create a deep copy so that it wont change the properties of the arraylist in the object
    ArrayList<String> instructions = new ArrayList<>(thisRecipe.getInstructions());

    instructions.forEach(instruction -> {
      int thisIndex = instructions.indexOf(instruction);

      instructions.set(thisIndex, (thisIndex + 1) + ": " + instruction);
    });

    System.out.println("Name: " + thisRecipe.getName() + "\nDescription: "
        + thisRecipe.getDescription() + "\n" + utils.ingredientsTable(thisRecipe.getIngredients(),
        thisRecipe.getApproximations()) + "\n\nInstructions:\n" + String.join("\n", instructions));

    System.out.println("\n\nPress ENTER to to go back...");

    utils.getInput();
  }

  /**
   * Method that lets the user create an instance of {@link Recipe} by defining its characteristics
   */
  private void createRecipe() {
    // placeholder recipe to make it set-able, so that it can throw the errors at the right time.
    Recipe newRecipe = new Recipe("", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    boolean hasEnteredValidName = false;

    while (!hasEnteredValidName) {
      utils.clearScreen();

      System.out.println("What is the name of the recipe? (max 45 characters)");
      String name = utils.getInput();
      try {
        newRecipe.setName(name);

        hasEnteredValidName = true;
      } catch (IllegalArgumentException e) {
        utils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    // no need to try the description, since it has no character limit.
    utils.clearScreen();
    System.out.println("Enter a short description for this recipe.");
    String description = utils.getInput();
    newRecipe.setDescription(description);

    boolean hasEnteredValidInteger = false;

    int amountOfIngredients = 0;

    while (!hasEnteredValidInteger) {
      utils.clearScreen();

      try {
        amountOfIngredients = utils.integerOption("How many ingredients are there in this recipe?");

        hasEnteredValidInteger = true;
      } catch (Exception e) {
        utils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    utils.clearScreen();

    for (int i = 0; i < amountOfIngredients; i++) {
      int typeIndex = 0;
      try {
        typeIndex = chooseValidListIndex("Ingredient " + (i + 1) + "\n\n" +
                utils.groceryTypeTable(foodStorage.getAllGroceryTypes()) +
                "\nPlease choose which ingredient to add. (Enter the number 'Num' of the ingredient you would like to add.)",
            foodStorage.getAllGroceryTypes().size());
        utils.clearScreen();
      } catch (IllegalArgumentException e) {
        utils.clearScreen();
        System.out.println(e.getMessage() + "\n");

      }

      boolean hasEnteredValidAmount = false;

      // placeholder ingredient to add to the recipe so that it throws the exceptions in the right
      // places.
      GroceryInstance newIngredient = new GroceryInstance(foodStorage.getSpecificType(typeIndex),
          0.1, 0.1, null);

      while (!hasEnteredValidAmount) {
        try {
          double ingredientAmount = utils.doubleOption(
              "How many '" + newIngredient.getMeasurementUnit() + "' of '" + newIngredient.getName()
                  + "' is used in this recipe?");

          newIngredient.setAmount(ingredientAmount);

          utils.clearScreen();
          hasEnteredValidAmount = true;
        } catch (Exception e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }

      newRecipe.addIngredient(newIngredient);

      utils.clearScreen();

      boolean hasEnteredValidApproximation = false;

      while (!hasEnteredValidApproximation) {

        System.out.println("What is an approximation for " + newIngredient.getAmount() + " "
            + newIngredient.getMeasurementUnit() + " of " + newIngredient.getName()
            + "? (e.g. 1 tablespoon, 1 teaspoon, cup, etc.)");

        String approximation = utils.getInput();

        utils.clearScreen();

        try {
          newRecipe.addApproximation(approximation);
          hasEnteredValidApproximation = true;
        } catch (IllegalArgumentException e) {
          utils.clearScreen();
          System.out.println(e.getMessage() + "\n");
        }
      }
    }

    hasEnteredValidInteger = false;

    int amountOfInstructions = 0;

    utils.clearScreen();

    while (!hasEnteredValidInteger) {
      try {
        amountOfInstructions = utils.integerOption(
            "How many steps are there in the instructions to this recipe?");

        hasEnteredValidInteger = true;

        utils.clearScreen();
      } catch (Exception e) {
        utils.clearScreen();

        System.out.println(e.getMessage() + "\n");
      }
    }

    utils.clearScreen();

    for (int i = 0; i < amountOfInstructions; i++) {
      System.out.println("\nEnter step number " + (i + 1) + " of " + amountOfInstructions);

      String currentStep = utils.getInput();

      newRecipe.addInstruction(currentStep);
    }

    utils.clearScreen();

    System.out.println("\n\nName: " + newRecipe.getName() + "\n" +
        "Description: " + newRecipe.getDescription() + "\n\n" + utils.ingredientsTable(
        newRecipe.getIngredients(), newRecipe.getApproximations()) + "\n\nInstructions: ");

    newRecipe.getInstructions().forEach(instruction ->
        System.out.println(
            (newRecipe.getInstructions().indexOf(instruction) + 1) + ": " + instruction));

    String addRecipe = uiYesNoOption("\n\nIs this Recipe OK? (Y/n)");

    if (addRecipe.equalsIgnoreCase("y")) {
      cookBook.addRecipe(newRecipe);
    }
  }

  /**
   * Method that lets the user remove one recipe of their choice from the cookbook.
   */
  private void removeRecipe() {
    utils.clearScreen();

    try {
      int removeIndex = chooseValidListIndex(utils.cookbookTable(cookBook.getRecipes(), foodStorage)
              + "\n\nPlease enter the number (See 'Num') of the recipe you would like to remove.",
          cookBook.getRecipes().size());

      cookBook.removeRecipe(removeIndex);
    } catch (IllegalArgumentException e) {
      utils.clearScreen();

      System.out.println(e.getMessage() + "\n\nPress ENTER to continue...");
      utils.getInput();
    }


  }

  /**
   * Method that gives a recipe suggestion if you have half or more of a recipe's ingredients
   * available in the food storage.
   */
  private void viewSuggestedRecipes() {
    utils.clearScreen();
    String includeOutOfDateFood = uiYesNoOption(
        "Would you like to include out of date groceries into consideration for what ingredients are currently available? (Y/n)");

    utils.clearScreen();

    try {
      System.out.println(
          "NOTE: Recipes are only suggested if you have half or more of the ingredients available in the food storage.\n\n"
              + "Suggested Recipes:\n" +
              utils.cookbookTable(
                  cookBook.recipeSuggestion(foodStorage.getAllGroceryInstances(),
                      includeOutOfDateFood.equals("y")),
                  foodStorage) + "\n\nPress ENTER to go back...");

      utils.getInput();
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage() + "\n\nPress ENTER to go back...");

      utils.getInput();
    }
  }

  // methods for entering specific elements

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
          "Cannot fetch any groceries, grocery classes/ or recipes, since there are none!");
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
            " (Please enter a decimal number " + start + " - " + end + ")");

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
   * This method is used to create a dialog menu that lets the user choose multiple different
   * indexes from a given list.
   *
   * @param message      the dialog message that will display over the user input.
   * @param lengthOfList the length of the given list.
   * @return an array of integers containing all indexes the user wants to access.
   * @throws IllegalArgumentException if the given list is empty.
   */
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
        utils.clearScreen();

        System.out.println(message);

        System.out.println("\nCurrent added values: " + indexes);

        System.out.println(
            "Enter another value: (1 - " + lengthOfList + ").");

        int currentIndex = utils.integerOption("");

        if (currentIndex > 0 && currentIndex <= lengthOfList && !indexes.contains(currentIndex)) {
          indexes.add(currentIndex);
        } else {
          utils.clearScreen();
          System.out.println("Please enter an integer (1 - " +
              foodStorage.getAllGroceryInstances().size()
              + "), that you haven't already added! \n");
        }
      } catch (Exception e) {
        utils.clearScreen();
        System.out.println("Please enter an integer (1 - " +
            foodStorage.getAllGroceryInstances().size() + "), that you haven't already added!\n");
      }

      utils.clearScreen();

      System.out.println(message);

      System.out.println("\nCurrent added values: " + indexes);

      String continueAddingIndexes = uiYesNoOption("\nWould you like to add more values? (Y/n)");

      if (continueAddingIndexes.equals("n")) {
        hasQuit = true;
      }
    }
    return indexes.stream().mapToInt(i -> i).toArray();
  }

  /**
   * This method is a UI method that uses the {@link Utils} method yesNoOption, and makes it more
   * user friendly by, for instance, catching the errors that method throws.
   *
   * @param message dialog message given as a {@link String} that displays above the user input.
   * @return a string containing either "y" or "n" (lowercase) that indicates yes or no from the
   * user.
   */
  private String uiYesNoOption(String message) {
    boolean hasEnteredYOrN = false;
    String yesNoChoice = "";

    System.out.println(message);

    while (!hasEnteredYOrN) {
      try {
        yesNoChoice = utils.yesNoOption();
        hasEnteredYOrN = true;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    return yesNoChoice;
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
        input = Integer.parseInt(utils.getInput());
        if (input >= 1 && input <= choicesList.size()) {
          hasEnteredValidInteger = true;
        } else {
          utils.clearScreen();
          System.out.println("Please enter a valid integer!");
        }
      } catch (NumberFormatException e) {
        utils.clearScreen();
        System.out.println("Please enter a valid integer!");
      }
    } while (!hasEnteredValidInteger);

    return input;
  }

}
