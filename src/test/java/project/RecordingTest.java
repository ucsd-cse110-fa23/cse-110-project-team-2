package project;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.io.File;

import javax.sound.sampled.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecordingTest {
    @Test
    void testRecordingMade() throws InterruptedException{
        Recorder r = new Recorder();
        File file = new File("recording.wav");
        r.startRecording();
        Thread.sleep(5000);
        r.stopRecording();
        assertTrue(file.exists());

    }
}
