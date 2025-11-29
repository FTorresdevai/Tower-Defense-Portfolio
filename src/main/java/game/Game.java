package game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import game.states.PlayState;
import game.states.State;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    private GameHUD hud;
    private State currentState;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(80, 24);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena(80, 24);
        hud = new GameHUD(arena);
        currentState = new PlayState();
    }

    public Arena getArena() { return arena; }

    public GameHUD getHUD() { return hud; }

    public void setState(State next) { this.currentState = next; }

    public void resetArena() {
        arena = new Arena(80,24);
        hud = new GameHUD(arena);
    }

    public void run() throws IOException {
        while (true) {
            KeyStroke key = screen.pollInput();

            if (key != null && (key.getKeyType() == KeyType.EOF || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'))) {

                screen.close();
                System.exit(0);
            }

            try{
                currentState.handleInput(this, key);
                currentState.update(this);
                screen.clear();
                TextGraphics g = screen.newTextGraphics();
                currentState.draw(this, g);
                screen.refresh();
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}