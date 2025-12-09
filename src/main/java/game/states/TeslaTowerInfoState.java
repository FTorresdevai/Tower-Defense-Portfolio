package game.states;

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

        g.putString(10, 5, "=== TESLA TOWER ===");
        g.putString(10, 7, "Damage: 2");
        g.putString(10, 8, "Range: 16");
        g.putString(10, 9, "Chain lightning (hits up to 3 extra enemies).");
        g.putString(10, 12, "Cost: 180 gold");
        g.putString(10, 14, "Press Y to buy");
        g.putString(10, 15, "Press N to return");

        Shape preview = TowerFactory.getTeslaTowerShape();
        drawShapePreview(g, preview, 60, 10);
    }
}
