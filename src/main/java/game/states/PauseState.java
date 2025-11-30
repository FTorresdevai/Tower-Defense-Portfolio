package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.view.PauseView;

public class PauseState implements State {

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getCharacter() != null && input.getCharacter() == 'r') {
            context.setState(new PlayState());
        }

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'q') {
            System.exit(0);
        }
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        new PauseView().draw(context.getArena(), context.getHUD(), g);
    }

    @Override
    public void update(Game context) throws Exception {
    }
}
