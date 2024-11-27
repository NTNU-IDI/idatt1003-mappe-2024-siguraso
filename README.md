# Portfolio project IDATT1003

STUDENT NAME = "Sigurd Andris Sørengen"
STUDENT ID = "Your ID"

## Project description

This projects was developed as the end assignment used in for the programming course IDATT1003 at NTNU-Trondheim. The FoodWaste app is more or less a utility that helps people keep track of their current food situation (e.g. what food they have at hand), as well as incuding some numerous other features:

**The main features of the app are:**
- A food storage to keep track of the food the user has at hand
  * The food storage keeps track of how much of each grocery is available, their expiration date & more.
- A cookbook for recipes
  * In the cookbook you can add a recipe for a dish of your choice.
    * The cookbook keeps track of what food you have in the food storage, and can tell you if you can or can't make the dish with the food the user has at hand. This way the program can also make suggestions for what recipes to make.
    * You can choose weather or not to include food that is past it's best before date, further helping in the fight against food waste.
   
**Instances of the GroceryInstance class contain the following information:**
- The name of the grocery and the measurement unit most often associated with this type of grocery.
- How much you have of this grocery
- The best before (expiration) date of the given grocery
- The price of the grocery per unit

**Instances of the Recipe class contain the following information**
- The ingredients of the recipe, including approximations for how much of each to use in the recipe.
- A short description of the recipe
- Instructions for how to make the recipe

## Project structure

All source files for classes are stored in [src/main/java/edu/ntnu/idi/idatt](https://github.com/NTNU-IDI/idatt1003-mappe-2024-siguraso/tree/main/src/main/java/edu/ntnu/idi/idatt) where they then split up into separate packages. The models package includes the base classes for groceries as well as recipes, the utils package contains UI utilities,  mostly used throughout the UserInterface class (used to create all text-based menus), which is stored in views. The main class, here titled 'FoodWasteApp' is stored in the root of the [src/main/java/edu/ntnu/idi/idatt](https://github.com/NTNU-IDI/idatt1003-mappe-2024-siguraso/tree/main/src/main/java/edu/ntnu/idi/idatt) directory.

The tests for each of the model classes are stored in [src/test/java/edu/ntnu/idi/idatt/models](https://github.com/NTNU-IDI/idatt1003-mappe-2024-siguraso/tree/main/src/test/java/edu/ntnu/idi/idatt/models).

## How to run the project

1. Download the source code
2. Run the file "FoodWasteApp.java" (make sure to have Java installed!) stored within src/main/java/edu/ntnu/idi/idatt using the file explorer of your choice.
3. The program will prompty open in a terminal window.

(Alternatively you can run the file directly through the terminal.)

## How to run the tests

*Coming soon!*

## References

*Coming soon!*

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
