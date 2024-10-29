package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Interface.ChoiceWindow;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    clearScreen();
    Scanner sc = new Scanner(System.in);

    while (true) {
      int mainMenuChoice = mainMenu(sc);

      switch (mainMenuChoice) {
        case 1:
          int manageFoodStorageChoice = manageFoodStorageMenu(sc);
          break;
        case 2:
          int manageGroceryTypes = manageGroceryTypeMenu(sc);
          break;
      }
    }
  }

  // methods
  // misc. methods:
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // menu-methods:
  private static int mainMenu(Scanner sc) {
    ChoiceWindow mainMenu = new ChoiceWindow();
    mainMenu.addChoice("Manage/display food storage.");
    mainMenu.addChoice("Manage grocery types.");

    return mainMenu.choiceSequnce("What do you want to do? ", sc);
  }

  private static int manageFoodStorageMenu(Scanner sc) {
    ChoiceWindow manageFoodStorageMenu = new ChoiceWindow();

    manageFoodStorageMenu.addChoice("Display food storage.");
    manageFoodStorageMenu.addChoice("Display out-of-date food in storage.");
    manageFoodStorageMenu.addChoice("Search in food storage.");
    manageFoodStorageMenu.addChoice("Add grocery to storage.");

    return manageFoodStorageMenu.choiceSequnce("Manage food storage: ", sc);

  }

  private static int manageGroceryTypeMenu(Scanner sc) {
    ChoiceWindow manageGroceryTypeMenu = new ChoiceWindow();

    manageGroceryTypeMenu.addChoice("Display all grocery types.");
    manageGroceryTypeMenu.addChoice("Add grocery type.");
    manageGroceryTypeMenu.addChoice("Edit grocery type.");
    manageGroceryTypeMenu.addChoice("Delete grocery type.");

    return manageGroceryTypeMenu.choiceSequnce("Manage grocery types: ", sc);
  }

}
