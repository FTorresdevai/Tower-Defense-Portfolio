package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;

public class PauseState implements State {

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getCharacter() != null && input.getCharacter() == 'r') {
            context.setState(new PlayState());
        }
    }

    @Override
    public void update(Game context) throws Exception {
    }

    @Override
    public void draw(Game context, TextGraphics g) throws Exception {
        context.getArena().draw(g);
        context.getHUD().draw(g);

        int width = context.getArena().getWidth();
        int height = context.getArena().getHeight();

        String msg1 = "=== PAUSED ===";
        String msg2 = "Press 'r' to resume";
        String msg3 = "Press 'q' to quit";

        int x1 = (width - msg1.length()) / 2;
        int y1 = height / 3;

        int x2 = (width - msg2.length()) / 2;
        int y2 = y1 + 2;

        int x3 = (width - msg3.length()) / 2;
        int y3 = y2 + 2;

        g.putString(x1, y1, msg1);
        g.putString(x2, y2, msg2);
        g.putString(x3, y3, msg3);
    }
}
