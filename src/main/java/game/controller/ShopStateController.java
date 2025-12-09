package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.audio.SoundManager;
import game.model.factories.TowerFactory;
import game.states.*;

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
            context.setState(new ClassicTowerInfoState(targetX, targetY));
            return;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') {
            context.setState(new RapidTowerInfoState(targetX, targetY));
            return;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') {
            context.setState(new SniperTowerInfoState(targetX, targetY));
            return;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') {
            context.setState(new BombTowerInfoState(targetX, targetY));
            return;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '5') {
            context.setState(new FrostTowerInfoState(targetX, targetY));
            return;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '6') {
            context.setState(new TeslaTowerInfoState(targetX, targetY));
            return;
        }

        if (input.getKeyType() == KeyType.Escape) {
            context.setState(new PlayState());
        }
    }

    public void update(Game context) throws Exception {
    }
}
