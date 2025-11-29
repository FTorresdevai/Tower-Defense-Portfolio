package game;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameHUD implements Observer {

    private final Arena arena;

    private int gold;
    private int lives;
    private int wave;

    public GameHUD(Arena arena) {
        this.arena = arena;
        this.arena.attach(this);
        update();
    }

    @Override
    public void update() {
        gold = arena.getGold();
        lives = arena.getLives();
        wave = arena.getWave();
    }

    public void draw(TextGraphics g){
        drawStats(g);
        drawControls(g);
    }

    public void drawStats(TextGraphics g) {
        g.putString(2, 0, "Gold: " + gold);
        g.putString(15, 0, "Lives: " + lives);
        g.putString(30, 0, "Wave: " + wave);
    }

    public void drawControls(TextGraphics g) {
        List<Integer> msgs_length = new ArrayList<Integer>();
        List<String> msgs = new ArrayList<>();

        String msg1 = "P->Pause";
        msgs_length.add(msg1.length());
        msgs.add(msg1);

        String msg2 = "Q->Quit";
        msgs_length.add(msg2.length());
        msgs.add(msg2);

        String msg3 = "B->Build Tower";
        msgs_length.add(msg3.length());
        msgs.add(msg3);

        String msg4 = "U->Undo Tower";
        msgs_length.add(msg4.length());
        msgs.add(msg4);

        int maxLen = Collections.max(msgs_length);
        int x = arena.getWidth() - maxLen - 2;

        int y = 0;
        for (String m : msgs) {
            g.putString(x,y,m);
            y += 1;
        }
    }
}