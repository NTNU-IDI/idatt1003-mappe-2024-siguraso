package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;


class TestRecipe {

  Recipe recipe;

  @BeforeEach
  void setUp() {
    recipe = new Recipe("name", "description", new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>());
  }


}