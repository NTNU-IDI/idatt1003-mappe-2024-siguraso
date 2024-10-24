package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

class GroceryInstanceTest {

  Grocery tomato = new Grocery("Tomat", "stk.");
  GroceryInstance tomato1 = new GroceryInstance(tomato, 5, "02.11.2024");

  @Test
  void getName() {
    assertEquals("Tomat", tomato1.getName());
  }

  @Test
  void getMeasurementUnit() {
    assertEquals("stk.", tomato1.getMeasurementUnit());
  }

  @Test
  void getAmount() {
    assertEquals(5, tomato1.getAmount());
  }

  @Test
  void getBestBeforeString() {
    assertEquals("02.11.2024", tomato1.getBestBeforeString());
  }

  @Test
  void getBestBeforeDate() throws ParseException {
    assertEquals(new GregorianCalendar(2024, Calendar.NOVEMBER, 2).getTime(),
        tomato1.getBestBeforeDate());
  }

  @Test
  void setAmount() {
    tomato1.setAmount(6);
    assertEquals(6, tomato1.getAmount());
  }
}