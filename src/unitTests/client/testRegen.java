package client;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;

public class testRegen extends ChatGPT{
    @Override
    public String generate(String ingredients, String mealType) throws IOException {throw new IOException();}
    
    @Test
    (expected=IOException.class)
    public void regenTest() throws IOException, InterruptedException, URISyntaxException{
        String mockIngredients = "Onion and brussel sprouts";
        String mockMealType = "Breakfast";
        ChatGPT gpt = new ChatGPT();
        gpt.generate(mockIngredients, mockMealType);
    }
}
