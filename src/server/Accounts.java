package server;

import java.util.*;
import java.io.*;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Accounts {
    //all the user account : password pairs in one JSON
    private JSONObject userPw; 
    //separate arrays key'd by username, arrays contain a singular JSONObject containing all recipe-title:recipe pairs for that account
    private JSONObject allRecipes; 
    Accounts(){
        userPw = null;
        allRecipes = null;
    }

    public void loaduserPW(File userPw){ //loads user:password pairs from json file
        try{
            this.userPw = new JSONObject(Files.readString(userPw.toPath()));
        }catch(FileNotFoundException e){
            this.userPw = new JSONObject();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadallRecipes(File allRecipes){ //loads allreacipes from json file
        try{
            this.allRecipes = new JSONObject(Files.readString(allRecipes.toPath()));
        }catch(FileNotFoundException e){
            this.allRecipes = new JSONObject();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeToFilePw() throws IOException{ //writes jsonObjects to Json files
        File f = new File("userPw.json");
        FileOutputStream fw = new FileOutputStream(f);
        OutputStreamWriter writer = new OutputStreamWriter(fw);
        writer.write(this.userPw.toString());
        writer.flush();
        writer.close();
    }

    public void writeToFileRecipes() throws IOException{ //writes jsonObjects to Json files
        File f = new File("allRecipes.json");
        FileOutputStream fw = new FileOutputStream(f);
        OutputStreamWriter writer = new OutputStreamWriter(fw);
        writer.write(this.allRecipes.toString());
        writer.flush();
        writer.close();
    }

    public boolean checkLogin(String username, String password){ //checks if login is valid
        return password.equals(userPw.getString(username));
    }

    public String getUserRecipes(String username){
        return ((JSONObject)allRecipes.getJSONArray(username).get(0)).toString();
    }

}
