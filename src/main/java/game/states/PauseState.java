package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.controller.PauseStateController;
import game.view.PauseView;

public class PauseState implements State {

    private final PauseStateController controller = new PauseStateController();
    private final PauseView pauseView = new PauseView();

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        controller.handleInput(context, input);
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        pauseView.draw(context.getArena(), context.getHUD(), g);
    }

    @Override
    public void update(Game context) throws Exception {
        controller.update(context);
    }
}
