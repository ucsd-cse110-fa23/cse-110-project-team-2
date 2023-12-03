package client;


import javafx.scene.layout.VBox;

public class RecipeList extends VBox {
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
            if(type != "None") {
                if(current.getMealType() != type) {
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
}
