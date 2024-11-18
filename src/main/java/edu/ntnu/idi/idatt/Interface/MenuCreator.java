package edu.ntnu.idi.idatt.Interface;

import edu.ntnu.idi.idatt.Grocery.Cookbook;
import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Grocery.Recipe;
import edu.ntnu.idi.idatt.Grocery.GroceryInstance;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Class that is used to create the different type of menus that the user can access.
 */
public class MenuCreator {

  // methods
  // misc. methods:

  /**
   * Used to clear the terminal window. Makes for a cleaner user experience.
   */
  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // various methods to print stuff

  /**
   * Used to print out a recipe's name, description, steps and ingredients.
   *
   * @param tableCreator Used to print out a table of the ingredients in the given recipe.
   * @param recipe       The recipe that we are looking to print.
   */
  private static void showRecipe(TableCreator tableCreator, Recipe recipe) {
    clearScreen();

    System.out.println("Name: '" + recipe.getName() + "'\n");

    System.out.println("Description: '" + recipe.getDescription() + "'\n");

    tableCreator.ingredientsTable(recipe.getIngredients(), recipe.getApproximations());

    System.out.println("\nInstructions:");
    recipe.getInstructions().forEach(instruction ->
        System.out.println(
            (recipe.getInstructions().indexOf(instruction) + 1) + ": " + instruction));

    System.out.println("\n");
  }

  // add groceryType/groceryInstance/recipe under-menus

  /**
   * Creates a new instance of GroceryType, and adds it to food storage based on the users input.
   *
   * @param sc            Scanner used for user input.
   * @param foodStorage   Used to append the new grocery type to food storage.
   * @param dialogCreator Creates the dialog option windows.
   */
  private static void addGroceryType(Scanner sc,
      FoodStorage foodStorage, DialogOptionCreator dialogCreator) {
    clearScreen();

    String TypeName = dialogCreator.validCharLimit(sc,
        "Please enter the name of the grocery.", 17);

    clearScreen();

    String UnitName = dialogCreator.validCharLimit(sc,
        "Please enter the measurement unit most often used when measuring '"
            + TypeName + "'.", 9);

    clearScreen();

    // returns either y or n.
    String yesNoOption = dialogCreator.yesNoOption(sc, "Is this OK (Y/N)? "
        + "\nName: " + TypeName + "\nUnit: " + UnitName);

    // if user wants to add the grocery instance, add it to the list. if not, it breaks the switch
    // case and returns the user to the previous menu.
    switch (yesNoOption) {
      case "y":
        foodStorage.addType(new GroceryType(TypeName, UnitName));
        break;
      case "n":
        break;
    }
  }

  /**
   * Adds a GroceryType to a given FoodStorage instance based on the users input.
   *
   * @param sc              Scanner used for user input.
   * @param foodStorage     Food storage to append the new GroceryInstance and to fetch Available
   *                        GroceryTypes.
   * @param thisGroceryType The grocery type the new grocery instance is based on.
   * @param dialogCreator   Used to create dialog windows.
   */
  private static void addGroceryInstance(Scanner sc, FoodStorage foodStorage,
      GroceryType thisGroceryType, DialogOptionCreator dialogCreator) {
    clearScreen();

    double instanceAmount = dialogCreator.validDoubleOption(sc,
        "Enter the amount of " + thisGroceryType.getName() + " (in "
            + thisGroceryType.getMeasurementUnit() + ") you would like to add", 0, 999.9);

    clearScreen();

    double pricePerUnit = dialogCreator.validDoubleOption(sc,
        "Enter the cost per " + thisGroceryType.getMeasurementUnit() + ".",
        0, 99999.9);

    clearScreen();

    sc.nextLine();
    String bestBefore = dialogCreator.validBestBeforeDateOption(sc,
        "Enter the best before date of the grocery");

    clearScreen();

    String yesNoSc = dialogCreator.yesNoOption(sc,
        "Would you like to add this grocery to food storage (Y/N)?"
            + "\n\nName: " + thisGroceryType.getName()
            + "\nAmount: " + instanceAmount + " " +
            thisGroceryType.getMeasurementUnit()
            + "\nPrice per " + thisGroceryType.getMeasurementUnit() + ": " + pricePerUnit
            + "\nBest before date: " + bestBefore);
    switch (yesNoSc) {
      // if yes, add it to the GroceryInstance list in foodStorage and return to the previous menu.
      case "y":
        foodStorage.addInstance(
            new GroceryInstance(thisGroceryType, instanceAmount, pricePerUnit, bestBefore));
        break;
      // if no, return to the previous menu
      case "n":
        break;
    }
  }

  /**
   * Creates a new recipe, and adds it to the cookbook based on the users input.
   *
   * @param sc            Used for user input.
   * @param foodStorage   Used to fetch grocery types.
   * @param cookBook      Used to add to recipe list.
   * @param dialogCreator Used to create dialog windows.
   * @param tableCreator  Used to create relevant tables when needed.
   */
  private static void addRecipe(Scanner sc, FoodStorage foodStorage, Cookbook cookBook,
      DialogOptionCreator dialogCreator, TableCreator tableCreator) {
    ArrayList<GroceryInstance> ingredients = new ArrayList<>();
    ArrayList<String> approximations = new ArrayList<>();
    ArrayList<String> instructions = new ArrayList<>();

    String recipeName = dialogCreator.validCharLimit(sc,
        "What is the name of this recipe?", 45);

    String description = dialogCreator.validCharLimit(sc,
        "Please enter a short description of the recipe", 45);

    int amountOfIngredients = dialogCreator.validIntOption(sc,
        "How many Ingredients are in this recipe?", 0, 999);

    clearScreen();

    for (int i = 0; i < amountOfIngredients; i++) {
      int thisTypeIndex = dialogCreator.validGroceryTypeIndex(sc, foodStorage, tableCreator,
          "(Ingredient " + (i + 1) + ") "
              + "Please select which ingredient you would like you add");

      double thisAmount = dialogCreator.validDoubleOption(sc,
          "How much of '" + foodStorage.getSpecificType(thisTypeIndex).getName() +
              "' (in " + foodStorage.getSpecificType(thisTypeIndex).getMeasurementUnit() +
              ") would you like to add?", 0, 99999.9);

      sc.nextLine();
      String thisApproximation = dialogCreator.validCharLimit(sc,
          "Add an approximaion "
              + "(e.g. 1 tablespoon, 1 cup etc., makes the recipe more readable, "
              + "leave empty if there is no need for one)\n", 20);

      // adds the ingredient using new Grocery Instance. Here the price per unit and best before
      // date go unused, so we just enter 0 and null for them respectively.
      ingredients.add(
          new GroceryInstance(foodStorage.getSpecificType(thisTypeIndex), thisAmount, 0,
              null));

      approximations.add(thisApproximation);
    }

    int amountOfSteps = dialogCreator.validIntOption(sc,
        "How many steps are there in the instructions?", 0, 999);
    sc.nextLine();

    for (int i = 0; i < amountOfSteps; i++) {
      clearScreen();
      System.out.println(
          "Please enter step " + (i + 1) + " of the recipe (try to keep it as short as possible):");

      String thisStep = sc.nextLine();

      instructions.add(thisStep);
    }

    showRecipe(tableCreator,
        new Recipe(recipeName, description, instructions, ingredients, approximations));

    String yesNoSc = dialogCreator.yesNoOption(sc, "Is this OK (Y/N)?");

    // if yes, add recipe to cookbook, if not, return to the previous menu.
    if (yesNoSc.equals("y")) {
      cookBook.addRecipe(
          new Recipe(recipeName, description, instructions, ingredients, approximations));
    }
  }

  // menu-methods:
  // main menu, and sub-menus.

  /**
   * The main menu that leads to the other sub-menus.
   *
   * @param sc            Scanner used for user-input.
   * @param foodStorage   The food storage that the program will be based around.
   * @param tableCreator  Used to create different types of tables.
   * @param dialogCreator Used to create various dialog messages so that the user inputs valid
   *                      values.
   * @param cookBook      Cookbook used to display and manage recipes
   */
  public void mainMenu(Scanner sc, FoodStorage foodStorage, TableCreator tableCreator,
      DialogOptionCreator dialogCreator, Cookbook cookBook) {

    clearScreen();

    ChoiceWindow mainMenu = new ChoiceWindow();
    mainMenu.addChoice("Manage food storage.");
    mainMenu.addChoice("Manage grocery types.");
    mainMenu.addChoice("Manage cookbook.");
    mainMenu.addChoice("Exit program.");

    boolean isFinished = false;

    while (!isFinished) {
      // creates the main menu.
      int mainMenuChoice = mainMenu.choiceSequence("What do you want to do? ", sc);

      switch (mainMenuChoice) {
        // sends the user to the 'manage food storage menu'
        case 1:
          manageFoodStorageMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;

        // sends the user to the 'manage grocery types' menu.
        case 2:
          manageGroceryTypeMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;

        // sends the user to the 'manage cookbook' menu.
        case 3:
          manageCookbookMenu(sc, foodStorage, tableCreator, dialogCreator, cookBook);
          break;

        // exits the program.
        case 4:
          isFinished = true;
      }
    }
  }

  // menu to manage the food storage.

  /**
   * Sub-menu that allows the user to manage the food storage. In this menu the user can either
   * Display the out of date food, search for GroceryInstances in the foodStorage, add a
   * GroceryInstance to the food storage, remove a GroceryInstance from the food storage, edit a
   * specific GroceryInstance or check the value of multiple different groceries.
   *
   * @param sc            Scanner used for user input.
   * @param foodStorage   Instance of FoodStorage which contains the basis for which items are in
   *                      the food storage.
   * @param tableCreator  Used to create tables.
   * @param dialogCreator Used to give the user various dialog options, and makes sure the user
   *                      inputs a valid input.
   */
  private static void manageFoodStorageMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, DialogOptionCreator dialogCreator) {
    // sets a decimal format similar to the ones used in the tables.
    DecimalFormat df = new DecimalFormat("###,###.##");

    ChoiceWindow manageFoodStorageMenu = new ChoiceWindow();

    manageFoodStorageMenu.addChoice("Display out-of-date food only.");
    manageFoodStorageMenu.addChoice("Search in food storage.");
    manageFoodStorageMenu.addChoice("Add grocery to storage.");
    manageFoodStorageMenu.addChoice("Remove grocery from storage.");
    manageFoodStorageMenu.addChoice(
        "Edit specific grocery (e.g. edit amount, best before date and price per unit)");
    manageFoodStorageMenu.addChoice("Check the value of multiple groceries.");
    manageFoodStorageMenu.addChoice("Return to main menu.");

    foodStorageLoop:
    while (true) {
      clearScreen();
      tableCreator.groceryInstanceTable(foodStorage.getAllGroceryInstances());

      System.out.println("\nTotal Value: " + df.format(foodStorage.getTotalValue()) + "\n\n");

      int manageFoodStorageChoice = manageFoodStorageMenu.choiceSequence(
          "Manage food storage: ", sc);

      sc.nextLine();

      switch (manageFoodStorageChoice) {

        // display out of date food
        case 1 -> {
          tableCreator.groceryInstanceTable(foodStorage.getOutOfDateInstances());

          System.out.println("\nCombined value of all out of date groceries: "
              + df.format(foodStorage.getOutOfDateValue()));

          System.out.println("\nPress ENTER to continue...");

          sc.nextLine();

        }

        // Search food storage
        case 2 -> {
          searchLoop:
          while (true) {
            System.out.println("Enter your search term:");

            String searchTerm = sc.nextLine();

            List<GroceryInstance> searchResults = foodStorage.groceryInstanceSearch(
                searchTerm);

            if (searchResults.isEmpty()) {
              clearScreen();
              // the search failed because there were no matching names, how do you want to
              // continue?
              System.out.println("The search term '" + searchTerm + "' gave no matching results.");

              String yesNoSc = dialogCreator.yesNoOption(sc,
                  "Would you like to search again? (Y/N)");

              switch (yesNoSc) {
                case "y":
                  // if yes restart the search loop, and search again
                  break;
                case "n":
                  // if no break the search loop, and return to the previous menu.
                  break searchLoop;
              }
            } else {
              // if the search arraylist isn't empty, it prints out the search items in a table.
              clearScreen();
              System.out.println("Search results:");
              tableCreator.groceryInstanceTable(
                  new ArrayList<>(foodStorage.groceryInstanceSearch(searchTerm)));

              System.out.println("Press ENTER to continue.");
              sc.nextLine();
              break;
            }
          }
        }

        // add instance
        case 3 -> {
          tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());

          String yesNoSc = dialogCreator.yesNoOption(sc,
              "Do you want to add a grocery based on an "
                  + "already existing Grocery type (Y/N)? "
                  + "(see the above list)");

          // if yes, create a new grocery instance based on an already added grocery type
          switch (yesNoSc) {
            case "y" -> {
              try {
                clearScreen();
                int groceryTypeIndex = dialogCreator.validGroceryTypeIndex(sc, foodStorage,
                    tableCreator,
                    "Chose one grocery based on its number (see (Num) of the above table)");

                addGroceryInstance(sc, foodStorage, foodStorage.getSpecificType(groceryTypeIndex),
                    dialogCreator);
              } catch (Exception e) {
                System.out.println(e.getMessage() + "(Press ENTER to continue)");
                sc.nextLine();
              }
            }
            case "n" -> {
              String yesNoScInner = dialogCreator.yesNoOption(sc,
                  "Do you want to create a new Grocery Instance? (Y/N)");

              switch (yesNoScInner) {
                // if yes make a new grocerytype and make a new grocery instance based on the new
                // grocery type, and return the user to the previous menu.
                case "y" -> {
                  clearScreen();
                  addGroceryType(sc, foodStorage, dialogCreator);

                  clearScreen();

                  // gets the newest grocery instance and adds an instance based on it.
                  addGroceryInstance(sc, foodStorage,
                      foodStorage.getSpecificType(foodStorage.getAllGroceryTypes().size()),
                      dialogCreator);
                }
                // if no, return to the previous menu.
                case "n" -> {
                }
              }
            }
          }
        }

        // remove instance
        case 4 -> {
          String yesNoSc = dialogCreator.yesNoOption(sc, "Do you really wish to remove"
              + " a grocery from the food storage? (Y/N)");
          int instanceRemoveIndex;

          switch (yesNoSc) {
            case "y" -> {
              // if the user wants to continue, remove a grocery instance of the users choice.
              try {
                instanceRemoveIndex = dialogCreator.validGroceryInstanceIndex(sc, foodStorage,
                    tableCreator,
                    "Which grocery do you wish to remove from food storage? "
                        + "(see 'num'-section of above table)");

                String curRemoved = foodStorage.getSpecificType(
                    instanceRemoveIndex).getName();

                foodStorage.removeInstance(instanceRemoveIndex);

                System.out.println(
                    "Successfully removed an instance of" + curRemoved
                        + ". (Press ENTER to continue)");

                sc.nextLine();

              } catch (Exception e) {
                // if there are no grocery instances in the main arraylist, inform the user and
                // return to the previous menu.
                clearScreen();
                System.out.println(e.getMessage() + "\nPress Enter to continue.");

                sc.nextLine();
              }
            }

            case "n" -> {
              // if no, return the user to the previous menu.
            }
          }
        }

        // edit grocery instance
        case 5 -> {
          int editInstanceIndex;

          try {
            editInstanceIndex = dialogCreator.validGroceryInstanceIndex(sc, foodStorage,
                tableCreator, "Which grocery would you like to edit?");

            double newAmount = dialogCreator.validDoubleOption(sc, "Enter the updated amount of " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getName()
                    + " (Changing from: " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getAmount() + " " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getMeasurementUnit() + ".)"
                , 0, 999.9);

            sc.nextLine();

            String newBestBeforeDate = dialogCreator.validBestBeforeDateOption(sc,
                "Enter the updated best before date. (Changing from "
                    + foodStorage.getSpecificInstance(editInstanceIndex).getBestBeforeString()
                    + ")");

            double newPricePerUnit = dialogCreator.validDoubleOption(sc,
                "Enter the updated price per unit for " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getName() +
                    " (Changing from " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getPricePerUnit() +
                    " per " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getMeasurementUnit() + ")", 0, 99999.9);

            //sets the new values.
            foodStorage.getSpecificInstance(editInstanceIndex).setAmount(newAmount);
            foodStorage.getSpecificInstance(editInstanceIndex).setBestBeforeDate(newBestBeforeDate);
            foodStorage.getSpecificInstance(editInstanceIndex).setPricePerUnit(newPricePerUnit);

          } catch (Exception e) {
            // if the food storage is empty, give the user instructions.
            clearScreen();
            System.out.println(e.getMessage() + "\nPress Enter to continue.");
            sc.nextLine();

          }
        }

        //find the combined value of a specific index of foods.
        case 6 -> {
          try {
            ArrayList<Integer> indexes = dialogCreator.multipleValidGroceryInstanceIndex(sc,
                foodStorage, tableCreator);

            ArrayList<GroceryInstance> combinedValueInstances = new ArrayList<>();

            for (Integer index : indexes) {
              combinedValueInstances.add(foodStorage.getSpecificInstance(index));
            }

            double sum = 0;

            for (GroceryInstance groceryInstance : combinedValueInstances) {
              sum += groceryInstance.getPrice();
            }

            tableCreator.groceryInstanceTable(combinedValueInstances);

            System.out.println("Combined value: " + df.format(sum));

            System.out.println("\nPress ENTER to continue.");
            sc.nextLine();

          } catch (Exception e) {
            System.out.println(e.getMessage() + "\nPress Enter to continue.");
            sc.nextLine();
          }

        }

        // return to main menu
        case 7 -> {
          break foodStorageLoop;
        }
      }
    }
  }

  // menu to manage grocery type

  /**
   * Sub-menu that allows the user to manage the different grocery types in the food storage. In
   * this menu the user can either add an instance of GroceryType, edit an instance of GroceryType
   * or delete an instance of GroceryType.
   *
   * @param sc            Scanner used for user input.
   * @param foodStorage   FoodStorage that contains the available GroceryTypes.
   * @param tableCreator  Used to create different types of tables.
   * @param dialogCreator Used to give the user various dialog options, and makes sure the user
   *                      inputs a valid input.
   */
  private static void manageGroceryTypeMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, DialogOptionCreator dialogCreator) {
    ChoiceWindow manageGroceryTypeMenu = new ChoiceWindow();

    manageGroceryTypeMenu.addChoice("Add grocery type.");
    manageGroceryTypeMenu.addChoice("Edit grocery type (change name or measurement unit).");
    manageGroceryTypeMenu.addChoice("Delete grocery type.");
    manageGroceryTypeMenu.addChoice("Return to main menu.");

    groceryTypeLoop:
    while (true) {
      clearScreen();
      tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());
      int choice = manageGroceryTypeMenu.choiceSequence("Manage grocery types: ", sc);
      switch (choice) {
        // add type
        case 1 -> {
          sc.nextLine();
          addGroceryType(sc, foodStorage, dialogCreator);
        }

        // edit type
        case 2 -> {
          clearScreen();

          int typeIndex = dialogCreator.validGroceryTypeIndex(sc, foodStorage, tableCreator,
              "Which grocery do you wish to edit?");

          sc.nextLine();
          String newName = dialogCreator.validCharLimit(sc,
              "Enter a new name for '" + foodStorage.getSpecificType(typeIndex).getName()
                  + "' (Leave empty if you dont want to change it)", 17);

          String newUnit = dialogCreator.validCharLimit(sc,
              "Enter a new unit (Change from '" + foodStorage.getSpecificType(typeIndex)
                  .getMeasurementUnit() + "', leave empty if you dont want to change it). \n", 9);

          if (!newName.isEmpty()) {
            foodStorage.getSpecificType(typeIndex).setName(newName);
          }

          if (!newUnit.isEmpty()) {
            foodStorage.getSpecificType(typeIndex).setMeasurementUnit(newUnit);
          }

        }

        // delete type
        case 3 -> {
          String yesNoSc = dialogCreator.yesNoOption(sc, "Do you really wish to remove"
              + " a grocery type from the food storage? (Y/N)");

          switch (yesNoSc) {
            case "y" -> {
              // if the user wants to continue, remove a grocery instance of the users choice.
              try {
                int typeRemoveIndex = dialogCreator.validGroceryTypeIndex(sc, foodStorage,
                    tableCreator,
                    "Which grocery type do you wish to remove from food storage? "
                        + "(see 'num'-section of above table)");

                String curRemoved = foodStorage.getSpecificType(
                    typeRemoveIndex).getName();

                foodStorage.removeType(typeRemoveIndex);

                clearScreen();

                System.out.println(
                    "Successfully removed " + curRemoved + ". (Press ENTER to continue...)");

                sc.nextLine();
                sc.nextLine();
              } catch (Exception e) {
                clearScreen();
                System.out.println(e.getMessage() + "\nPress Enter to continue.");
                sc.nextLine();
              }
            }
            case "n" -> {
              // if no, return the user to the previous menu.
            }
          }
        }

        // return to main menu
        case 4 -> {
          break groceryTypeLoop;
        }
      }


    }
  }

  // menu to manage the cookbook (recipes and dat)

  /**
   * Sub-menu that allows the user to manage the different recipes in the cookbook. In this menu the
   * user can add a recipe, delete a recipe from the cookbook and view suggested recipes based on
   * what GroceryInstances are in the food storage..
   *
   * @param sc            Scanner used for user input.
   * @param foodStorage   FoodStorage used to check what types of food the user has stored.
   * @param tableCreator  used to create different types of tables.
   * @param dialogCreator used to create various dialog windows so that the user inputs valid
   *                      values.
   */
  private static void manageCookbookMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, DialogOptionCreator dialogCreator, Cookbook cookBook) {
    ChoiceWindow manageCookbookMenu = new ChoiceWindow();

    manageCookbookMenu.addChoice("View recipe.");
    manageCookbookMenu.addChoice(
        "Add recipe (!Make sure to add the relevant grocery types in the grocery type menu!).");
    manageCookbookMenu.addChoice("Delete recipe.");
    manageCookbookMenu.addChoice("View suggested recipes.");
    manageCookbookMenu.addChoice("Return to main menu.");

    cookbookLoop:
    while (true) {
      clearScreen();
      tableCreator.recipeTable(cookBook.getRecipes(), foodStorage);

      int cookbookMenuChoice = manageCookbookMenu.choiceSequence(
          "Manage cookbook recipes: ", sc);

      switch (cookbookMenuChoice) {
        // view recipe
        case 1 -> {
          sc.nextLine();
          int recipeIndex;
          try {
            recipeIndex = dialogCreator.validRecipeIndex(sc, tableCreator, cookBook, foodStorage,
                "Which recipe would you like to view?");

            showRecipe(tableCreator, cookBook.getSpecificRecipe(recipeIndex));

            System.out.println("Press ENTER to continue...");
            sc.nextLine();
            sc.nextLine();

          } catch (Exception e) {
            System.out.println(e.getMessage() + "\nPress Enter to continue.");
            sc.nextLine();
          }
        }

        // add recipe
        case 2 -> {
          sc.nextLine();
          addRecipe(sc, foodStorage, cookBook, dialogCreator, tableCreator);
        }

        // Delete recipe
        case 3 -> {
          sc.nextLine();
          String yesNoSc = dialogCreator.yesNoOption(sc,
              "Do you really wish to remove a recipe from the cookbook? (Y/N)");

          if (yesNoSc.equals("y")) {
            try {
              int removeIndex = dialogCreator.validRecipeIndex(sc, tableCreator, cookBook,
                  foodStorage, "Which recipe would you like to remove?");

              cookBook.removeRecipe(removeIndex);
            } catch (Exception e) {
              clearScreen();
              System.out.println(e.getMessage() + "\nPress Enter to continue.");
              sc.nextLine();
            }
          }
        }

        // view recipe suggestions.
        case 4 -> {
          sc.nextLine();

          String yesNoSc = dialogCreator.yesNoOption(sc,
              "Would you like to include out-of-date food to the consideration on which "
                  + "recipes to recommend? (Y/N)");

          boolean includeOutOfDate = yesNoSc.equals("y");

          ArrayList<Recipe> suggestions = cookBook.recipeSuggestion(foodStorage, includeOutOfDate);

          System.out.println("Here are your reccomendations: \n");

          try {
            tableCreator.recipeTable(suggestions, foodStorage);

            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();

          } catch (Exception e) {
            System.out.println(e.getMessage() + "\nPress Enter to continue.");
          }
        }

        // return to the main menu.
        case 5 -> {
          break cookbookLoop;
        }
      }
    }
  }
}
