package game.states;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.*;
import game.Game;
import game.audio.SoundManager;
import game.model.Pixel;
import game.model.Shape;
import game.model.factories.TowerFactory;

public class SniperTowerInfoState implements State {

    private final int x, y;

    public SniperTowerInfoState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void drawShapePreview(TextGraphics g, Shape shape, int baseX, int baseY) {
        for (Pixel p : shape.getPixels()) {
            int drawX = baseX + p.getDx();
            int drawY = baseY + p.getDy();
            g.putString(drawX, drawY, String.valueOf(p.getChar()));
        }
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character) {

            char c = Character.toLowerCase(input.getCharacter());

            if (c == 'y') {

                int cost = 120;
                Shape shape = TowerFactory.getSniperTowerShape();

                if (!context.getArena().isPlaceable(x, y, shape)) {
                    ShopState shop = new ShopState(x, y);
                    shop.setErrorMessage("Invalid placement!");
                    context.setState(shop);
                    return;
                }

                if (context.getArena().getGold() >= cost) {
                    SoundManager.getInstance().play("sfx_bought");
                    context.getArena().addTower(TowerFactory.createSniperTower(x, y));
                    context.getArena().removeGold(cost);
                    context.setState(new PlayState());
                }
                else {
                    ShopState shop = new ShopState(x, y);
                    shop.setErrorMessage("Not enough gold!");
                    context.setState(shop);
                }
            }

            if (c == 'n') {
                context.setState(new ShopState(x, y));
            }
        }
    }

    @Override
    public void update(Game context) {}

    @Override
    public void draw(Game context, TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(140, 44), ' ');

        g.setForegroundColor(TextColor.ANSI.RED);
        g.putString(35, 5, " ____  _   _ ___ ____  _____ ____  ");
        g.putString(35, 6, "/ ___|| \\ | |_ _|  _ \\| ____|  _ \\ ");
        g.putString(35, 7, "\\___ \\|  \\| || || |_) |  _| | |_) |");
        g.putString(35, 8, " ___) | |\\  || ||  __/| |___|  _ < ");
        g.putString(35, 9, "|____/|_| \\_|___|_|   |_____|_| \\_\\");

        g.putString(75, 5, " _____   ___  __        __ _____  ____  ");
        g.putString(75, 6, "|_   _| / _ \\ \\ \\      / /| ____||  _ \\ ");
        g.putString(75, 7, "  | |  | | | | \\ \\ /\\ / / |  _|  | |_) |");
        g.putString(75, 8, "  | |  | |_| |  \\ V  V /  | |___ |  _ < ");
        g.putString(75, 9, "  |_|   \\___/     \\/\\/    |_____||_| \\_\\");

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(40, 13, "Damage: 6");
        g.putString(40, 14, "Range: 50");
        g.putString(40, 15, "Description:");
        g.putString(40, 16, "Infinite range specialist.");
        g.putString(40, 17, "Deals massive damage but fires slowly.");
        g.putString(40, 18, "Ideal for picking off strong enemies.");

        g.putString(40, 24, "Special: Detects Camo Enemies");
        g.putString(40, 25, "Cost: 120 gold");

        g.putString(118, 16, "Shape:");
        Shape preview = TowerFactory.getSniperTowerShape();
        drawShapePreview(g, preview, 120, 20);

        g.setForegroundColor(TextColor.ANSI.GREEN);
        g.putString(40, 28, "Press Y to buy");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(40, 29, "Press N to return");
    }
}
