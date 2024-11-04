package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dialog;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt.Interface.DialogOptionCreator;

import java.util.Date;
import java.text.SimpleDateFormat;

class DialogOptionCreatorTest {

  @Test
  void isValidDate() throws ParseException {
    assertTrue(DialogOptionCreator.isValidDate("03.03.2003"));
  }

  @Test
  void yesNoOption() {
  }

  @Test
  void validGroceryTypeIndex() {
  }

  @Test
  void validGroceryInstanceIndex() {
  }

  @Test
  void validMeasurementUnitOption() {
  }

  @Test
  void validTypeNameOption() {
  }

  @Test
  void validPricePerUnitOption() {
  }

  @Test
  void validAmountOption() {
  }

  @Test
  void validBestBeforeDateOption() {
  }
}