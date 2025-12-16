package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.states.PlayState;

public class GameOverStateController {

    private final int finalWave;

    public GameOverStateController(int finalWave) {
        this.finalWave = finalWave;
    }

    public void handleInput(Game context, KeyStroke input) {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character &&
                Character.toLowerCase(input.getCharacter()) == 'r') {

            context.resetArena();
            context.setState(new PlayState());
            return;
        }

        if (input.getKeyType() == KeyType.Escape ||
                (input.getKeyType() == KeyType.Character &&
                        Character.toLowerCase(input.getCharacter()) == 'q')) {

            System.exit(0);
        }
    }

    public void update(Game context) {

    }
}
