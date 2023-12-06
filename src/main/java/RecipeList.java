
import java.util.Date;

import javafx.scene.layout.VBox;

public class RecipeList extends VBox{
    private boolean namesAscending = false;
    private boolean datesAscending = false;

    RecipeList() {
        this.setSpacing(5); // sets spacing between Recipes
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void deleteRecipe(Recipe recipe) {
        for(int i = 0; i < this.getChildren().size(); i++){
            Recipe current = ((Recipe) this.getChildren().get(i));
            if(current == recipe) {
                this.getChildren().remove(i);
                break;
            }
        }
    }

    public void filterRecipes(String type) {
        removeFilter();
        for(int i = 0; i < this.getChildren().size(); i++) {
            Recipe current = ((Recipe) this.getChildren().get(i));
            if(!type.equals("None")) {
                if(!current.getMealType().equals(type)) {
                    current.setVisible(false);
                    current.managedProperty().bind(current.visibleProperty());
                }
            }
            else {
                removeFilter();
            }
        }
    }

    public void removeFilter() {
        for(int i = 0; i < this.getChildren().size(); i++) {
            Recipe current = ((Recipe) this.getChildren().get(i));
            current.setVisible(true);
        }
    }

    public void sortRecipesByName() {
        int max;
        for (int i = 0; i < this.getChildren().size(); i++) {
            max = i;
            for(int j = i; j < this.getChildren().size(); ++j) {
                String r1 = ((Recipe)this.getChildren().get(max)).getRecipeTitle();
                String r2 = ((Recipe)this.getChildren().get(j)).getRecipeTitle();
                if(namesAscending) {
                    if(r1.compareToIgnoreCase(r2) < 0) {
                        max = j;
                    }
                }
                else {
                    if(r1.compareToIgnoreCase(r2) > 0) {
                        max = j;
                    }
                }
            }
            this.getChildren().get(max).toBack();
        }
        namesAscending = !namesAscending;
    }

    public void sortRecipesByDate() {
        int max;
        for (int i = 0; i < this.getChildren().size(); i++) {
            max = i;
            for(int j = i; j < this.getChildren().size(); ++j) {
                Date r1 = ((Recipe)this.getChildren().get(max)).getDate();
                Date r2 = ((Recipe)this.getChildren().get(j)).getDate();
                if(datesAscending) {
                    if(r1.compareTo(r2) < 0) {
                        max = j;
                    }
                }
                else {
                    if(r1.compareTo(r2) > 0) {
                        max = j;
                    }
                }
            }
            this.getChildren().get(max).toBack();
        }
        datesAscending = !datesAscending;
    }
}
