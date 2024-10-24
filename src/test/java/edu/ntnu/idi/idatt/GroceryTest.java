package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GroceryTest {

  GroceryType surST = new GroceryType("Surstrømming", "stk.");

  @Test
  void getName() {
    assertEquals("Surstrømming", surST.getName());
  }

  @Test
  void getMeasurementUnit() {
    assertEquals("stk.", surST.getMeasurementUnit());
  }
}