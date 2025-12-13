package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Enemy;
import game.model.Pixel;

public class EnemyView {
    public void draw(Enemy enemy, TextGraphics g) {
        g.setForegroundColor(enemy.getColor());
        for (Pixel px : enemy.getShape().getPixels()) {
            int x = (int) enemy.getPosition().getX() + px.getDx();
            int y = (int) enemy.getPosition().getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}
