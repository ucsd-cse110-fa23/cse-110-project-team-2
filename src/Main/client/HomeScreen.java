package client;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class HomeScreen extends Screen {
    private RecipeDisplay recipeDisplay;
    private FilterSortButtons fsButtons;

    HomeScreen() {
        super();
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", this::changeNextScreenEvent);
        recipeDisplay = new RecipeDisplay();
        fsButtons = new FilterSortButtons();
        this.setTop(fsButtons);
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

class FilterSortButtons extends HBox {
    private ComboBox<String> filters;
    private Button sortButtonName;
    private boolean namesAscending = false;
    private Button sortButtonDate;
    private boolean datesAscending = false;
    
    FilterSortButtons() {
        filters = new ComboBox<String>();
        filters.getItems().addAll("None", "Breakfast", "Lunch", "Dinner");
        filters.setPromptText("Filter Recipes");
        sortButtonName = new Button("Name");
        sortButtonDate = new Button("Date");
        this.getChildren().addAll(filters, sortButtonName, sortButtonDate);
        this.setAlignment(Pos.CENTER);
        addListeners();
    }

    public void addListeners() {
        filters.setOnAction(e -> AppFrame.recipeList.filterRecipes(filters.getValue()));
        sortButtonName.setOnAction(e -> {
            AppFrame.recipeList.sortRecipesByName();
            if(!namesAscending) {
                sortButtonName.setText("Name ↑");
                namesAscending = true;
            }
            else {
                sortButtonName.setText("Name ↓");
                namesAscending = false;
            }
        });
        sortButtonDate.setOnAction(e -> {
            AppFrame.recipeList.sortRecipesByDate();
            if(!datesAscending) {
                sortButtonDate.setText("Date ↑");
                datesAscending = true;
            }
            else {
                sortButtonDate.setText("Date ↓");
                datesAscending = false;
            }
        });
    }
}
