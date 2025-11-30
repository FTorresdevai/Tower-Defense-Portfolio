package game.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Arena;
import game.model.Enemy;
import game.model.Projectile;
import game.model.Tower;

public class ArenaView {
    public void draw(Arena arena, TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');

        PathView pathView = new PathView();
        pathView.draw(arena.getPath(), graphics);

        TowerView towerView = new TowerView();
        for (Tower t : arena.getTowers()) towerView.draw(t, graphics);

        EnemyView enemyView = new EnemyView();
        for (Enemy e : arena.getEnemies()) enemyView.draw(e, graphics);

        ProjectileView projectileView = new ProjectileView();
        for (Projectile p : arena.getProjectiles()) projectileView.draw(p, graphics);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(arena.getCursorX(), arena.getCursorY(), "X");
    }
}
