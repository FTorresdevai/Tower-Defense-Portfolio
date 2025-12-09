package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.*;
import game.Game;
import game.audio.SoundManager;
import game.model.factories.TowerFactory;

public class SniperTowerInfoState implements State {

    private final int x, y;

    public SniperTowerInfoState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character) {
            char c = Character.toLowerCase(input.getCharacter());

            if (c == 'y') {
                int cost = 120;
                if (context.getArena().getGold() >= cost) {
                    SoundManager.getInstance().play("sfx_bought");
                    context.getArena().addTower(TowerFactory.createSniperTower(x, y));
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
    public void draw(Game context, TextGraphics g) {
        g.putString(10, 5, "=== SNIPER TOWER ===");
        g.putString(10, 7, "Damage: 5");
        g.putString(10, 8, "Range: 12");
        g.putString(10, 9, "Can detect camo enemies.");
        g.putString(10, 12, "Cost: 120 gold");
        g.putString(10, 14, "Press Y to buy");
        g.putString(10, 15, "Press N to return");
    }
}
