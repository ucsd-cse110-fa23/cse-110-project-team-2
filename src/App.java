import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Recorder record = new Recorder();
        record.startRecording();
        TimeUnit.SECONDS.sleep(10);
        record.stopRecording();

        Whisper whisper = new Whisper();
        whisper.transcribe(record.getRecordingFile());
    }
}
