package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Interface.ChoiceWindow;
import edu.ntnu.idi.idatt.Interface.DialogOptionCreator;
import edu.ntnu.idi.idatt.Interface.TableCreator;

import edu.ntnu.idi.idatt.Grocery.GroceryInstance;

import java.util.Scanner;
import java.util.ArrayList;

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

    double instanceAmount = dialogCreator.validAmountOption(sc, "Enter the amount of "
        + thisGroceryType.getName() + " you would like to add");

    clearScreen();

    double pricePerUnit = dialogCreator.validPricePerUnitOption(sc,
        "How much does it cost for 1 " + thisGroceryType.getMeasurementUnit() + " of "
            + thisGroceryType.getName());

    clearScreen();

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
  private static int mainMenu(Scanner sc) {
    ChoiceWindow mainMenu = new ChoiceWindow();
    mainMenu.addChoice("Manage/display food storage.");
    mainMenu.addChoice("Manage grocery types.");
    mainMenu.addChoice("Exit program.");

    return mainMenu.choiceSequnce("What do you want to do? ", sc);
  }

  private static void manageFoodStorageMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, DialogOptionCreator dialogCreator) {
    ChoiceWindow manageFoodStorageMenu = new ChoiceWindow();

    manageFoodStorageMenu.addChoice("Display out-of-date food only.");
    manageFoodStorageMenu.addChoice("Search in food storage.");
    manageFoodStorageMenu.addChoice("Add grocery to storage.");
    manageFoodStorageMenu.addChoice("Remove grocery from storage.");
    manageFoodStorageMenu.addChoice("Return to main menu.");

    foodStorageLoop:
    while (true) {
      clearScreen();
      tableCreator.groceryInstanceTable(foodStorage.getAllGroceryInstances());

      int manageFoodStorageChoice = manageFoodStorageMenu.choiceSequnce(
          "Manage food storage: ", sc);

      switch (manageFoodStorageChoice) {
        case 1:
          tableCreator.groceryInstanceTable(foodStorage.getOutOfDateInstances());

          System.out.println("\n\nPress ENTER to continue...");
          sc.nextLine();
          sc.nextLine();
          sc.nextLine();

          break;

        case 2:
          while (true) {
            System.out.println("Enter your search term:");

            String searchTerm = sc.nextLine();
            sc.nextLine();

            ArrayList<GroceryInstance> searchResults = foodStorage.groceryInstanceSearch(
                searchTerm);

            if (searchResults.isEmpty()) {

              while (true) {
                clearScreen();
                // the search failed because there were no matching names, do you want to continue?
                System.out.println("The search term " + searchTerm + " gave no matching results. \n"
                    + "Do you want to search again? (Y/N)");

                // makes sure the user inputs either Y or N.
                try {
                  String yesNoSc = sc.nextLine();

                  // if "n" searchLoop breaks, and it returns the user to the manageFoodStorage menu.
                  if (yesNoSc.equalsIgnoreCase("n")) {
                    clearScreen();
                    break;

                  }
                  // if "y" YNloop breaks, returning the user to the search menu.
                  else if (yesNoSc.equalsIgnoreCase("y")) {
                    clearScreen();
                    break;
                  }

                } catch (Exception e) {
                  System.out.println("Please enter Y, if yes, or N, if no.");
                }
              }

            } else {
              // if the search arraylist isnt empty, it prints out the search items in a table.
              clearScreen();
              System.out.println("Search results:");
              tableCreator.groceryInstanceTable(foodStorage.groceryInstanceSearch(searchTerm));

              System.out.println("Press ENTER to continue.");
              sc.nextLine();

              break;

            }
          }
          break;

        case 3:
          tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());

          String yesNoSc = dialogCreator.yesNoOption(sc,
              "Do you want to add a grocery based on an "
                  + "already existing Grocery type (Y/N)? "
                  + "(see the above list)");

          // if yes, create a new grocery instance based on an already added grocery type
          switch (yesNoSc) {
            case "y" -> {
              clearScreen();
              int groceryTypeIndex = dialogCreator.validGroceryTypeIndex(sc, foodStorage,
                  tableCreator,
                  "Chose one grocery based on its number (see (Num) of the above table)");

              addGroceryInstance(sc, foodStorage, foodStorage.getSpecificType(groceryTypeIndex),
                  dialogCreator);
            }
            case "n" -> {
              String yesNoScInner = dialogCreator.yesNoOption(sc,
                  "Do you want to create a new Grocery Instance?");

              switch (yesNoScInner) {
                // if yes make a new grocerytype and make a new grocery instance based on the new
                // grocery type, and return the user to the previous menu.
                case "y" -> {
                  clearScreen();
                  addGroceryType(sc, foodStorage, dialogCreator);

                  clearScreen();
                  // gets the newest grocery instance (since it automatically does index - 1, we write
                  // 0 to get the newest instance in the Grocery type list.
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

        case 4:
          break;
        case 5:
          break foodStorageLoop;

      }
    }
  }

  private static void manageGroceryTypeMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator, DialogOptionCreator dialogCreator) {
    ChoiceWindow manageGroceryTypeMenu = new ChoiceWindow();

    manageGroceryTypeMenu.addChoice("Add grocery type.");
    manageGroceryTypeMenu.addChoice("Edit grocery type.");
    manageGroceryTypeMenu.addChoice("Delete grocery type.");
    manageGroceryTypeMenu.addChoice("Return to main menu.");

    groceryTypeLoop:
    while (true) {
      clearScreen();
      tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());
      int choice = manageGroceryTypeMenu.choiceSequnce("Manage grocery types: ", sc);
      switch (choice) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break groceryTypeLoop;


      }
    }

  }

  // add-methods.

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
      int mainMenuChoice = mainMenu(sc);

      switch (mainMenuChoice) {
        case 1:
          manageFoodStorageMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;
        case 2:
          manageGroceryTypeMenu(sc, foodStorage, tableCreator, dialogCreator);
          break;
        case 3:
          sc.close();
          System.exit(0);
      }
    }
  }
}
