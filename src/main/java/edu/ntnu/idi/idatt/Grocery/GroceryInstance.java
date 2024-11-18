package edu.ntnu.idi.idatt.Grocery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unlike GroceryTypes, which here are more or less viewed as a 'class' of a grocery, a
 * GroceryInstance is viewed as a product of a GroceryType, or in other words we view
 * GroceryInstances as the products themselves, while GroceryTypes are the product class. For
 * example the product class tomato can have many different products, and each product have their
 * own prices, best-before dates, etc.. Grocery instances define exactly these product-specific
 * variables based on a 'product clas', or in other words, a GroceryType.
 **/
public class GroceryInstance {

  // Object variables
  private final GroceryType groceryType;
  private double amount;
  private double pricePerUnit;
  private String bestBefore;

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

  /**
   * Gets the name of the grocery
   *
   * @return String contaoining the name of the grocery
   */
  public String getName() {
    return this.groceryType.getName();
  }

  /**
   * gets the measurement unit most often associated to this type of grocery.
   *
   * @return A string containing the measurement unit.
   */
  public String getMeasurementUnit() {
    return this.groceryType.getMeasurementUnit();
  }

  /**
   * Gets the amount of the grocery defined in the GroceryInstance.
   *
   * @return a double containing the amount.
   */
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
      System.out.println("Wrong date format! (DD.MM.YYYY)");
      return null;
    }
  }

  /**
   * Gets the price per unit for this GroceryInstance.
   *
   * @return a double containing the price per unit
   */
  public double getPricePerUnit() {
    return this.pricePerUnit;
  }

  /**
   * Gets the total price of this GroceryInstance
   *
   * @return a double containing the total price of this GroceryInstance.
   */
  public double getPrice() {
    return this.amount * this.pricePerUnit;
  }

  // Set-methods

  /**
   * Sets the amount of the GroceryInstance.
   *
   * @param newAmount a double containing the new Amount.
   */
  public void setAmount(double newAmount) {
    this.amount = newAmount;
  }

  /**
   * Sets the price per unit
   *
   * @param newPricePerUnit a double containing the new price per unit.
   */
  public void setPricePerUnit(double newPricePerUnit) {
    this.pricePerUnit = newPricePerUnit;
  }

  /**
   * Sets the best before date.
   *
   * @param newBestBefore the new best before date in the format DD.MM.YYYY
   */
  public void setBestBeforeDate(String newBestBefore) {
    this.bestBefore = newBestBefore;
  }

  // Other void-methods.

  /**
   * Removes a certain amount of this GroceryInstance.
   *
   * @param amount a double containing the amount you would like to remove.
   */
  public void removeAmount(double amount) {
    this.amount -= amount;
  }

  /**
   * Adds a certain amount of this GroceryInstance.
   *
   * @param amount a double of the amount that is to be added.
   */
  public void addAmount(double amount) {
    this.amount += amount;
  }

  // Boolean methods

  /**
   * Checks weather or not this GroceryInstance is out of date.
   */
  public boolean isOutOfDate() {
    Date today = new Date();

    return this.getBestBeforeDate().before(today);
  }

}
