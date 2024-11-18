package edu.ntnu.idi.idatt.Grocery;

/**
 * An instance of the GroceryType class explains a "general class" of a grocery (e.g. tomato,
 * potato, butter, etc.). This class is used in tandem with GroceryInstance, where GroceryType
 * explains what class the grocery is (if the grocery is a tomato, it is a part of the "tomato
 * general class", or in other words, the tomato GroceryType).
 */
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

  /**
   * Gets the name of the GroceryType.
   *
   * @return a String containing the name of the grocery type.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the measurement unit most often associated with this type of grocery.
   *
   * @return a String containing the measurement unit.
   */
  public String getMeasurementUnit() {
    return measurementUnit;
  }

  // Set-methods

  /**
   * Sets the name of the grocery type to one defined by the user.
   *
   * @param newName the new user-specified name.
   */
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Sets the measurement unit of the grocery type to one defined by the user.
   *
   * @param newMeasurementUnit a String containing the new user-defined measurement unit
   */
  public void setMeasurementUnit(String newMeasurementUnit) {
    this.measurementUnit = newMeasurementUnit;
  }

}