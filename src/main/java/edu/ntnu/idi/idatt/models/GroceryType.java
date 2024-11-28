package edu.ntnu.idi.idatt.models;

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
    return this.name;
  }

  /**
   * Fetches the name of the {@link GroceryType} in lowercase. (Used for sorting purposes)
   *
   * @return the name of the grocery in lowercase.
   */
  public String getNameLowerCase() {
    return this.name.toLowerCase();
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
   * Sets the name of the {@link GroceryType} to one defined by the user.
   *
   * @param newName the new user-specified name.
   * @throws IllegalArgumentException if the name is longer than the character limit of 30
   *                                  characters.
   */
  public void setName(String newName) throws IllegalArgumentException {
    if (newName.length() > 30) {
      throw new IllegalArgumentException(
          "Name of grocery class cannot be longer than 30 characters!");
    } else {
      this.name = newName;
    }
  }

  /**
   * Sets the measurement unit of the grocery type to one defined by the user.
   *
   * @param newMeasurementUnit a String containing the new user-defined measurement unit
   * @throws IllegalArgumentException if a given measurement unit is longer than the character limit
   *                                  of 16 characters.
   */
  public void setMeasurementUnit(String newMeasurementUnit) throws IllegalArgumentException {
    if (newMeasurementUnit.length() > 16) {
      throw new IllegalArgumentException(
          "Measurement unit of a grocery class cannot be longer than 16 characters!");
    } else {
      this.measurementUnit = newMeasurementUnit;
    }
  }

}