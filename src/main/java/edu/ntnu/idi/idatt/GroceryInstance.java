package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GroceryInstance {

  // Object variables
  private final Grocery grocery;
  private double amount;
  private final String bestBefore;

  // Constructor
  GroceryInstance(Grocery grocery, double amount, String bestBefore) {
    this.grocery = grocery;
    this.amount = amount;
    this.bestBefore = bestBefore;
  }

  // Get-methods
  public String getName() {
    return this.grocery.getName();
  }

  public String getMeasurementUnit() {
    return this.grocery.getMeasurementUnit();
  }

  public double getAmount() {
    return this.amount;
  }

  /**
   * Method for displaying the date as an instance of String. Useful when trying to display the best
   * before date.
   */
  public String getBestBeforeString() {
    return this.bestBefore;
  }

  /**
   * Method for returning the date as an instance of Date. Useful when comparing the current date
   * and the best before date.
   *
   * @throws ParseException The method throws a ParseException in case the given BestBefore String
   *                        is in an invalid date format (only "DD-MM-YYYY"`) is accepted).
   */
  public Date getBestBeforeDate() throws ParseException {
    // Creates a dateformat dd-MM-yyyy
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    // Parses this instance's bestBefore String object to a Date object.
    return format.parse(this.bestBefore);
  }

  // Set-methods
  public void setAmount(double amount) {
    this.amount = amount;
  }
}
