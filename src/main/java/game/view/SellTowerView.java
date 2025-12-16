package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.GameHUD;

public class SellTowerView {

    private final int sellValue;

    public SellTowerView(int sellValue) {
        this.sellValue = sellValue;
    }

    public void draw(Arena arena, GameHUD hud, TextGraphics g) throws Exception {
        new ArenaView().draw(arena, g);
        new HUDview().draw(hud, arena, g);

        int width = 50;
        int height = 9;
        int x = (arena.getWidth() - width) / 2;
        int y = arena.getHeight() / 3;

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
        center.accept(y + 1, "--- SELL TOWER ---");

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        center.accept(y + 3, "You will receive " + sellValue + " gold");

        g.setForegroundColor(TextColor.ANSI.GREEN);
        center.accept(y + 5, "[Y] Confirm");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        center.accept(y + 6, "[N] Cancel");
    }
}
