package edu.ntnu.idi.idatt.Grocery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GroceryInstance {

  // Object variables
  private final GroceryType groceryType;
  private double amount;
  private double pricePerUnit;
  private final String bestBefore;

  // Constructor

  /**
   * Creates an "Instance" of a given GroceryType object, and adds information that can differ based
   * on for example date of purchase (e.g. different best before dates) and the place of purchase
   * (e.g. different prices per unit),
   *
   * @param groceryType  Instance of the GroceryType class, which grocery ware is it?
   * @param amount       The amount of this GroceryType (based on the given unit measurement in this
   *                     GroceryType instance).
   * @param pricePerUnit The cost per unit (based on the give unit measurement given in this
   *                     GroceryType instance).
   * @param bestBefore   The best before date (given as an instance of String in the format
   *                     "DD.MM.YYYY").
   */
  public GroceryInstance(GroceryType groceryType, double amount, double pricePerUnit,
      String bestBefore) {
    this.groceryType = groceryType;
    this.amount = amount;
    this.bestBefore = bestBefore;
    this.pricePerUnit = pricePerUnit;
  }

  // Get-methods
  public String getName() {
    return this.groceryType.getName();
  }

  public String getMeasurementUnit() {
    return this.groceryType.getMeasurementUnit();
  }

  public double getAmount() {
    return this.amount;
  }

  /**
   * Method for displaying the best before date as an instance of String. Useful when trying to
   * display the best before date.
   */
  public String getBestBeforeString() {
    return this.bestBefore;
  }

  /**
   * Method for returning the best before date as an instance of Date. Useful when comparing the
   * current date and the best before date. Throws a ParseException if the date is not in the format
   * "DD.MM.YYYY".
   */
  public Date getBestBeforeDate() {
    // Creates a dateformat dd-MM-yyyy
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    // Parses this instance's bestBefore String object to a Date object.
    try {
      return format.parse(this.bestBefore);
    } catch (ParseException e) {
      System.out.println("Feil format på datoen! (dd.MM.yyyy)");
      return null;
    }
  }

  public double getPricePerUnit() {
    return this.pricePerUnit;
  }

  public double getPrice() {
    return this.amount * this.pricePerUnit;
  }

  // Set-methods
  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  // Other void-methods.
  public void removeAmount(double amount) {
    this.amount -= amount;
  }

  // Boolean methods

  /**
   * Checks weather or not theis GroceryInstance is out of date.
   */
  public Boolean isOutOfDate() {
    Date today = new Date();

    return this.getBestBeforeDate().before(today);
  }

}
