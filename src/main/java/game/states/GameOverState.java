package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.controller.GameOverStateController;
import game.view.GameOverView;

public class GameOverState implements State {

    private final GameOverStateController controller;
    private final GameOverView view;

    public GameOverState(int finalWave) {
        this.controller = new GameOverStateController(finalWave);
        this.view = new GameOverView(finalWave);
    }

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
        view.draw(g);
    }
}
