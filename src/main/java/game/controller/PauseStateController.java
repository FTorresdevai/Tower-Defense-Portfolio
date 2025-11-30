package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.states.PlayState;

public class PauseStateController {
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && input.getCharacter() != null &&
                Character.toLowerCase(input.getCharacter()) == 'r') {
            context.setState(new PlayState());
            return;
        }

        if (input.getKeyType() == KeyType.Character && input.getCharacter() != null &&
                Character.toLowerCase(input.getCharacter()) == 'q') {
            System.exit(0);
        }
    }

    public void update(Game context) {}
}
