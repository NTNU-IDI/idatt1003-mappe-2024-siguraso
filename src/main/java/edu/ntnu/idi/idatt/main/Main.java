package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Interface.ChoiceWindow;
import edu.ntnu.idi.idatt.Interface.TableCreator;

import edu.ntnu.idi.idatt.Grocery.GroceryInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  // methods
  // misc. methods:
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  //checks if a date is valid based on the date format used throughout the program.
  public static boolean isValidDate(String dateString) {
    try {
      SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
      format.parse(dateString);

      //if it can format the dateString, return true;
      return true;
    } catch (ParseException e) {

      //if it cant format the dateString, return false:
      return false;
    }
  }

  // add groceryType/groceryInstance


  // menu-methods:
  private static int mainMenu(Scanner sc) {
    ChoiceWindow mainMenu = new ChoiceWindow();
    mainMenu.addChoice("Manage/display food storage.");
    mainMenu.addChoice("Manage grocery types.");
    mainMenu.addChoice("Exit program.");

    return mainMenu.choiceSequnce("What do you want to do? ", sc);
  }

  private static void manageFoodStorageMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator) {
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
          searchLoop:
          while (true) {
            System.out.println("Enter your search term:");

            String searchTerm = sc.nextLine();
            sc.nextLine();

            ArrayList<GroceryInstance> searchResults = foodStorage.groceryInstanceSearch(
                searchTerm);

            if (searchResults.isEmpty()) {

              YNloop:
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
                    break searchLoop;

                  }
                  // if "y" YNloop breaks, returning the user to the search menu.
                  else if (yesNoSc.equalsIgnoreCase("y")) {
                    clearScreen();
                    break YNloop;
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

              break searchLoop;

            }
          }
          break;

        case 3:
          tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());

          System.out.println(
              "Do you want to add a grocery based on an already existing Grocery type (Y/N)? "
                  + "(see the above list)");

          String yesNoSc;

          while (true) {
            //makes sure that the user inputs Y or N.
            try {
              yesNoSc = sc.nextLine();
              sc.nextLine();

              if (yesNoSc.equalsIgnoreCase("n") || yesNoSc.equalsIgnoreCase("y")) {
                break;
              } else {
                clearScreen();
                System.out.println("Please enter Y, if yes, or N, if no.");
              }

            } catch (Exception e) {
              clearScreen();
              System.out.println("Please enter Y, if yes, or N, if no.");
            }
          }

          // if yes, create a new grocery instance based on an already added grocery type
          if (yesNoSc.equalsIgnoreCase("y")) {
            tableCreator.groceryTypeTable(foodStorage.getAllGroceryTypes());

            int groceryTypeIndex;

            //makes sure the user inputs a valid index.
            while (true) {
              try {
                System.out.println(
                    "Please input the number (Num) of the Grocery type you would like to add.");

                groceryTypeIndex = sc.nextInt();

                // if it is a valid input based on the amount of total grocery types, break the loop.
                if (groceryTypeIndex >= 1 && groceryTypeIndex <= foodStorage.getAllGroceryTypes()
                    .size()) {
                  break;
                }
                // if it isnt a valid input, restart the loop, and encourage the user
                // to input a valid number.
                else {
                  clearScreen();
                  System.out.println(
                      "Please enter an integer 1 - " + foodStorage.getAllGroceryTypes().size()
                          + ".\n\n");
                }

              } catch (Exception e) {
                // if the user inputs something other than an integer,
                // restart the loop and enourage the user again.
                clearScreen();
                System.out.println(
                    "Please enter an integer 1 - " + foodStorage.getAllGroceryTypes().size()
                        + ".\n\n");
              }
            }

            double pricePerUnit;

            // makes sure the user enters a valid number.
            while (true) {
              try {
                System.out.println(
                    "Please enter the price of " + foodStorage.getSpecificType(groceryTypeIndex)
                        .getName() + " per " + foodStorage.getSpecificType(groceryTypeIndex)
                        .getMeasurementUnit() + ".");

                pricePerUnit = sc.nextDouble();
                sc.nextLine();

                // if it is a valid input (meaning not a negative number or a number above 999999)
                // it breaks the loop and continues.
                if (pricePerUnit > 0 && pricePerUnit < 1000000) {
                  break;
                }
                // if it isnt a valid input, restart the loop, and encourage the user
                // to input a valid number.
                else {
                  clearScreen();
                  System.out.println("Please enter a price 0 - 999999\n\n");
                }

              } catch (Exception e) {
                // if the user inputs something other than an integer,
                // restart the loop and enourage the user again.
                clearScreen();
                System.out.println("Please enter a price 0 - 999999\n\n");
              }
            }

            double groceryAmount;

            // makes sure the user enters a valid number.
            while (true) {
              try {
                System.out.println(
                    "Please enter the amount of " + foodStorage.getSpecificType(groceryTypeIndex)
                        .getMeasurementUnit() + " you would like to add.");

                groceryAmount = sc.nextDouble();
                sc.nextLine();

                // if it is a valid input (meaning not a negative number or a number above 999999)
                // it breaks the loop and continues.
                if (groceryAmount > 0 && groceryAmount < 1000000) {
                  break;
                }
                // if it isnt a valid input, restart the loop, and encourage the user
                // to input a valid number.
                else {
                  clearScreen();
                  System.out.println("Please enter an number 0 - 999999\n\n");
                }

              } catch (Exception e) {
                // if the user inputs something other than an integer,
                // restart the loop and enourage the user again.
                clearScreen();
                System.out.println("Please enter an number 0 - 999999\n\n");
              }
            }

            String bestBefore;

            while (true) {
              clearScreen();
              System.out.println("Please enter the best before date. (DD.MM.YYYY)");

              bestBefore = sc.nextLine();
              sc.nextLine();

              if (isValidDate(bestBefore)) {
                break;
              } else {
                clearScreen();

                System.out.println("Please enter the date using the format 'DD.MM.YYYY' \n\n");
              }
            }

            while (true) {
              clearScreen();
              System.out.println("Would you like to add this grocery to food storage (Y/N)?"
                  + "\n\nName: " + foodStorage.getSpecificType(groceryTypeIndex).getName()
                  + "\nAmount: " + groceryAmount + " " +
                  foodStorage.getSpecificType(groceryTypeIndex).getMeasurementUnit() +
                  "\nBest before date: " + bestBefore);

              //makes sure that the user inputs Y or N.
              try {
                yesNoSc = sc.nextLine();
                sc.nextLine();

                if (yesNoSc.equalsIgnoreCase("n") || yesNoSc.equalsIgnoreCase("y")) {
                  break;
                } else {
                  clearScreen();
                  System.out.println("Please enter Y, if yes, or N, if no.");
                }

              } catch (Exception e) {
                clearScreen();
                System.out.println("Please enter Y, if yes, or N, if no.");
              }
            }

            if (yesNoSc.equalsIgnoreCase("y")) {
              // if the user wants to add this to the food storage, it appends it and breaks the
              // switch case,
              foodStorage.addInstance(
                  new GroceryInstance(foodStorage.getSpecificType(groceryTypeIndex), groceryAmount,
                      pricePerUnit, bestBefore));

              break;
            } else {
              //if the user does not want to add this to the food storage, it breaks the switch case
              // and returns the user to the manage food storage menu.
              break;
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
      TableCreator tableCreator) {
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
    TableCreator tableCreator = new TableCreator();
    Scanner sc = new Scanner(System.in);

    ArrayList<GroceryInstance> allGroceries = new ArrayList<>();
    FoodStorage foodStorage = new FoodStorage(allGroceries);

    while (true) {
      int mainMenuChoice = mainMenu(sc);

      switch (mainMenuChoice) {
        case 1:
          manageFoodStorageMenu(sc, foodStorage, tableCreator);
          break;
        case 2:
          manageGroceryTypeMenu(sc, foodStorage, tableCreator);
          break;
        case 3:
          sc.close();
          System.exit(0);
      }
    }
  }
}
