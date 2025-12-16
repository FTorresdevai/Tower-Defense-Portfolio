package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.controller.SellTowerStateController;
import game.view.SellTowerView;

public class SellTowerState implements State {

    private final SellTowerStateController controller;
    private final SellTowerView view;

    public SellTowerState(Game context) {
        int sellValue = context.getArena().getSellValueAtCursor();
        this.controller = new SellTowerStateController();
        this.view = new SellTowerView(sellValue);
    }

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        controller.handleInput(context, input);
    }

    @Override
    public void update(Game context) {}

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        view.draw(context.getArena(), context.getHUD(), g);
    }
}
