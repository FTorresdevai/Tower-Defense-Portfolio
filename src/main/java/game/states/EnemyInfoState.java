package game.states;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;

public class EnemyInfoState implements State {

    @Override
    public void handleInput(Game context, KeyStroke key) {
        if (key == null) return;

        if (key.getKeyType() == KeyType.Escape) {
            context.setState(new PlayState());
        }
    }

    @Override
    public void update(Game context) {}

    @Override
    public void draw(Game context, TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#111133"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(140, 44), ' ');

        int y = 3;

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(59, y, "=== ENEMY DATABASE ===");
        y += 3;

        g.setForegroundColor(TextColor.Factory.fromString("#FF6666"));
        g.putString(5, y, "●");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "BASIC ENEMY");
        y++;
        g.putString(10, y++, "HP: 4     Speed: 0.12     Bounty: 2");
        g.putString(10, y++, "Appears: Waves 1–∞");
        g.putString(10, y++, "Description: Standard infantry. The most found enemy.");
        y += 1;

        g.setForegroundColor(TextColor.Factory.fromString("#FFDD55"));
        g.putString(5, y, "»");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "FAST ENEMY");
        y++;
        g.putString(10, y++, "HP: 4     Speed: 0.22     Bounty: 1");
        g.putString(10, y++, "Appears: Waves 4–∞ (30% chance)");
        g.putString(10, y++, "Description: Agile scouts, the pace allows the to bypass weak defenses.");
        y += 1;

        g.setForegroundColor(TextColor.Factory.fromString("#FFAA33"));
        g.putString(5, y, "◆");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "STRONG ENEMY");
        y++;
        g.putString(10, y++, "HP: 10    Speed: 0.10     Bounty: 2");
        g.putString(10, y++, "Appears: Waves 6–∞ (25% chance)");
        g.putString(10, y++, "Description: Armored units that resist low damage towers.");
        y += 1;

        g.setForegroundColor(TextColor.Factory.fromString("#6699CC"));
        g.putString(5, y, "■");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "TANK ENEMY");
        y++;
        g.putString(10, y++, "HP: 18    Speed: 0.07     Bounty: 3");
        g.putString(10, y++, "Appears: Waves 9–∞ (20% chance)");
        g.putString(10, y++, "Description: Heavy units. Very hard to destroy.");
        y += 1;

        g.setForegroundColor(TextColor.Factory.fromString("#AA66FF"));
        g.putString(5, y, "?");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "CAMO ENEMY");
        y++;
        g.putString(10, y++, "HP: 5     Speed: 0.12     Bounty: 2");
        g.putString(10, y++, "Appears: Waves 13–∞ (15% chance)");
        g.putString(10, y++, "Description: Invisible to towers without camo detection (e.g. SniperTower).");
        y += 1;

        g.setForegroundColor(TextColor.Factory.fromString("#FF44AA"));
        g.putString(5, y, "♛");
        g.setForegroundColor(TextColor.ANSI.WHITE);
        g.putString(10, y, "BOSS ENEMY");
        y++;
        g.putString(10, y++, "HP: 150   Speed: 0.09     Bounty: 15");
        g.putString(10, y++, "Appears: Every 10 waves (10, 20, 30 ...)");
        g.putString(10, y++, "Description: Catastrophic threat. Requires coordinated fire. Time to deploy your strongest towers");
        y += 3;

        g.setForegroundColor(TextColor.ANSI.CYAN);
        g.putString(55, 41, "PRESS ESC TO RETURN");
    }
}
