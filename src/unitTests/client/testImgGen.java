package client;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;
import javafx.scene.image.Image;

public class testImgGen{
    private File imageFile;
    @Test
    public void testRegen() throws IOException, InterruptedException, URISyntaxException{
        String mockTitle = "Chicken Omlette";
        mockDallE dallE = new mockDallE();
        dallE.image(mockTitle);
        imageFile = new File("src/main/client/recipeImage.png");
        assertTrue(imageFile.exists());
    }
}