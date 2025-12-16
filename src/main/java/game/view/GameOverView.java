package game.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameOverView {

    private final int finalWave;

    public GameOverView(int finalWave) {
        this.finalWave = finalWave;
    }

    public void draw(TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(140, 44), ' ');

        g.setForegroundColor(TextColor.ANSI.RED);
        int titleX = 40;
        int titleY = 8;
        g.putString(titleX, titleY++, "  ____    _    __  __ _____        _____     _______ ____  ");
        g.putString(titleX, titleY++, " / ___|  / \\  |  \\/  | ____|      / _ \\ \\   / / ____|  _ \\ ");
        g.putString(titleX, titleY++, "| |  _  / _ \\ | |\\/| |  _|       | | | \\ \\ / /|  _| | |_) |");
        g.putString(titleX, titleY++, "| |_| |/ ___ \\| |  | | |___      | |_| |\\ V / | |___|  _ < ");
        g.putString(titleX, titleY++, " \\____/_/   \\_\\_|  |_|_____|      \\___/  \\_/  |_____|_| \\_\\");
        g.putString(titleX, titleY++, "============================================================");


        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(58, 18, "--- MISSION REPORT ---");

        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(50, 22, "STATUS:");
        g.setForegroundColor(TextColor.ANSI.RED);
        g.putString(70, 22, "TERMINATED");

        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(50, 25, "WAVES SURVIVED:");

        g.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        String waveDisplay = "[  " + finalWave + "  ]";
        g.putString((140 - waveDisplay.length()) / 2, 28, waveDisplay);

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.drawLine(40, 36, 100, 36, '-');

        g.setForegroundColor(TextColor.ANSI.GREEN);
        g.putString(45, 38, "[R] REBOOT SYSTEM");

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(85, 38, "[Q] ABORT");
    }
}
