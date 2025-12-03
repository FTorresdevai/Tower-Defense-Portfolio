package game.audio;
import javax.sound.sampled.AudioFormat;

public class SoundData {
    byte[] audioBytes;
    AudioFormat format;

    public SoundData(byte[] audioBytes, AudioFormat format) {
        this.audioBytes = audioBytes;
        this.format = format;
    }
}
