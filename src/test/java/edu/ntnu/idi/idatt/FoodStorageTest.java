package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FoodStorageTest {


  @Test
  void getSpecificInstance() {

    ArrayList<GroceryInstance> items = new ArrayList<>();

    GroceryType TOMATO = new GroceryType("Tomato", "kg");
    GroceryType MUSHROOM = new GroceryType("Mushroom", "kg");
    GroceryType CHICKEN = new GroceryType("Chicken", "kg");
    GroceryType KIWI = new GroceryType("Kiwi", "stk.");
    GroceryInstance tomato01 = new GroceryInstance(TOMATO, 0.4, 10, "11.11.2024");
    GroceryInstance mushroom01 = new GroceryInstance(MUSHROOM, 2, 23, "10.10.2024");
    GroceryInstance chicken01 = new GroceryInstance(CHICKEN, 1, 103, "30.10.2024");
    GroceryInstance kiwi01 = new GroceryInstance(KIWI, 4, 13, "27.10.2024");

    items.add(tomato01);
    items.add(mushroom01);
    items.add(chicken01);
    items.add(kiwi01);

    FoodStorage foodStorage = new FoodStorage(items);

    assertEquals(foodStorage.getSpecificInstance(1).getName(), "Tomato");
    assertEquals(foodStorage.getSpecificInstance(2).getName(), "Mushroom");
  }

  @Test
  void groceryInstanceSearch() {

    ArrayList<GroceryInstance> items = new ArrayList<>();

    GroceryType TOMATO = new GroceryType("Tomato", "kg");
    GroceryType MUSHROOM = new GroceryType("Mushroom", "kg");
    GroceryType CHICKEN = new GroceryType("Chicken", "kg");
    GroceryType KIWI = new GroceryType("Kiwi", "stk.");
    GroceryInstance tomato01 = new GroceryInstance(TOMATO, 0.4, 10, "11.11.2024");
    GroceryInstance mushroom01 = new GroceryInstance(MUSHROOM, 2, 23, "10.10.2024");
    GroceryInstance tomato02 = new GroceryInstance(TOMATO, 0.7, 10, "10.11.2024");
    GroceryInstance chicken01 = new GroceryInstance(CHICKEN, 1, 103, "30.10.2024");
    GroceryInstance kiwi01 = new GroceryInstance(KIWI, 4, 13, "27.10.2024");

    items.add(tomato01);
    items.add(mushroom01);
    items.add(tomato02);
    items.add(chicken01);
    items.add(kiwi01);

    FoodStorage foodStorage = new FoodStorage(items);

    assertEquals(foodStorage.groceryInstanceSearch("Tomato").get(0).getName(), "Tomato");
    assertEquals(foodStorage.groceryInstanceSearch("Tomato").get(1).getName(), "Tomato");
  }

  @Test
  void removeInstance() {

    ArrayList<GroceryInstance> items = new ArrayList<>();

    GroceryType TOMATO = new GroceryType("Tomato", "kg");
    GroceryType MUSHROOM = new GroceryType("Mushroom", "kg");
    GroceryType CHICKEN = new GroceryType("Chicken", "kg");
    GroceryType KIWI = new GroceryType("Kiwi", "stk.");
    GroceryInstance tomato01 = new GroceryInstance(TOMATO, 0.4, 10, "11.11.2024");
    GroceryInstance mushroom01 = new GroceryInstance(MUSHROOM, 2, 23, "10.10.2024");
    GroceryInstance tomato02 = new GroceryInstance(TOMATO, 0.7, 10, "10.11.2024");
    GroceryInstance chicken01 = new GroceryInstance(CHICKEN, 1, 103, "30.10.2024");
    GroceryInstance kiwi01 = new GroceryInstance(KIWI, 4, 13, "27.10.2024");

    items.add(tomato01);
    items.add(mushroom01);
    items.add(tomato02);
    items.add(chicken01);
    items.add(kiwi01);

    FoodStorage foodStorage = new FoodStorage(items);

    foodStorage.removeInstance(1);

    assertEquals(foodStorage.getSpecificInstance(1).getName(), "Mushroom");
  }

  @Test
  void removeInstanceAmount() {

    ArrayList<GroceryInstance> items = new ArrayList<>();

    GroceryType TOMATO = new GroceryType("Tomato", "kg");
    GroceryType MUSHROOM = new GroceryType("Mushroom", "kg");
    GroceryType CHICKEN = new GroceryType("Chicken", "kg");
    GroceryType KIWI = new GroceryType("Kiwi", "stk.");
    GroceryInstance tomato01 = new GroceryInstance(TOMATO, 0.4, 10, "11.11.2024");
    GroceryInstance mushroom01 = new GroceryInstance(MUSHROOM, 2, 23, "10.10.2024");
    GroceryInstance tomato02 = new GroceryInstance(TOMATO, 0.7, 10, "10.11.2024");
    GroceryInstance chicken01 = new GroceryInstance(CHICKEN, 1, 103, "30.10.2024");
    GroceryInstance kiwi01 = new GroceryInstance(KIWI, 4, 13, "27.10.2024");

    items.add(tomato01);
    items.add(mushroom01);
    items.add(tomato02);
    items.add(chicken01);
    items.add(kiwi01);

    FoodStorage foodStorage = new FoodStorage(items);

    foodStorage.removeInstanceAmount(1, 0.2);

    assertEquals(foodStorage.getSpecificInstance(1).getAmount(), 0.2);
  }

  @Test
  void getSpecificValue() {

    ArrayList<GroceryInstance> items = new ArrayList<>();

    GroceryType TOMATO = new GroceryType("Tomato", "kg");
    GroceryType MUSHROOM = new GroceryType("Mushroom", "kg");
    GroceryType CHICKEN = new GroceryType("Chicken", "kg");
    GroceryType KIWI = new GroceryType("Kiwi", "stk.");
    GroceryInstance tomato01 = new GroceryInstance(TOMATO, 0.4, 10, "11.11.2024");
    GroceryInstance mushroom01 = new GroceryInstance(MUSHROOM, 2, 23, "10.10.2024");
    GroceryInstance tomato02 = new GroceryInstance(TOMATO, 0.7, 10, "10.11.2024");
    GroceryInstance chicken01 = new GroceryInstance(CHICKEN, 1, 103, "30.10.2024");
    GroceryInstance kiwi01 = new GroceryInstance(KIWI, 4, 13, "27.10.2024");

    items.add(tomato01);
    items.add(mushroom01);
    items.add(tomato02);
    items.add(chicken01);
    items.add(kiwi01);

    FoodStorage foodStorage = new FoodStorage(items);

    int[] testValues = new int[]{1};

  }

  @Test
  void getTotalValue() {
  }

  @Test
  void getOutOfDateInstances() {
  }

  @Test
  void getOutOfDateValue() {
  }
}