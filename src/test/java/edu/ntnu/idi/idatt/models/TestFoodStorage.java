package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

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
    foodStorage.addInstance(new GroceryInstance(potato, 1, 1, "01.01.2001"));

  }

  @Nested
  @DisplayName(
      "Negative tests for the FoodStorage class, checks if the FoodStorage throws exceptions at "
          + "the right times.")
  public class FoodStorageThrowsExceptions {

  }
}