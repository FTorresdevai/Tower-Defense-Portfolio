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

public class BombTowerInfoState implements State {

    private final int x, y;

    public BombTowerInfoState(int x, int y) {
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
                int cost = 150;
                Shape shape = TowerFactory.getBombTowerShape();

                if (!context.getArena().isPlaceable(x, y, shape)) {
                    ShopState shop = new ShopState(x, y);
                    shop.setErrorMessage("Invalid placement!");
                    context.setState(shop);
                    return;
                }

                if (context.getArena().getGold() >= cost) {
                    SoundManager.getInstance().play("sfx_bought");
                    context.getArena().addTower(TowerFactory.createBombTower(x, y));
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
    public void draw(Game context, TextGraphics g) throws Exception {

        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(140, 44), ' ');

        g.setForegroundColor(TextColor.ANSI.RED);
        g.putString(35, 5, " ____   ___  __  __   ____ ");
        g.putString(35, 6, "| __ ) / _ \\|  \\/  | | __ )");
        g.putString(35, 7, "|  _ \\| | | | |\\/| | |  _ \\");
        g.putString(35, 8, "| |_) | |_| | |  | | | |_) | ");
        g.putString(35, 9, "|____/ \\___/|_|  |_| |____/");

        // ... TOWER ...
        g.putString(65, 5, " _____   ___  __        __ _____  ____  ");
        g.putString(65, 6, "|_   _| / _ \\ \\ \\      / /| ____||  _ \\ ");
        g.putString(65, 7, "  | |  | | | | \\ \\ /\\ / / |  _|  | |_) |");
        g.putString(65, 8, "  | |  | |_| |  \\ V  V /  | |___ |  _ < ");
        g.putString(65, 9, "  |_|   \\___/     \\/\\/    |_____||_| \\_\\");

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(40, 13, "Damage: 3");
        g.putString(40, 14, "Range: 5");
        g.putString(40, 15, "Description:");
        g.putString(40, 16, "A dependable explosive tower designed");
        g.putString(40, 17, "to handle clustered waves of enemies.");
        g.putString(40, 18, "Its powerful blasts strike multiple");
        g.putString(40, 19, "targets at once — perfect crowd control.");
        g.putString(40, 24, "Special: Explodes on hit (radius 2)");
        g.putString(40, 25, "Cost: 150 gold");

        g.putString(118, 16, "Shape:");
        Shape bombShape = TowerFactory.getBombTowerShape();
        drawShapePreview(g, bombShape, 120, 20);

        g.setForegroundColor(TextColor.ANSI.GREEN);
        g.putString(40, 28, "Press Y to buy");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(40, 29, "Press N to return");
    }
}
