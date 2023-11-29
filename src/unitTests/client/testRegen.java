package client;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;
import javafx.util.Pair;

public class testRegen extends mockRegen{
    @Test
    public void testReg() throws IOException, InterruptedException, URISyntaxException{
        String mockIngredients = "Onion and brussel sprouts";
        String mockMealType = "Breakfast";
        mockRegen regen= new mockRegen();
        Pair<String,String> actual = regen.regen(mockIngredients, mockMealType);
        Pair<String,String> expected = new Pair<String,String>("Chicken and egg scramble", "Scramble eggs with chicken on pan");
        assertEquals(expected, actual);
    }
}
