package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GroceryInstanceTest {

  GroceryInstance groceryInstance;

  @BeforeEach
  void setUp() {
    groceryInstance = new GroceryInstance(new GroceryType("Tomato", "kg"),
        10, 20, "01.01.2022");
  }

  @Nested
  @DisplayName("Negative tests for the GroceryInstance class, checks if the GroceryInstance throws "
      + "exceptions at the right times.")
  public class GroceryInstanceThrowsExceptions {

    @Test
    @DisplayName("setAmount throws an exception when the amount is negative or over 999.9.")
    void verifySetAmountThrowsException() {
      try {
        groceryInstance.setAmount(-10);
        fail(
            "verifySetAmountThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("The grocery amount must be between 0 and 999.9!", e.getMessage());
      }

      try {
        groceryInstance.setAmount(1000);
        fail(
            "verifySetAmountThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("The grocery amount must be between 0 and 999.9!", e.getMessage());
      }
    }

    @Test
    @DisplayName("setPricePerUnit throws an exception when the price per unit is negative or over 99999.9.")
    void verifySetPricePerUnitThrowsException() {
      try {
        groceryInstance.setPricePerUnit(-10);
        fail(
            "verifySetPricePerUnitThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("The grocery price per unit must be between 0 and 99999.9!",
            e.getMessage());
      }

      try {
        groceryInstance.setPricePerUnit(100000);
        fail(
            "verifySetPricePerUnitThrowsException() failed, since it didnt throw the expected "
                + "IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("The grocery price per unit must be between 0 and 99999.9!",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("setBestBefore throws an exception when the date is in the wrong format.")
    void groceryInstanceSetBestBeforeThrowsException() {
      try {
        // try a date that is in the wrong format:
        groceryInstance.setBestBeforeDate("01-01-2022");
        fail(
            "groceryInstanceSetBestBeforeThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter a valid date in the format DD.MM.YYYY", e.getMessage());
      }

      try {
        // try a string that is just a random string:
        groceryInstance.setBestBeforeDate("This is not a date, I never get asked out on dates :(");

        fail(
            "groceryInstanceSetBestBeforeThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter a valid date in the format DD.MM.YYYY", e.getMessage());
      }

      try {
        // try a string which just consists of a number with 8 digits which could represent a date:
        groceryInstance.setBestBeforeDate("01112024");

        fail(
            "groceryInstanceSetBestBeforeThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter a valid date in the format DD.MM.YYYY", e.getMessage());
      }
    }

    @Test
    @DisplayName(
        "removeAmount throws an exception when amountToRemove is negative (or zero) or over "
            + "more than the current amount.")
    void groceryInstanceRemoveAmountThrowsException() {
      try {
        groceryInstance.removeAmount(-10);
        fail(
            "groceryInstanceRemoveAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter an amount between 0 and " + groceryInstance.getAmount(),
            e.getMessage());
      }

      try {
        groceryInstance.removeAmount(0);
        fail(
            "groceryInstanceRemoveAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter an amount between 0 and " + groceryInstance.getAmount(),
            e.getMessage());
      }

      try {
        groceryInstance.removeAmount(30);
        fail(
            "groceryInstanceRemoveAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Please enter an amount between 0 and " + groceryInstance.getAmount(),
            e.getMessage());
      }
    }

  }
}