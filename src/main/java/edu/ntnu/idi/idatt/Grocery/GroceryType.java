package edu.ntnu.idi.idatt.Grocery;

public class GroceryType {

  // Object variables
  private String name;
  private String measurementUnit;

  // Constructor

  /**
   * Grocery class that defines important information about one type of grocery.
   *
   * @param name            The name of the grocery.
   * @param measurementUnit The unit most often used to measure said grocery.
   */
  public GroceryType(String name, String measurementUnit) {
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

  // Set-methods
  public void setName(String newName) {
    this.name = newName;
  }

  public void setMeasurementUnit(String newMeasurementUnit) {
    this.measurementUnit = newMeasurementUnit;
  }

}