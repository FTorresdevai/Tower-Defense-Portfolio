package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;
import game.states.ShopState;

public class ShopStateView {

    public void draw(Arena arena, GameHUD hud, ShopState shopState, TextGraphics g) throws Exception {
        new ArenaView().draw(arena, g);
        new HUDview().draw(hud, arena, g);

        int width = 60;
        int height = 18;
        int x = (arena.getWidth() - width) / 2;
        int y = 5;

        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.setForegroundColor(TextColor.ANSI.WHITE);

        for (int i = 0; i < height; i++) {
            g.putString(x, y + i, " ".repeat(width));
        }

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(x, y, "┌" + "─".repeat(width - 2) + "┐");

        for (int i = 1; i < height - 1; i++) {
            g.putString(x, y + i, "│" + " ".repeat(width - 2) + "│");
        }

        g.putString(x, y + height - 1, "└" + "─".repeat(width - 2) + "┘");

        java.util.function.BiConsumer<Integer, String> center = (yy, text) -> {
            int xx = x + (width - text.length()) / 2;
            g.putString(xx, yy, text);
        };

        g.setForegroundColor(TextColor.ANSI.RED);
        center.accept(y + 1, "--- TOWER STORE ---");

        g.setForegroundColor(TextColor.ANSI.WHITE);
        center.accept(y + 3, "1. Basic Tower (50g)");
        center.accept(y + 5, "2. Rapid Tower (65g)");
        center.accept(y + 7, "3. Sniper Tower (120g)");
        center.accept(y + 9, "4. Bomb Tower (150g)");
        center.accept(y + 11, "5. Frost Tower (80g)");
        center.accept(y + 13, "6. Tesla Tower (180g)");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        center.accept(y + 16, "Press 1–6 to buy | Press ESC to cancel");

        if (shopState.getErrorMessage() != null) {
            g.setForegroundColor(TextColor.ANSI.RED);
            center.accept(y + 15, shopState.getErrorMessage());
        }
    }


}
