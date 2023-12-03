package client;

import javafx.scene.control.ComboBox;

public class HomeScreen extends Screen {
    private RecipeDisplay recipeDisplay;
    private ComboBox<String> filters;

    HomeScreen() {
        super();
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", this::changeNextScreenEvent);
        recipeDisplay = new RecipeDisplay();
        filters = new ComboBox<String>();
        filters.getItems().addAll("None", "Breakfast", "Lunch", "Dinner");
        filters.setPromptText("Filter Recipes");
        filters.setOnAction(e -> AppFrame.recipeList.filterRecipes(filters.getValue()));
        this.setTop(filters);
        this.setCenter(recipeDisplay);
    }

    public RecipeDisplay getRecipeDisplay() {
        return this.recipeDisplay;
    }

    @Override
    protected Screen createNextScreen() {
        return new PromptScreen();
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }
}
