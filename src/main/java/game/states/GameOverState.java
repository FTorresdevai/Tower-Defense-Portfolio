package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.controller.GameOverStateController;
import game.view.GameOverView;

public class GameOverState implements State {

    private final GameOverStateController controller = new GameOverStateController();
    private final GameOverView view = new GameOverView();

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        controller.handleInput(context, input);
    }

    @Override
    public void update(Game context) throws Exception {
        controller.update(context);
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        view.draw(context.getArena(), context.getHUD(), g);
    }
}