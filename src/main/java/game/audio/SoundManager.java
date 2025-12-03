package game.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static SoundManager instance;

    private final Clip[] channels = new Clip[5];
    private final Map<String, SoundData> soundBuffers = new HashMap<>();

    private SoundManager() {
        initChannels();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void initChannels() {
        for (int i = 0; i < channels.length; i++) {
            try {
                channels[i] = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadAssetAsync(String name, String path) {
        new Thread(() -> {
            try {
                InputStream is = getClass().getResourceAsStream(path);
                if (is == null) return;

                InputStream bufferedIn = new BufferedInputStream(is);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = audioStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();

                soundBuffers.put(name, new SoundData(buffer.toByteArray(), audioStream.getFormat()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void play(String name) {
        play(name, 0.0f);
    }

    public void play(String name, float dbReduction) {
        SoundData data = soundBuffers.get(name);
        if (data == null) return;

        Clip freeClip = null;
        for (Clip clip : channels) {
            if (clip != null && !clip.isRunning()) {
                freeClip = clip;
                break;
            }
        }

        if (freeClip != null) {
            try {
                if (freeClip.isOpen()) freeClip.close();
                freeClip.open(data.format, data.audioBytes, 0, data.audioBytes.length);

                if (freeClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl gainControl = (FloatControl) freeClip.getControl(FloatControl.Type.MASTER_GAIN);
                    float newVolume = Math.max(dbReduction, gainControl.getMinimum());
                    newVolume = Math.min(newVolume, gainControl.getMaximum());
                    gainControl.setValue(newVolume);
                }

                freeClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}