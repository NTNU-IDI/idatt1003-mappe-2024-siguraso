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

    ArrayList<GroceryInstance> tomatoSearch = foodStorage.groceryInstanceSearch("tomato");

    assertEquals("Tomato", tomatoSearch.get(0).getName());
    assertEquals("Tomato", tomatoSearch.get(1).getName());
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

    assertEquals("Mushroom", foodStorage.getSpecificInstance(1).getName());
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

    assertEquals(0.2, foodStorage.getSpecificInstance(1).getAmount());
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

    int[] testValues = new int[]{1, 2};

    assertEquals((0.4 * 10) + (23 * 2), foodStorage.getSpecificValue(testValues));

  }

  @Test
  void getTotalValue() {
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

    assertEquals((0.4 * 10) + (2 * 23) + (0.7 * 10) + (1 * 103) + (4 * 13),
        foodStorage.getTotalValue());
  }

  @Test
  void getOutOfDateInstances() {
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

    // manually add the elements that are out of date
    ArrayList<GroceryInstance> outOfDate = new ArrayList<>();
    outOfDate.add(mushroom01);
    outOfDate.add(kiwi01);

    // makes a version that is made using the getOutOfDateInstances method.
    ArrayList<GroceryInstance> outOfDateTest = foodStorage.getOutOfDateInstances();

    // checks the two elements that should be included individually
    assertEquals(outOfDate.get(0).getName(), outOfDateTest.get(0).getName());
    assertEquals(outOfDate.get(1).getName(), outOfDateTest.get(1).getName());

    // checks if the outOfDateTest is at length it should be by purposely trying to print out an
    // element of an index that should be out of range.
    try {
      System.out.println(outOfDateTest.get(2).getName());
    } catch (Exception e) {
      System.out.println("The list is as long as it should be :)");
    }
  }

  @Test
  void getOutOfDateValue() {
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

    assertEquals((2 * 23) + (4 * 13), foodStorage.getOutOfDateValue());
  }
}