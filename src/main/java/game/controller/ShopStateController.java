package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.audio.SoundManager;
import game.model.factories.TowerFactory;
import game.states.PlayState;

public class ShopStateController {
    private final int targetX;
    private final int targetY;


    public ShopStateController(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') {
            buyBasicTower(context);
        }

        if (input.getKeyType() == KeyType.Escape) {
            context.setState(new PlayState());
        }
    }

    public void update(Game context) throws Exception {
    }

    private void buyBasicTower(Game context) {
        int cost = 50;

        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createBasicTower(targetX, targetY));
            context.getArena().removeGold(cost);
        } else {
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
}
