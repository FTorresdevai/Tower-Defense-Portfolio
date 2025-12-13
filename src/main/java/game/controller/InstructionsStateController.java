package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.states.PlayState;

import java.io.IOException;

public class InstructionsStateController {

    public void handleInput(Game context, KeyStroke input) throws IOException {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Enter) {
            context.setState(new PlayState());
        }

        if (input.getKeyType() == KeyType.Escape ||
                (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'q')) {
            System.exit(0);
        }
    }

    public void update(Game context) {
    }

}