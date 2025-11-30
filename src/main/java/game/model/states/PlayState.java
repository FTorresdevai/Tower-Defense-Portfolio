package game.model.states;

import game.view.ArenaView;
import game.view.HUDview;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;

public class PlayState implements State {

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'p') {
            context.setState(new PauseState());
            return;
        }

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'b') {
            int x = context.getArena().getCursorX();
            int y = context.getArena().getCursorY();

            if (context.getArena().isPlaceable(x, y)) {
                context.setState(new ShopState(x, y));
            } else {
                context.getHUD().showMessage("Invalid Position!");
            }
            return;
        }

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'q') {
            System.exit(0);
        }

        if (input.getKeyType() == KeyType.ArrowUp) {
            context.getArena().moveCursorUp();
        }
        else if (input.getKeyType() == KeyType.ArrowDown) {
            context.getArena().moveCursorDown();
        }
        else if (input.getKeyType() == KeyType.ArrowLeft) {
            context.getArena().moveCursorLeft();
        }
        else if (input.getKeyType() == KeyType.ArrowRight) {
            context.getArena().moveCursorRight();
        }

        if (input.getKeyType() == KeyType.Character) {
            char c = Character.toLowerCase(input.getCharacter());
            if (c == 'w') context.getArena().moveCursorUp();
            if (c == 's') context.getArena().moveCursorDown();
            if (c == 'a') context.getArena().moveCursorLeft();
            if (c == 'd') context.getArena().moveCursorRight();
        }
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
        new ArenaView().draw(context.getArena(), g);
        new HUDview().draw(context.getHUD(), context.getArena(), g);
    }
}