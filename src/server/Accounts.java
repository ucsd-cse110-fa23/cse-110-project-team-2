package server;

import java.util.*;
import java.io.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Accounts {
    private HashMap<String,String> accounts;
    Accounts(File accountsJSON){
        //Code taken from Dirk Schumacher on Stack Overflow
        /*
         https://stackoverflow.com/questions/7463414/what-s-the-best-way-to-load-a-jsonobject-from-a-json-text-file
         */
        JsonElement json = null;
        try (Reader reader = new InputStreamReader(new FileInputStream(accountsJSON), "UTF-8")) {
            json = JsonParser.parseReader( reader );
            JsonObject accounts = json.getAsJsonObject();
            accounts.getAsJsonArray("accounts");
        } catch (Exception e) {
            accounts = new HashMap<String,String>();
        }
    }
}
