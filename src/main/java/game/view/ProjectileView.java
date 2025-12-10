package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Pixel;
import game.model.Projectile;

public class ProjectileView {
    public void draw(Projectile projectile, TextGraphics g) {
        g.setForegroundColor(projectile.getProjectileColor());
        for (Pixel px : projectile.getShape().getPixels()) {
            int x = (int) projectile.getPosition().getX() + px.getDx();
            int y = (int) projectile.getPosition().getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}
