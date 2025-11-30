package game.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static SoundManager instance;
    private final Map<String, Clip> sounds = new HashMap<>();

    private SoundManager(){

    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void loadAssetAsync(String name, String link) {
        new Thread(() -> {
            try {
                URL url = getClass().getResource(link);
                if (url == null) {
                    System.err.println("ERROR: Sound not found: " + link);
                    return;
                }
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                sounds.put(name, clip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void play(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
}