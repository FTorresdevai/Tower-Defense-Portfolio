package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HUDview {

    public void draw(GameHUD hud, Arena arena, TextGraphics g) {
        drawStats(hud, g);
        drawControls(arena, g);
        drawMessage(hud, arena, g);
        drawWavePrompt(arena, g);
    }

    public void drawStats(GameHUD hud, TextGraphics g) {
        g.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        g.putString(2, 0, "Gold: " + hud.getGold());

        g.setForegroundColor(TextColor.Factory.fromString("#FF6666"));
        g.putString(15, 0, "Lives: " + hud.getLives());

        g.setForegroundColor(TextColor.Factory.fromString("#AAAAFF"));
        g.putString(30, 0, "Wave: " + hud.getWave());

        g.setForegroundColor(TextColor.ANSI.WHITE);
    }

    public void drawControls(Arena arena, TextGraphics g) {
        g.setForegroundColor(TextColor.Factory.fromString("#AAB2D5"));
        List<Integer> msgs_length = new ArrayList<Integer>();
        List<String> msgs = new ArrayList<>();

        String msg1 = "P->Pause";
        msgs_length.add(msg1.length());
        msgs.add(msg1);

        String msg2 = "I->Enemy Info";
        msgs_length.add(msg2.length());
        msgs.add(msg2);

        String msg3 = "Q->Quit";
        msgs_length.add(msg3.length());
        msgs.add(msg3);

        String msg4 = "B->Buy Tower(store)";
        msgs_length.add(msg4.length());
        msgs.add(msg4);

        String msg5 = "U->Undo Tower";
        msgs_length.add(msg5.length());
        msgs.add(msg5);

        int maxLen = Collections.max(msgs_length);
        int x = arena.getWidth() - maxLen - 2;

        int y = 0;
        for (String m : msgs) {
            g.putString(x,y,m);
            y += 1;
        }
        g.setForegroundColor(TextColor.ANSI.WHITE);
    }

    public void drawMessage(GameHUD hud, Arena arena, TextGraphics g){
        if (hud.getMessageTimer() > 0) {
            g.setForegroundColor(TextColor.Factory.fromString("#FF4444"));
            int width = arena.getWidth();
            int x = (width - hud.getCurrentMessage().length()) / 2;
            int y = 5;
            g.putString(x, y, hud.getCurrentMessage());
            g.setForegroundColor(TextColor.ANSI.WHITE);
        }
    }

    private void drawWavePrompt(Arena arena, TextGraphics g) {
        if (arena.isWaitingForPlayer()) {
            String msg = "PRESS SPACE TO START WAVE";
            int x = (arena.getWidth() - msg.length()) / 2;
            int y = 3;

            g.setForegroundColor(TextColor.Factory.fromString("#00DDFF"));
            g.putString(x, y, msg);
            g.setForegroundColor(TextColor.ANSI.WHITE);
        }
    }
}
