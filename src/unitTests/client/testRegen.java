package client;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;

public class testRegen extends ChatGPT{
    @Override
    public String generate(String ingredients, String mealType) {return "regenerated";}
    
    @Test
    public void regenTest() throws IOException{
        String mockIngredients = "Onion and brussel sprouts";
        String mockMealType = "Breakfast";
        ChatGPT gpt = new ChatGPT();
        String s = gpt.generate(mockIngredients, mockMealType);
        assertTrue(s.equals("regenerated"));
    }
}
