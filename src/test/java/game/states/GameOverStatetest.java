package game.states;

import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.model.states.GameOverState;
import game.model.states.PlayState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameOverStateTest {

    private Game game;
    private GameOverState state;

    @BeforeEach
    void setup() {
        game = Mockito.mock(Game.class);
        state = new GameOverState();
    }

    @Test
    void pressingMRestartsToPlay() throws Exception {
        KeyStroke key = new KeyStroke('m', false, false);

        state.handleInput(game, key);

        Mockito.verify(game).resetArena();
        Mockito.verify(game).setState(Mockito.any(PlayState.class));
    }

    @Test
    void pressingOtherKeysDoesNothing() throws Exception {
        KeyStroke key = new KeyStroke('x', false, false);

        state.handleInput(game, key);

        Mockito.verify(game, Mockito.never()).resetArena();
        Mockito.verify(game, Mockito.never()).setState(Mockito.any());
    }
}