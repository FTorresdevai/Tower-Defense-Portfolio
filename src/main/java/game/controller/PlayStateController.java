package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.audio.SoundManager;
import game.states.GameOverState;
import game.states.PauseState;
import game.states.ShopState;

public class PlayStateController {

    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'p') {
            context.setState(new PauseState());
            SoundManager.getInstance().play("sfx_menuchange");
            return;
        }

        if (input.getKeyType() == KeyType.Character && Character.toLowerCase(input.getCharacter()) == 'b') {
            int x = context.getArena().getCursorX();
            int y = context.getArena().getCursorY();

            SoundManager.getInstance().play("sfx_menuchange");

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

    public void update(Game context) throws Exception {
        int before = context.getArena().getProjectiles().size();
        int enemiesBefore = context.getArena().getEnemies().size();

        context.getArena().update();

        int after = context.getArena().getProjectiles().size();
        if (after > before) {
            SoundManager.getInstance().play("sfx_shoot");
        }

        int enemiesAfter = context.getArena().getEnemies().size();
        if (enemiesAfter < enemiesBefore) {
            SoundManager.getInstance().play("sfx_coin_enemydeath");
        }

        if (context.getArena().isGameOver()) {
            context.setState(new GameOverState());
        }
    }
}
