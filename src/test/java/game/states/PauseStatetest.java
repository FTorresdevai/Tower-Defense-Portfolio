package game.states;

import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PauseStateTest {

    private Game game;
    private PauseState state;

    @BeforeEach
    void setup() {
        game = Mockito.mock(Game.class);
        state = new PauseState();
    }

    @Test
    void pressingRReturnsToPlayState() throws Exception {
        KeyStroke key = new KeyStroke('r', false, false);

        state.handleInput(game, key);

        Mockito.verify(game).setState(Mockito.any(PlayState.class));
    }

}