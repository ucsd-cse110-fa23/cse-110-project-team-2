
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessLogic {
    private ChatGPT cgpt;
    private Whisper wspr;
    //private HashMap<String,String> accounts;
    private Accounts accounts;
    private DallE dallE;
    public BusinessLogic(){
        this.cgpt = new ChatGPT();
        this.wspr = new Whisper();
        //this.accounts = new HashMap<String,String>();
        this.accounts = new Accounts();
        this.dallE = new DallE();
    }

    //Generates using chatgpt
    public String generate(String ingredients, String mealtype) throws IOException, InterruptedException, URISyntaxException{
        return cgpt.generate(ingredients, mealtype);
    }

    public String generateTitle(String ingredients, String mealtype) throws IOException, InterruptedException, URISyntaxException{
        return cgpt.generateTitle(ingredients, mealtype);
    }
    //Transcribes using whisper
    public String transcribe(File recording) throws IOException, URISyntaxException, JSONException {
        return wspr.transcribe(recording);
    }

    public void generateImage(String title) throws IOException, URISyntaxException, JSONException, InterruptedException {
        dallE.image(title);
    }

    public boolean checkLogin(String username, String password){
        return accounts.checkLogin(username, password);
    }

    public boolean addUserAccount(String username, String password) throws IOException{
        return accounts.addUserAccount(username, password);
    }

    public boolean deleteRecipe(String username, String recipeTitleDate){
        return accounts.deleteRecipeFromAccount(username, recipeTitleDate);
    }

    public JSONObject getAllRecipes(String username){
        return accounts.getUserRecipes(username);
    }

    public boolean saveRecipeToAccount(String username, JSONObject recipe){
        System.out.println("What am I saving?");
        System.out.println(username);
        System.out.println(recipe.toString());
        return accounts.saveRecipeToAccount(username, recipe);
    }

}
