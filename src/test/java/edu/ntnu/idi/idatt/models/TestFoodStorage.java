package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestFoodStorage {

  FoodStorage foodStorage;

  @BeforeEach
  public void setUp() {
    foodStorage = new FoodStorage();
    GroceryType tomato = new GroceryType("tomato", "kg");
    GroceryType potato = new GroceryType("potato", "kg");

    foodStorage.addType(tomato);
    foodStorage.addType(potato);

    // one out of date instance, one that is not.
    foodStorage.addInstance(new GroceryInstance(tomato, 1, 1, "01.01.9999"));
    foodStorage.addInstance(new GroceryInstance(potato, 1, 2, "01.01.2001"));

  }

  @Nested
  @DisplayName(
      "Positive tests for the FoodStorage class, checks if the FoodStorage does not throw exceptions"
          + " when it should not, as well as checking if they return the right data if needed.")
  public class FoodStorageDoesNotThrowExceptions {

    @Test
    @DisplayName("getter methods should not throw any exceptions when fetching data, and checks if "
        + "they return the right data.")
    void groceryInstanceGettersDoesNotThrowException() {
      try {
        GroceryType type = new GroceryType("type", "kg");
        GroceryInstance instance = new GroceryInstance(type, 1, 3,
            "01.01.9999");
        foodStorage.addType(type);
        foodStorage.addInstance(instance);

        assertEquals("type", foodStorage.getSpecificType(3).getName());
        assertEquals("tomato", foodStorage.getSpecificInstance(1).getName());
        assertEquals((1 + 3), foodStorage.getMultipleSpecificValue(new int[]{1, 3}));
        assertEquals((1 + 2 + 3), foodStorage.getTotalValue());
        assertEquals(1, foodStorage.getOutOfDateInstances().size());
        assertEquals(2, foodStorage.getOutOfDateValue());
        foodStorage.getSpecificInstance(3).setBestBeforeDate("01.01.2001");
        assertEquals(2, foodStorage.getOutOfDateInstances().size());
        assertEquals((2 + 3), foodStorage.getOutOfDateValue());
        assertEquals(3, foodStorage.getAllGroceryInstances().size());
        assertEquals(3, foodStorage.getAllGroceryTypes().size());
      } catch (Exception e) {
        fail("groceryInstanceGettersDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("newFoodStorage should not throw any exceptions when creating a new instance.")
    void newFoodStorageDoesNotThrowException() {
      try {
        FoodStorage newFoodStorage = new FoodStorage();
      } catch (Exception e) {
        fail("newFoodStorageDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("removeInstance to food storage should not throw any exceptions when removing an "
        + "instance.")
    void groceryInstanceSearchDoesNotThrowException() {
      try {
        assertEquals("tomato", foodStorage.groceryInstanceSearch("tom").getFirst().getName());
        assertEquals("potato", foodStorage.groceryInstanceSearch("po").getFirst().getName());
        assertEquals(0, foodStorage.groceryInstanceSearch("notInStorage").size());
      } catch (Exception e) {
        fail("groceryInstanceSearchDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("removeInstance to food storage should not throw any exceptions when removing an "
        + "instance.")
    void foodStorageRemoveInstanceDoesNotThrowException() {
      try {
        // getName of the first instance changes from tomato...
        assertEquals("tomato", foodStorage.getSpecificInstance(1).getName());
        foodStorage.removeInstance(1);
        // ...to potato because tomato was removed.
        assertEquals("potato", foodStorage.getSpecificInstance(1).getName());
        foodStorage.removeInstance(1);
        // after the second instance is removed, there are no instances left.
        assertEquals(0, foodStorage.getAllGroceryInstances().size());
      } catch (Exception e) {
        fail(
            "foodStorageRemoveInstanceDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("removeType to food storage should not throw any exceptions when removing a type.")
    void foodStorageRemoveTypeDoesNotThrowException() {
      try {
        // getName of the first instance changes from tomato...
        assertEquals("tomato", foodStorage.getSpecificType(1).getName());
        foodStorage.removeType(1);
        // ...to potato because tomato was removed.
        assertEquals("potato", foodStorage.getSpecificType(1).getName());
        foodStorage.removeType(1);
        // after the second instance is removed, there are no instances left.
        assertEquals(0, foodStorage.getAllGroceryTypes().size());
      } catch (Exception e) {
        fail("foodStorageRemoveTypeDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("addInstance to food storage should not throw any exceptions when adding a new "
        + "instance.")
    void foodStorageAddInstanceDoesNotThrowException() {
      try {
        // also indirectly tests the sortInsrances method because the instances are sorted by
        // alpghabetically, meaning that a grocery instance with the name "a" will sorted to the
        // first index.
        GroceryType type = new GroceryType("a", "kg");
        GroceryInstance instance = new GroceryInstance(type, 1, 1, "01.01.9999");
        foodStorage.addInstance(instance);
        assertEquals("a", foodStorage.getAllGroceryInstances().getLast().getName());
      } catch (Exception e) {
        fail("foodStorageAddInstanceDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("addType to food storage should not throw any exceptions when adding a new type.")
    void foodStorageAddTypeDoesNotThrowException() {
      try {
        GroceryType type = new GroceryType("a", "kg");
        foodStorage.addType(type);
        assertEquals("a", foodStorage.getAllGroceryTypes().getLast().getName());
      } catch (Exception e) {
        fail("foodStorageAddTypeDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName(
        "removeInstanceAmount to food storage should not throw any exceptions when removing "
            + "an amount of a specific instance.")
    void foodStorageRemoveInstanceAmountDoesNotThrowException() {
      try {
        // the amount of the first instance is 1, and 1 is removed.
        assertEquals(1, foodStorage.getSpecificInstance(1).getAmount());
        foodStorage.removeInstanceAmount(1, 0.9);
        // the amount of the first instance is now 0.
        assertEquals((1 - 0.9), foodStorage.getSpecificInstance(1).getAmount());
      } catch (Exception e) {
        fail(
            "foodStorageRemoveInstanceAmountDoesNotThrowException() failed, since it threw an "
                + "exception, message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName(
        "sortGroceryTypes sorts GroceryTypes alphabetically.")
    void foodStorageSortGroceryTypesDoesNotThrowException() {
      try {
        GroceryType type = new GroceryType("a", "kg");
        GroceryType type2 = new GroceryType("b", "kg");
        GroceryType type3 = new GroceryType("c", "kg");
        foodStorage.addType(type);
        foodStorage.addType(type2);
        foodStorage.addType(type3);
        foodStorage.sortGroceryTypes();
        assertEquals("a", foodStorage.getSpecificType(1).getName());
        assertEquals("b", foodStorage.getSpecificType(2).getName());
        assertEquals("c", foodStorage.getSpecificType(3).getName());
      } catch (Exception e) {
        fail(
            "foodStorageSortGroceryTypesDoesNotThrowException() failed, since it threw an "
                + "exception, message: " + e.getMessage());
      }
    }
  }
}