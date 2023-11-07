public class Test {
    public static void main(String[] args) throws Exception{
        Recorder r = new Recorder();
        r.startRecording();
        Thread.sleep(5000);
        r.stopRecording();
    }
}
