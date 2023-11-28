package client;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;

public class testImgGen{
    @Test
    public void testRegen() throws IOException, InterruptedException, URISyntaxException{
        String mockTitle = "Chicken Omlette";
        mockGPT gpt = new mockDallE();
        String s = gpt.generate(mockTitle);
        assertEquals(s, "regenerated");
    }
}