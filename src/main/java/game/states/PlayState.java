package game.states;

import game.controller.PlayStateController;
import game.view.ArenaView;
import game.view.HUDview;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;


public class PlayState implements State {
    private final PlayStateController controller = new PlayStateController();
    private final ArenaView arenaView = new ArenaView();
    private final HUDview hudView = new HUDview();

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
        arenaView.draw(context.getArena(), g);
        hudView.draw(context.getHUD(), context.getArena(), g);
    }
}

