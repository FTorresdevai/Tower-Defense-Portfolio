package game.states;

import com.googlecode.lanterna.input.KeyStroke;
import game.model.Arena;
import game.Game;
import game.model.GameHUD;
import game.model.states.GameOverState;
import game.model.states.PauseState;
import game.model.states.PlayState;
import game.model.states.ShopState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayStateTest {

    private Game game;
    private Arena arena;
    private GameHUD hud;
    private PlayState state;

    @BeforeEach
    void setup() {
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
        hud = Mockito.mock(GameHUD.class);

        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getHUD()).thenReturn(hud);

        state = new PlayState();
    }

    @Test
    void testPressP_GoesToPause() throws Exception {
        KeyStroke key = new KeyStroke('p', false, false);

        state.handleInput(game, key);

        Mockito.verify(game).setState(Mockito.any(PauseState.class));
    }

    @Test
    void testPressB_InvalidPlacement_ShowsMessage() throws Exception {
        KeyStroke key = new KeyStroke('b', false, false);

        Mockito.when(arena.getCursorX()).thenReturn(5);
        Mockito.when(arena.getCursorY()).thenReturn(5);
        Mockito.when(arena.isPlaceable(5, 5)).thenReturn(false);

        state.handleInput(game, key);

        Mockito.verify(hud).showMessage("Invalid Position!");
        Mockito.verify(game, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void testPressB_ValidPlacement_GoesToShop() throws Exception {
        KeyStroke key = new KeyStroke('b', false, false);

        Mockito.when(arena.getCursorX()).thenReturn(4);
        Mockito.when(arena.getCursorY()).thenReturn(6);
        Mockito.when(arena.isPlaceable(4, 6)).thenReturn(true);

        state.handleInput(game, key);

        Mockito.verify(game).setState(Mockito.any(ShopState.class));
    }

    @Test
    void testUpdateTriggersGameOverTransition() throws Exception {
        Mockito.when(arena.isGameOver()).thenReturn(true);

        state.update(game);

        Mockito.verify(game).setState(Mockito.any(GameOverState.class));
    }

    @Test
    void testUpdateNormal_NoChange() throws Exception {
        Mockito.when(arena.isGameOver()).thenReturn(false);

        state.update(game);

        Mockito.verify(game, Mockito.never()).setState(Mockito.any());
    }
}