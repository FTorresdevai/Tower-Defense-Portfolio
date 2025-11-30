package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;

public class ShopStateView {

    public void draw(Arena arena, GameHUD hud, TextGraphics g)  throws Exception {
        new ArenaView().draw(arena, g);
        new HUDview().draw(hud, arena, g);

        int width = 30;
        int height = 10;
        int x = (arena.getWidth() - width) / 2;
        int y = 5;

        g.setBackgroundColor(TextColor.ANSI.BLACK);
        g.setForegroundColor(TextColor.ANSI.WHITE);

        for (int i = 0; i < height; i++) {
            g.putString(x, y + i, " ".repeat(width));
        }

        g.setForegroundColor(TextColor.ANSI.RED);
        g.putString(x + 2, y + 1, "--- TOWER STORE ---");

        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(x + 2, y + 3, "1. Basic Tower (50g)");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(x + 2, y + 8, "Press '1' to buy");
        g.putString(x + 2, y + 9, "Press ESC to cancel");
    }
}
