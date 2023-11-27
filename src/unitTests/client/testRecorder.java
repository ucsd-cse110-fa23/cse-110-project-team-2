package client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class testRecorder {

    private Recorder recorder;

    @Before
    public void setUp() {
        recorder = new Recorder();
    }

    @After
    public void tearDown() {
        recorder = null;
    }

    @Test
    public void testStartAndStopRecording() {
        recorder.startRecording();
        //record something for 2 secs
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recorder.stopRecording();

        File recordingFile = recorder.getRecordingFile();
        assertTrue("Recording file should not be null", recordingFile.exists());
        assertTrue("Recording file should have a non-zero size", recordingFile.length() > 0);
    }
}