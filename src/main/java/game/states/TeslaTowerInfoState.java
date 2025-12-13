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

public class TeslaTowerInfoState implements State {

    private final int x, y;

    public TeslaTowerInfoState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void drawShapePreview(TextGraphics g, Shape shape, int baseX, int baseY) {
        for (Pixel p : shape.getPixels()) {
            int dx = baseX + p.getDx();
            int dy = baseY + p.getDy();
            g.putString(dx, dy, String.valueOf(p.getChar()));
        }
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character) {
            char c = Character.toLowerCase(input.getCharacter());

            if (c == 'y') {

                int cost = 180;
                Shape shape = TowerFactory.getTeslaTowerShape();

                if (!context.getArena().isPlaceable(x, y, shape)) {
                    ShopState shop = new ShopState(x, y);
                    shop.setErrorMessage("Invalid placement!");
                    context.setState(shop);
                    return;
                }

                if (context.getArena().getGold() >= cost) {
                    SoundManager.getInstance().play("sfx_bought");
                    context.getArena().addTower(TowerFactory.createTeslaTower(x, y));
                    context.getArena().removeGold(cost);
                    context.setState(new PlayState());
                } else {
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
        g.putString(35, 5, " _____ _____ ____  _        _    ");
        g.putString(35, 6, "|_   _| ____/ ___|| |      / \\   ");
        g.putString(35, 7, "  | | |  _| \\___ \\| |     / _ \\  ");
        g.putString(35, 8, "  | | | |___ ___) | |___ / ___ \\ ");
        g.putString(35, 9, "  |_| |_____|____/|_____/_/   \\_\\");

        g.putString(70, 5, " _____   ___  __        __ _____  ____  ");
        g.putString(70, 6, "|_   _| / _ \\ \\ \\      / /| ____||  _ \\ ");
        g.putString(70, 7, "  | |  | | | | \\ \\ /\\ / / |  _|  | |_) |");
        g.putString(70, 8, "  | |  | |_| |  \\ V  V /  | |___ |  _ < ");
        g.putString(70, 9, "  |_|   \\___/     \\/\\/    |_____||_| \\_\\");

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(40, 13, "Damage: 2");
        g.putString(40, 14, "Range: 16");
        g.putString(40, 15, "Description:");
        g.putString(40, 16, "Experimental electrical weapon.");
        g.putString(40, 17, "Fires chain lightning that jumps");
        g.putString(40, 18, "between multiple targets.");

        g.putString(40, 24, "Special: Chain Lightning + Camo");
        g.putString(40, 25, "Cost: 180 gold");

        g.putString(118, 16, "Shape:");
        Shape preview = TowerFactory.getTeslaTowerShape();
        drawShapePreview(g, preview, 120, 20);

        g.setForegroundColor(TextColor.ANSI.GREEN);
        g.putString(40, 28, "Press Y to buy");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(40, 29, "Press N to return");
    }
}
