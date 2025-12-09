package game;
import game.audio.SoundManager;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import game.model.Arena;
import game.model.GameHUD;
import game.model.MainPathProvider;
import game.states.PlayState;
import game.states.State;
import java.io.IOException;



public class Game {
    private Screen screen;
    private Arena arena;
    private GameHUD hud;
    private State currentState;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(140, 44);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        terminal.setCursorVisible(false);
        screen.startScreen();
        screen.doResizeIfNecessary();
        SoundManager sm = SoundManager.getInstance();
        sm.loadAssetAsync("sfx_shoot", "/sounds/sfx_shoot.wav");
        sm.loadAssetAsync("sfx_coin_enemydeath", "/sounds/sfx_coin_enemydeath.wav");
        sm.loadAssetAsync("sfx_menuchange", "/sounds/sfx_menuchange.wav");
        sm.loadAssetAsync("sfx_bought", "/sounds/sfx_bought.wav");

        arena = new Arena(140, 44, MainPathProvider.createMainPath());
        hud = new GameHUD(arena);
        currentState = new PlayState();
    }

    private void draw() throws Exception {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        currentState.draw(this, graphics);
        screen.refresh();
    }

    public Arena getArena() { return arena; }
    public GameHUD getHUD() { return hud; }
    public void setState(State next) { this.currentState = next; }

    public void resetArena() {
        arena = new Arena(140, 44, MainPathProvider.createMainPath());
        hud = new GameHUD(arena);
    }

    public void run() throws IOException {
        while (true) {
            KeyStroke key = screen.pollInput();
            hud.tickMessage();

            if (key != null && key.getKeyType() == KeyType.EOF) {
                break;
            }

            try {
                currentState.handleInput(this, key);
                currentState.update(this);
                draw();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        screen.close();
    }
}