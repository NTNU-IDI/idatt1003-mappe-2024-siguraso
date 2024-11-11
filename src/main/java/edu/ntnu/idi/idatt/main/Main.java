package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Interface.ChoiceWindow;
import edu.ntnu.idi.idatt.Interface.DialogOptionCreator;
import edu.ntnu.idi.idatt.Interface.TableCreator;

import edu.ntnu.idi.idatt.Grocery.GroceryInstance;

import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Main {

  // methods
  // misc. methods:
  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // add groceryType/groceryInstance

  /**
   * Creates a new instance of GroceryType, and adds it to food storage.
   *
   * @param sc            Scanner used for user input.
   * @param foodStorage   Used to append the new grocery type to food storage.
   * @param dialogCreator Creates the dialog option windows.
   */
  public static void addGroceryType(Scanner sc,
      FoodStorage foodStorage, DialogOptionCreator dialogCreator) {
    clearScreen();

    String TypeName = dialogCreator.validTypeNameOption(sc,
        "Enter the name of the type of grocery you would like to add");

    clearScreen();

    String UnitName = dialogCreator.validMeasurementUnitOption(sc,
        "Enter the measurement unit most used when measuring this type of grocery");

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
   * Adds a GroceryType to a given FoodStorage instance.
   *
   * @param sc              Scanner used for user input.
   * @param foodStorage     Food storage to append the new GroceryInstance and to fetch Available
   *                        GroceryTypes.
   * @param thisGroceryType the grocery type the new grocery instance is based on.
   * @param dialogCreator   Used to create dialog windows.
   */
  public static void addGroceryInstance(Scanner sc, FoodStorage foodStorage,
      GroceryType thisGroceryType, DialogOptionCreator dialogCreator) {
    clearScreen();

    double instanceAmount = dialogCreator.validAmountOption(sc,
        "How many " + thisGroceryType.getMeasurementUnit() + " of " +
            thisGroceryType.getName() + " you would like to add?");

    clearScreen();

    double pricePerUnit = dialogCreator.validPricePerUnitOption(sc,
        "How much does it cost for 1 " + thisGroceryType.getMeasurementUnit() + " of "
            + thisGroceryType.getName());

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


  // menu-methods:
  // main menu
  private static int mainMenu(Scanner sc) {
    ChoiceWindow mainMenu = new ChoiceWindow();
    mainMenu.addChoice("Manage food storage.");
    mainMenu.addChoice("Manage grocery types.");
    mainMenu.addChoice("Exit program.");

    return mainMenu.choiceSequnce("What do you want to do? ", sc);
  }

  // menu to manage the food storage.
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

      int manageFoodStorageChoice = manageFoodStorageMenu.choiceSequnce(
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

            ArrayList<GroceryInstance> searchResults = foodStorage.groceryInstanceSearch(
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
              tableCreator.groceryInstanceTable(foodStorage.groceryInstanceSearch(searchTerm));

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

            double newAmount = dialogCreator.validAmountOption(sc,
                "Enter the updated amount of " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getName()
                    + " (Changing from: " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getAmount() + " " +
                    foodStorage.getSpecificInstance(editInstanceIndex).getMeasurementUnit() + ".)");

            sc.nextLine();

            String newBestBeforeDate = dialogCreator.validBestBeforeDateOption(sc,
                "Enter the updated best before date. (Changing from "
                    + foodStorage.getSpecificInstance(editInstanceIndex).getBestBeforeString()
                    + ")");

            double newPricePerUnit = dialogCreator.validPricePerUnitOption(sc,
                "Enter the updated price per unit for " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getName() +
                    " (Changing from " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getPricePerUnit() +
                    " per " + foodStorage.getSpecificInstance(
                    editInstanceIndex).getMeasurementUnit() + ")");

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
      int choice = manageGroceryTypeMenu.choiceSequnce("Manage grocery types: ", sc);
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
          String newName = dialogCreator.validTypeNameOption(sc,
              "Enter a new name for '" + foodStorage.getSpecificType(typeIndex).getName()
                  + "' (Leave empty if you dont want to change it)");

          String newUnit = dialogCreator.validMeasurementUnitOption(sc,
              "Enter a new unit (Change from '" + foodStorage.getSpecificType(typeIndex)
                  .getMeasurementUnit() + "', leave empty if you dont want to change it). \n");

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

  private static void manageCookbookMenu(Scanner sc, TableCreator tableCreator,
      DialogOptionCreator dialogCreator) {
    ChoiceWindow manageCookbookMenu = new ChoiceWindow();

    manageCookbookMenu.addChoice("Add recipe.");
    manageCookbookMenu.addChoice("Delete recipe.");
    manageCookbookMenu.addChoice("View suggested recipes.");
    manageCookbookMenu.addChoice("Return to main menu.");

    cookbookLoop:
    while (true) {
      clearScreen();

    }

  }


  // main method
  public static void main(String[] args) {
    clearScreen();

    //declares instances of scanner and table creator:
    DialogOptionCreator dialogCreator = new DialogOptionCreator();
    TableCreator tableCreator = new TableCreator();
    Scanner sc = new Scanner(System.in);

    ArrayList<GroceryInstance> allGroceries = new ArrayList<>();
    FoodStorage foodStorage = new FoodStorage(allGroceries);

    while (true) {
      // creates the main menu.
      int mainMenuChoice = mainMenu(sc);

      switch (mainMenuChoice) {
        // sends the user to the 'manage food storage menu'
        case 1:
          manageFoodStorageMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;

        // sends the user to the 'manage grocery types' menu.
        case 2:
          manageGroceryTypeMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;

        // exits the program.
        case 3:
          sc.close();
          System.exit(0);
      }
    }
  }
}
