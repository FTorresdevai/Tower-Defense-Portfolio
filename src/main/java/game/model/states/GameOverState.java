package game.model.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;

public class GameOverState implements State {

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getCharacter() != null && input.getCharacter() == 'm') {
            context.resetArena();
            context.setState(new PlayState());
        }
    }

    @Override
    public void update(Game context) throws Exception {
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        new game.view.GameOverView().draw(context.getArena(), context.getHUD(), g);
    }
}