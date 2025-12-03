package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;

public class ShopStateView {

    public void draw(Arena arena, GameHUD hud, TextGraphics g)  throws Exception {
        new ArenaView().draw(arena, g);
        new HUDview().draw(hud, arena, g);

        int width = 60;
        int height = 18;
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
        g.putString(x + 2, y + 4, "2. Rapid Tower (65g)");
        g.putString(x + 2, y + 5, "3. Sniper Tower (120g)");
        g.putString(x + 2, y + 6, "4. Bomb Tower (150g)");
        g.putString(x + 2, y + 7, "5. Frost Tower (80)");
        g.putString(x + 2, y + 8, "6. Tesla Tower (180)");



        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(x + 2, y + 16, "Press '1' to buy");
        g.putString(x + 2, y + 17, "Press ESC to cancel");
    }
}
