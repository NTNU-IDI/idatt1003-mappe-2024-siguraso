package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestGroceryType {

  GroceryType groceryType;

  @BeforeEach
  void setUp() {
    groceryType = new GroceryType("tomato", "kg");
  }


  @Nested
  @DisplayName("Negative tests for the GroceryType class, checks if the GroceryType throws "
      + "exceptions at the right times.")
  public class GroceryTypeThrowsExceptions {

    @Test
    @DisplayName("setName throws an exception when the name is more than 30 chars.")
    void verifySetNameThrowsException() {
      try {
        groceryType.setName("This is a name that is way too long for the name of a grocery class");
        fail(
            "verifySetNameThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Name of grocery class cannot be longer than 30 characters!",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("setMeasurementUnit throws an exception when the unit is more than 10 chars.")
    void verifySetMeasurementUnitThrowsException() {
      try {
        groceryType.setMeasurementUnit("This unit is way too long for a measurement unit");
        fail(
            "verifySetMeasurementUnitThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Measurement unit of a grocery class cannot be longer than 16 "
            + "characters!", e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for the GroceryType class, checks if the GroceryType throws "
      + "exceptions when it should not.")
  public class GroceryTypeDoesNotThrowExceptions {

    @Test
    @DisplayName("Test if the GroceryType does not throw any exceptions when creating a new instance.")
    void newGroceryTypeDoesNotThrowException() {
      try {
        GroceryType newGroceryType = new GroceryType("null", "null");
      } catch (Exception e) {
        fail("newGroceryTypeDoesNotThrowException() failed, since it threw an exception.");
      }
    }
  }
}