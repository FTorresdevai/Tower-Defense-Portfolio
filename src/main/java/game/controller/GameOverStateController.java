package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.states.PlayState;

public class GameOverStateController {
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getCharacter() != null && input.getCharacter() == 'm') {
            context.resetArena();
            context.setState(new PlayState());
        }
    }

    public void update(Game context) throws Exception {
    }
}
