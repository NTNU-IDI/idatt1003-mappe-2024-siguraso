package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
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

    @Test
    @DisplayName(
        "addAmount if the addAmount is negative, addAmount = 0,  or if the addAmount + the original amount > 999.9.")
    void groceryInstanceAddAmountThrowsException() {
      try {
        groceryInstance.addAmount(-1);
        fail(
            "groceryInstanceAddAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Cannot add an that amount, please add an amount between 0 and " + (999.9
            - groceryInstance.getAmount()), e.getMessage());
      }

      try {
        groceryInstance.addAmount(0);
        fail(
            "groceryInstanceAddAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Cannot add an that amount, please add an amount between 0 and " + (999.9
            - groceryInstance.getAmount()), e.getMessage());
      }

      try {
        groceryInstance.addAmount(1000);
        fail(
            "groceryInstanceAddAmountThrowsException() failed, since it didnt throw the expected"
                + " IllegalArgumentException.");
      } catch (IllegalArgumentException e) {
        assertEquals("Cannot add an that amount, please add an amount between 0 and " + (999.9
            - groceryInstance.getAmount()), e.getMessage());
      }
    }

  }

  @Nested
  @DisplayName("Positive tests for the GroceryInstance class, checks if the GroceryInstance throws "
      + "exceptions when it should not.")
  public class GroceryInstanceDoesNotThrowExceptions {

    @Test
    @DisplayName("Test if the GroceryInstance does not throw any exceptions when creating a new "
        + "instance.")
    void newGroceryInstanceDoesNotThrowException() {
      try {
        GroceryInstance newGroceryInstance = new GroceryInstance(new GroceryType("null", "null"),
            0, 0, "01.01.2022");

      } catch (Exception e) {
        fail("newGroceryInstanceDoesNotThrowException() failed, since it threw an exception.");
      }
    }

    @Test
    @DisplayName("Getter methods fetch the correct values.")
    void groceryInstanceGetterMethods() {
      // create a 'copy' of the best before date that has the same values, but is a different object.
      // to run tests on it.
      Date bestBeforeCopy = new Date(122, Calendar.JANUARY, 1);

      try {
        assertEquals("Tomato", groceryInstance.getName());
        assertEquals("kg", groceryInstance.getMeasurementUnit());
        assertEquals("tomato", groceryInstance.getNameLowerCase());
        assertEquals(10, groceryInstance.getAmount());
        assertEquals(20, groceryInstance.getPricePerUnit());
        assertEquals((20 * 10), groceryInstance.getPrice());
        assertEquals("\u001B[31m01.01.2022\u001B[0m", groceryInstance.getBestBeforeString());
        assertEquals("\u001B[31m01.01.2022\u001B[0m", groceryInstance.getBestBeforeString());
        assertEquals(bestBeforeCopy, groceryInstance.getBestBeforeDate());
      } catch (Exception e) {
        fail("groceryInstanceGetterMethods() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("setAmount sets the amount to a new value without throwing an exception.")
    void groceryInstanceSetAmount() {
      try {
        groceryInstance.setAmount(20);
        assertEquals(20, groceryInstance.getAmount());
      } catch (Exception e) {
        fail("groceryInstanceSetAmount() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("setPricePerUnit sets the price per unit to a new value without throwing an exception.")
    void groceryInstanceSetPricePerUnit() {
      try {
        groceryInstance.setPricePerUnit(30);
        assertEquals(30, groceryInstance.getPricePerUnit());
      } catch (Exception e) {
        fail("groceryInstanceSetPricePerUnit() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("setBestBeforeDate sets the best before date to a new value without throwing an exception.")
    void groceryInstanceSetBestBeforeDate() {
      // create a 'copy' of the new best before date that has the same values, but is a different
      // object, used to compare the values.
      // Sets the date twice, once for an expire date that is before the current date, and once for
      // a date that is after the current date to test the red text color.
      Date bestBeforeCopy = new Date(123, Calendar.JANUARY, 1);

      try {
        groceryInstance.setBestBeforeDate("01.01.2023");
        assertEquals("\u001B[31m01.01.2023\u001B[0m", groceryInstance.getBestBeforeString());
        assertEquals(bestBeforeCopy, groceryInstance.getBestBeforeDate());
        groceryInstance.setBestBeforeDate("31.12.9998");
        assertEquals("31.12.9998", groceryInstance.getBestBeforeString());
      } catch (Exception e) {
        fail("groceryInstanceSetBestBeforeDate() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("removeAmount removes the correct amount without throwing an exception.")
    void groceryInstanceRemoveAmount() {
      try {
        groceryInstance.removeAmount(5);
        assertEquals(5, groceryInstance.getAmount());
      } catch (Exception e) {
        fail("groceryInstanceRemoveAmount() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("addAmount adds the correct amount without throwing an exception.")
    void groceryInstanceAddAmount() {
      try {
        groceryInstance.addAmount(5);
        assertEquals(15, groceryInstance.getAmount());
      } catch (Exception e) {
        fail("groceryInstanceAddAmount() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }

    @Test
    @DisplayName("isOutOfDate returns true if the best before date is before the current date.")
    void groceryInstanceIsOutOfDate() {
      try {
        assertTrue(groceryInstance.isOutOfDate());

        groceryInstance.setBestBeforeDate("31.12.9998");

        assertFalse(groceryInstance.isOutOfDate());
      } catch (Exception e) {
        fail("groceryInstanceIsOutOfDate() failed, since it threw an exception, message: "
            + e.getMessage());
      }
    }
  }
}