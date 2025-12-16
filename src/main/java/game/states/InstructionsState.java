package game.states;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.controller.InstructionsStateController;

import java.io.IOException;

public class InstructionsState implements State {

    private final InstructionsStateController controller = new InstructionsStateController();

    @Override
    public void handleInput(Game context, KeyStroke input) throws Exception {
        controller.handleInput(context, input);
    }

    @Override
    public void update(Game context) throws Exception {
        controller.update(context);
    }

    @Override
    public void draw(Game context, TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(140, 44), ' ');

        g.setForegroundColor(TextColor.ANSI.RED);
        int yTitle = 3;
        int xTitle = 45;

        g.putString(xTitle, yTitle++, " _______  _______  __   __  _______  ______  ");
        g.putString(xTitle, yTitle++, "|       ||       ||  | |  ||       ||    _ | ");
        g.putString(xTitle, yTitle++, "|_     _||   _   ||  |_|  ||    ___||   | || ");
        g.putString(xTitle, yTitle++, "  |   |  |  | |  ||       ||   |___ |   |_||_");
        g.putString(xTitle, yTitle++, "  |   |  |  |_|  ||       ||    ___||    __  |");
        g.putString(xTitle, yTitle++, "  |   |  |       ||   _   ||   |___ |   |  | |");
        g.putString(xTitle, yTitle++, "  |___|  |_______||__| |__||_______||___|  |_|");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        xTitle = 38;
        g.putString(xTitle, yTitle++, " ______   _______  _______  _______  __    _  _______  _______ ");
        g.putString(xTitle, yTitle++, "|      | |       ||       ||       ||  |  | ||       ||       |");
        g.putString(xTitle, yTitle++, "|  _    ||    ___||    ___||    ___||   |_| ||  _____||    ___|");
        g.putString(xTitle, yTitle++, "| | |   ||   |___ |   |___ |   |___ |       || |_____ |   |___ ");
        g.putString(xTitle, yTitle++, "| |_|   ||    ___||    ___||    ___||  _    ||_____  ||    ___|");
        g.putString(xTitle, yTitle++, "|       ||   |___ |   |    |   |___ | | |   | _____| ||   |___ ");
        g.putString(xTitle, yTitle++, "|______| |_______||___|    |_______||_|  |__||_______||_______|");


        g.setForegroundColor(TextColor.ANSI.YELLOW);
        String sub = "--- MISSION DETAILS ---";
        g.putString((140 - sub.length()) / 2, 18, sub);


        g.setForegroundColor(TextColor.ANSI.WHITE);

        int leftColX = 25;
        int row = 22;

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(leftColX, row, "> MISSION OBJECTIVES");
        row += 2;

        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(leftColX, row++, "* Stop all enemies before");
        g.putString(leftColX, row++, "  they reach the base.");
        row++;
        g.putString(leftColX, row++, "* You have 10 Lives.");
        g.putString(leftColX, row++, "  Don't waste them.");
        row++;
        g.putString(leftColX, row++, "* Earn Gold by destroying enemies");
        g.putString(leftColX, row++, "  and surviving waves to upgrade.");


        int rightColX = 85;
        row = 22;

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(rightColX, row, "> CONTROL MODULE");
        row += 2;

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(rightColX, row++, "[ ARROW KEYS ]");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(rightColX, row++, "Move Cursor");
        row++;

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(rightColX, row++, "[ B ]");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(rightColX, row++, "Build Tower Menu");
        row++;

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(rightColX, row++, "[ S ] / [ SPACE ]");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(rightColX, row++, "Start Next Wave");
        row++;

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(rightColX, row++, "[ Q ]");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(rightColX, row++, "Quit Mission");
        row++;

        g.setForegroundColor(TextColor.ANSI.YELLOW);
        g.putString(rightColX, row++, "[ U ]");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(rightColX, row++, "Sell Tower");


        g.setForegroundColor(TextColor.ANSI.GREEN);
        String startMsg = ">>> PRESS [ENTER] TO START MISSION <<<";
        g.putString((140 - startMsg.length()) / 2, 40, startMsg);
    }
}