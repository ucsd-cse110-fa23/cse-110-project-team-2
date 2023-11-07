package test;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.io.File;

import javax.sound.sampled.*;

import static org.junit.jupiter.api.*;


public class RecordingTest {
    @Test
    void testRecordingMade(){
        Recorder r = new Recorder();
        File file = new File("recording.wav");
        r.startRecording();
        r.stopRecording();
        assertTrue(file.exists());

    }
}
