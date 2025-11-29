package game.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import game.Game;

public interface State {
    void handleInput(Game context, KeyStroke input) throws Exception;
    void update(Game context) throws Exception;
    void draw(game.Game context, TextGraphics g) throws Exception;
}
