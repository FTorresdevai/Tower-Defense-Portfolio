package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.states.PlayState;

public class SellTowerStateController {

    public void handleInput(Game context, KeyStroke input) {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character) {
            char c = Character.toLowerCase(input.getCharacter());

            if (c == 'y') {
                context.getArena().sellTowerAtCursor();
                context.setState(new PlayState());
                return;
            }

            if (c == 'n') {
                context.setState(new PlayState());
            }
        }
    }
}
