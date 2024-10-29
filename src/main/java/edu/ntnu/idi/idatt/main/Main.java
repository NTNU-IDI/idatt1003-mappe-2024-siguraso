package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Interface.ChoiceWindow;
import edu.ntnu.idi.idatt.Interface.TableCreator;

import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Grocery.GroceryInstance;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

  // methods
  // misc. methods:
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
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

  private static int manageFoodStorageMenu(Scanner sc, FoodStorage foodStorage,
      TableCreator tableCreator) {
    ChoiceWindow manageFoodStorageMenu = new ChoiceWindow();

    manageFoodStorageMenu.addChoice("Display food storage.");
    manageFoodStorageMenu.addChoice("Display out-of-date food in storage.");
    manageFoodStorageMenu.addChoice("Search in food storage.");
    manageFoodStorageMenu.addChoice("Add grocery to storage.");

    return manageFoodStorageMenu.choiceSequnce("Manage food storage: ", sc);

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

  // main method
  public static void main(String[] args) {
    //declares instances of scanner and table creator:
    TableCreator tableCreator = new TableCreator();
    Scanner sc = new Scanner(System.in);

    ArrayList<GroceryInstance> allGroceries = new ArrayList<>();
    FoodStorage foodStorage = new FoodStorage(allGroceries);

    while (true) {
      int mainMenuChoice = mainMenu(sc);

      switch (mainMenuChoice) {
        case 1:
          int manageFoodStorageChoice = manageFoodStorageMenu(sc, foodStorage, tableCreator);
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
