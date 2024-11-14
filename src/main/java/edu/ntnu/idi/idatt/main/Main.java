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


public class Main {

  public static void main(String[] args) {
    ArrayList<GroceryType> allTypes = new ArrayList<>();

    allTypes.add(new GroceryType("Tomato", "kg"));
    allTypes.add(new GroceryType("Potato", "kg"));
    allTypes.add(new GroceryType("Cabbage", "pcs."));
    allTypes.add(new GroceryType("Salt", "pcs."));
    allTypes.add(new GroceryType("Bread", "pcs."));
    allTypes.add(new GroceryType("Milk", "L"));
    allTypes.add(new GroceryType("Cheese", "pcs."));
    allTypes.add(new GroceryType("Potato", "kg"));

    ArrayList<Recipe> recipes = new ArrayList<>();

    FoodStorage foodStorage = new FoodStorage(new ArrayList<>(), allTypes);
    TableCreator tableCreator = new TableCreator();
    DialogOptionCreator dialogCreator = new DialogOptionCreator();
    Cookbook cookBook = new Cookbook(recipes);
    MenuCreator menuCreator = new MenuCreator();
    Scanner sc = new Scanner(System.in);

    menuCreator.mainMenu(sc, foodStorage, tableCreator, dialogCreator, cookBook);
    sc.close();
  }
}
