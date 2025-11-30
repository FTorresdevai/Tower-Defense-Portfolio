package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.controller.ShopStateController;
import game.view.ShopStateView;

public class ShopState implements State {

    private final ShopStateController controller;
    private final ShopStateView view = new ShopStateView();

    public ShopState(int x, int y) {
        this.controller = new ShopStateController(x, y);
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        controller.handleInput(context, input);
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        view.draw(context.getArena(), context.getHUD(), g);
    }

    @Override
    public void update(Game context) throws Exception {
        controller.update(context);
    }
}