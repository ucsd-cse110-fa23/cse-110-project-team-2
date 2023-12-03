package unitTests.client;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;

public class testRegen{
    @Test
    public void testRegen() throws IOException, InterruptedException, URISyntaxException{
        String mockIngredients = "Onion and brussel sprouts";
        String mockMealType = "Breakfast";
        mockGPT gpt = new mockGPT();
        String s = gpt.generate(mockIngredients, mockMealType);
        assertEquals(s, "regenerated");
    }
}
