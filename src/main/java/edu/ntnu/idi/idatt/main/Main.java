package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.Grocery.FoodStorage;
import edu.ntnu.idi.idatt.Grocery.GroceryType;
import edu.ntnu.idi.idatt.Grocery.Cookbook;
import edu.ntnu.idi.idatt.Grocery.Recipe;
import edu.ntnu.idi.idatt.Interface.DialogOptionCreator;
import edu.ntnu.idi.idatt.Interface.TableCreator;
import edu.ntnu.idi.idatt.Interface.MenuCreator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class which executes the menus from the MenuCreator class.
 */
public class Main {

  /**
   * Starts the program by defining some pre-defined grocery types, and launches the main menu.
   */
  public static void main(String[] args) {
    //defines some predefined grocery types.
    ArrayList<GroceryType> allTypes = new ArrayList<>();

    allTypes.add(new GroceryType("Tomato", "kg"));
    allTypes.add(new GroceryType("Potato", "kg"));
    allTypes.add(new GroceryType("Cabbage", "pcs."));
    allTypes.add(new GroceryType("Salt", "pcs."));
    allTypes.add(new GroceryType("Bread", "pcs."));
    allTypes.add(new GroceryType("Milk", "L"));
    allTypes.add(new GroceryType("Cheese", "pcs."));
    allTypes.add(new GroceryType("Potato", "kg"));

    // creates the recipe list.
    ArrayList<Recipe> recipes = new ArrayList<>();

    // defines all the objects needed for the main menu.
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>(), allTypes);
    TableCreator tableCreator = new TableCreator();
    DialogOptionCreator dialogCreator = new DialogOptionCreator();
    Cookbook cookBook = new Cookbook(recipes);
    MenuCreator menuCreator = new MenuCreator();
    Scanner sc = new Scanner(System.in);

    //starts the main menu.
    menuCreator.mainMenu(sc, foodStorage, tableCreator, dialogCreator, cookBook);

    // once the main menu is finished, it closes the scanner and exits the program.
    sc.close();
  }
}
