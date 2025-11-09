import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(160, 80);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        arena = new Arena(160, 80);
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        screen.refresh();
    }

    public void run() throws IOException {
        while (true){
            KeyStroke key = screen.pollInput();
            processKey(key);
            arena.update();
            draw();
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case EOF:
                screen.close();
                System.exit(0);
                break;
            default:
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    System.exit(0);
                }
                break;
        }

        arena.processKey(key);
        System.out.println(key);
    }
}
