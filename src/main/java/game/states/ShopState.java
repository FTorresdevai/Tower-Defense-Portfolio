package game.states;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.TowerFactory;

public class ShopState implements State {

    private int targetX;
    private int targetY;

    public ShopState(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') {
            buyBasicTower(context);
        }

        if (input.getKeyType() == KeyType.Escape) {
            context.setState(new PlayState());
        }
    }

    @Override
    public void update(Game context) throws Exception {
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        context.getArena().draw(g);

        int width = 30;
        int height = 10;
        int x = (context.getArena().getWidth() - width) / 2;
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

    private void buyBasicTower(Game context) {
        int cost = 50;

        if (context.getArena().getGold() >= cost) {
            context.getArena().addTower(TowerFactory.createBasicTower(targetX, targetY));
            context.getArena().removeGold(cost);
            context.setState(new PlayState());
        } else {
            context.getHUD().showMessage("Sem Ouro Suficiente!");
            context.setState(new PlayState());
        }
    }
}