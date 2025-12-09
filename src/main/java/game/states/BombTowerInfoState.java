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
                if (context.getArena().getGold() >= cost) {
                    SoundManager.getInstance().play("sfx_bought");
                    context.getArena().addTower(TowerFactory.createBombTower(x, y));
                    context.getArena().removeGold(cost);
                    context.setState(new PlayState());
                } else {
                    context.getHUD().showMessage("Not enough gold!");
                    context.setState(new ShopState(x, y));
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


        g.setBackgroundColor(TextColor.Factory.fromString("#111133")); // dark blue
        g.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(140, 44), ' ');


        g.setForegroundColor(TextColor.Factory.fromString("#FF5555")); // red
        g.setForegroundColor(TextColor.ANSI.RED);
        g.putString(35, 5, " ____   ___  __  __   ____ ");
        g.putString(35, 6, "| __ ) / _ \\|  \\/  | | __ )");
        g.putString(35, 7, "|  _ \\| | | | |\\/| | |  _ \\");
        g.putString(35, 8, "| |_) | |_| | |  | | | |_) | ");
        g.putString(35, 9, "|____/ \\___/|_|  |_| |____/");
        g.putString(65, 5, " _____   ___  __        __ _____  ____  ");
        g.putString(65, 6, "|_   _| / _ \\ \\ \\      / /| ____||  _ \\ ");
        g.putString(65, 7, "  | |  | | | | \\ \\ /\\ / / |  _|  | |_) |");
        g.putString(65, 8, "  | |  | |_| |  \\ V  V /  | |___ |  _ < ");
        g.putString(65, 9, "  |_|   \\___/     \\/\\/    |_____||_| \\_\\");



        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(40, 13,  "Damage: 3");
        g.putString(40, 14, "Range: 5");
        g.putString(40, 15, "Description:");
        g.putString(40, 16, "A dependable explosive tower designed");
        g.putString(40, 17, "to handle clustered waves of enemies.");
        g.putString(40, 18, "Its powerful blasts strike multiple");
        g.putString(40, 19, "targets at once, making it perfect for");
        g.putString(40, 20, "crowd control when the path is full.");
        g.putString(40, 21, "Reliable, destructive, and always");
        g.putString(40, 22, "satisfying to use.");
        g.putString(118, 16, "Shape:");

        Shape bombShape = TowerFactory.createBombTower(0, 0).getShape();
        drawShapePreview(g, bombShape, 120, 20);

        g.putString(40, 24, "Special: Explodes on hit and damages enemies in a 2 radius");
        g.putString(40, 25, "Cost: 150 gold");


        g.setForegroundColor(TextColor.ANSI.GREEN);
        g.putString(40, 28, "Press Y to buy");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(40, 29, "Press N to return");
    }

}
