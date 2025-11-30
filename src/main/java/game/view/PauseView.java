package game.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;

public class PauseView {
    public void draw(Arena arena, GameHUD hud, TextGraphics g) throws Exception {
        new ArenaView().draw(arena, g);
        new HUDview().draw(hud, arena, g);

        int width = arena.getWidth();
        int height = arena.getHeight();

        String msg1 = "=== PAUSED ===";
        String msg2 = "Press 'r' to resume";
        String msg3 = "Press 'q' to quit";

        int x1 = (width - msg1.length()) / 2;
        int y1 = height / 3;

        int x2 = (width - msg2.length()) / 2;
        int y2 = y1 + 2;

        int x3 = (width - msg3.length()) / 2;
        int y3 = y2 + 2;

        g.putString(x1, y1, msg1);
        g.putString(x2, y2, msg2);
        g.putString(x3, y3, msg3);
    }
}
