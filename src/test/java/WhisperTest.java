package test.java;
import java.io.*;
import java.net.*;
import org.json.*;

import java.io.File;

import javax.sound.sampled.*;

import static org.junit.jupiter.api.*;

public class WhisperTest {
    @Test
    void testTranscribeWorking(){
        MockWhisper w = new MockWhisper();
        Recorder r = new Recorder();
        File f = new File("recording.wav");
        r.startRecording();
        r.stopRecording();
        String s = w.transcribe(f);
        assertTrue(s.equals("Working"));
    }
}
