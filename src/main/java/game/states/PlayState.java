package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;

public class PlayState implements State {

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'p') {
            context.setState(new PauseState());
            return;
        }

        context.getArena().processKey(input);
    }

    @Override
    public void update(Game context) throws Exception {
        context.getArena().update();

        if (context.getArena().isGameOver()) {
            context.setState(new GameOverState());
        }
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        context.getArena().draw(g);
        context.getHUD().drawStats(g);
    }
}

