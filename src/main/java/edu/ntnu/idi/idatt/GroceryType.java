package edu.ntnu.idi.idatt;

public class GroceryType {

  // Object variables
  private final String name;
  private final String measurementUnit;

  // Constructor

  /**
   * Grocery class that defines important information about one type of grocery.
   *
   * @param name            The name of the grocery.
   * @param measurementUnit The unit most often used to measure said grocery.
   */
  GroceryType(String name, String measurementUnit) {
    this.name = name;
    this.measurementUnit = measurementUnit;
  }

  // Get-methods
  public String getName() {
    return name;
  }

  public String getMeasurementUnit() {
    return measurementUnit;
  }
}