package edu.ntnu.idi.idatt.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unlike GroceryTypes, which here are more or less viewed as a 'class' of a grocery, a
 * GroceryInstance is viewed as a product of a GroceryType, or in other words we view
 * GroceryInstances as the products themselves, while GroceryTypes are the product class. For
 * example the grocery class tomato can have many different products, and each product have their
 * own prices, best-before dates, etc.. GroceryInstances define exactly these product-specific
 * variables based on a 'product class', or in other words, a GroceryType as well as other more
 * specific information about the product, like how much of the product there is.
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
   * (e.g. different prices per unit).
   *
   * @param groceryType  Instance of the GroceryType class, which "class of grocery" (e.g. tomato,
   *                     potato butter) is it?
   * @param amount       The amount of this GroceryType (based on the given unit measurement in this
   *                     GroceryType instance).
   * @param pricePerUnit The cost per unit (based on the given unit measurement given in this
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
   * Fetches the name of the {@link GroceryType} of this {@link GroceryInstance}.
   *
   * @return {@link String} containing the name of the {@link GroceryType}.
   */
  public String getName() {
    return this.groceryType.getName();
  }

  /**
   * Fetches the name of the {@link GroceryType} in lowercase. (Used for sorting purposes)
   *
   * @return a {@link String} containing the name of the grocery in lowercase.
   */
  public String getNameLowerCase() {
    return this.groceryType.getName().toLowerCase();
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
   * Fetches the amount of the {@link GroceryInstance}.
   *
   * @return a double containing the amount.
   */
  public double getAmount() {
    return this.amount;
  }

  /**
   * Method for displaying the best before date as an instance of String. Useful when trying to
   * display the best before date. If the grocery is out of date, the String will be colored red.
   * The best before date is given in the format "DD.MM.YYYY".
   */
  public String getBestBeforeString() {
    if (this.isOutOfDate()) {
      // if it out of date, color the String red.
      return "\u001B[31m" + this.bestBefore + "\u001B[0m";
    }
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
   * Fetches the price per unit for this GroceryInstance.
   *
   * @return a double containing the price per unit
   */
  public double getPricePerUnit() {
    return this.pricePerUnit;
  }

  /**
   * Gets the total price of this {@link GroceryInstance}.
   *
   * @return a double containing the total price of this GroceryInstance.
   */
  public double getPrice() {
    return this.amount * this.pricePerUnit;
  }

  // Set-methods

  /**
   * Sets the amount of the {@link GroceryInstance}.
   *
   * @param newAmount a double containing the new Amount.
   * @throws IllegalArgumentException if the given amount is less than 0, or more than 99999.9
   */
  public void setAmount(double newAmount) throws IllegalArgumentException {
    if (newAmount >= 0 && newAmount < 999.9) {
      this.amount = newAmount;
    } else {
      throw new IllegalArgumentException("The grocery amount must be between 0 and 999.9!");
    }
  }

  /**
   * Sets the price per unit of the {@link GroceryInstance}.
   *
   * @param newPricePerUnit a double containing the new price per unit.
   * @throws IllegalArgumentException if the given price per unit is less than 0 or more than
   *                                  99999.9.
   */
  public void setPricePerUnit(double newPricePerUnit) throws IllegalArgumentException {
    if (newPricePerUnit > 0 && newPricePerUnit < 99999.9) {
      this.pricePerUnit = newPricePerUnit;
    } else {
      throw new IllegalArgumentException(
          "The grocery price per unit must be between 0 and 99999.9!");
    }

  }

  /**
   * Sets the best before date of the {@link GroceryInstance}. Based on a given {@link String}
   * containing the date in the format "DD.MM.YYYY".
   *
   * @param newBestBefore the new best before date as a {@link String} in the format DD.MM.YYYY.
   * @throws IllegalArgumentException if the given date is invalid (e.g. has an invalid month, day
   *                                  or year, or a wrong date format.
   */
  public void setBestBeforeDate(String newBestBefore) throws IllegalArgumentException {
    if (isValidDate(newBestBefore)) {
      this.bestBefore = newBestBefore;
    } else {
      throw new IllegalArgumentException("Please enter a valid date in the format DD.MM.YYYY");
    }

  }

  // Other void-methods.

  /**
   * Removes a defined amount from this {@link GroceryInstance}.
   *
   * @param amountToRemove a double containing the amount you would like to remove.
   * @throws IllegalArgumentException if the amount to remove is less than 0 or more than the
   *                                  current amount.
   */
  public void removeAmount(double amountToRemove) throws IllegalArgumentException {
    if (amountToRemove <= 0 || amountToRemove >= this.amount) {
      throw new IllegalArgumentException("Please enter an amount between 0 and " + this.amount);
    }

    this.amount -= amountToRemove;
  }

  /**
   * Adds a defined amount to this {@link GroceryInstance}.
   *
   * @param amountToAdd a double of the amount that is to be added.
   */
  public void addAmount(double amountToAdd) {
    if (amountToAdd <= 0 || amountToAdd + this.amount > 999.9) {
      throw new IllegalArgumentException(
          "Cannot add an that amount, please add an amount between 0 and " + (999.9
              - this.amount));
    }

    this.amount += amountToAdd;
  }

  // Boolean methods

  /**
   * Checks weather or not this {@link GroceryInstance} is out of date.
   *
   * @return {@link Boolean} true or false that checks if the grocery is out of date.
   */
  public boolean isOutOfDate() {
    Date today = new Date();

    return this.getBestBeforeDate().before(today);
  }

  /**
   * Checks weather or not an entered {@link String} is a valid date.
   *
   * @param dateString the date given as a {@link String} in the format DD.MM.YYYY
   * @return {@link Boolean} true or false based on if the date is valid.
   */
  private boolean isValidDate(String dateString) {
    String[] dateParts = dateString.split("\\.");

    try {
      int day = Integer.parseInt(dateParts[0]);
      int month = Integer.parseInt(dateParts[1]);
      int year = Integer.parseInt(dateParts[2]);

      // if the different parts are as long as they should be and are in an amount that is
      // valid when it comes to date, it is a valid date.
      return
          (dateParts[0].length() == 2 && dateParts[1].length() == 2 && dateParts[2].length() == 4)
              &&
              (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 0 && year < 9999);

    } catch (Exception e) {
      // if it runs into an error when parsing the day, month and year, it isnt a valid date
      // because it probably contains a different datatype than integer.
      return false;
    }
  }

}
