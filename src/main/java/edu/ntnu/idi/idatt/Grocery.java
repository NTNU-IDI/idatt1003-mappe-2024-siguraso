package edu.ntnu.idi.idatt;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Grocery {

  // Object variables
  private final String name;
  private final String measurementUnit;

  /**
   * Grocery class that defines important information about one type of grocery.
   *
   * @param name            The name of the grocery.
   * @param measurementUnit The unit most often used to measure said grocery.
   */
  Grocery(String name, String measurementUnit) {
    this.name = name;
    this.measurementUnit = measurementUnit;
  }


}