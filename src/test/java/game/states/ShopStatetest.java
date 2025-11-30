package game.states;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.model.Arena;
import game.Game;
import game.model.GameHUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShopStateTest {

    private Game game;
    private Arena arena;
    private GameHUD hud;
    private ShopState state;

    @BeforeEach
    void setup() {
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
        hud = Mockito.mock(GameHUD.class);

        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getHUD()).thenReturn(hud);

        state = new ShopState(10, 10);
    }

    @Test
    void escapeReturnsToPlayState() throws Exception {
        KeyStroke key = new KeyStroke(KeyType.Escape);

        state.handleInput(game, key);

        Mockito.verify(game).setState(Mockito.any(PlayState.class));
    }

    @Test
    void buyTowerWithEnoughGold() throws Exception {
        Mockito.when(arena.getGold()).thenReturn(100);

        KeyStroke key = new KeyStroke('1', false, false);

        state.handleInput(game, key);

        Mockito.verify(arena).addTower(Mockito.any());
        Mockito.verify(arena).removeGold(50);
        Mockito.verify(game).setState(Mockito.any(PlayState.class));
    }

    @Test
    void buyTowerWithoutEnoughGold() throws Exception {
        Mockito.when(arena.getGold()).thenReturn(20);

        KeyStroke key = new KeyStroke('1', false, false);

        state.handleInput(game, key);

        Mockito.verify(hud).showMessage("Not enough gold!");
        Mockito.verify(game).setState(Mockito.any(PlayState.class));
    }
}