
package game;
import com.googlecode.lanterna.screen.Screen;
import game.model.Arena;
import game.model.GameHUD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Screen screen;
    private Arena arena;
    private GameHUD hud;


    @Test
    void testResetArenaNew() throws Exception {
        Game game = new Game();
        Arena oldArena = game.getArena();
        GameHUD oldHUD = game.getHUD();
        game.resetArena();
        assertNotSame(oldArena, game.getArena(), "resetArena() should create new Arena");
        assertNotSame(oldHUD, game.getHUD(), "resetArena() should create new HUD");
        Arena newArena = game.getArena();
        GameHUD newHud = game.getHUD();

        assertTrue(newArena.getObservers().contains(newHud),"the new HUD should be observer of the new Arena");

    }
//    @Test
//    void resetArenaRestoresInitialState() throws Exception {
//        Game game = new Game();
//        game.getArena().();
//
//        game.resetArena();
//
//        Arena a = game.getArena();
//
//        assertEquals(10, a.getLives());
//        assertEquals(100, a.getGold());
//        assertEquals(1, a.getWave());
//    }

}