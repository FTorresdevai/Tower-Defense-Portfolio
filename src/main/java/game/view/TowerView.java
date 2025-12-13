package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Pixel;
import game.model.Tower;

public class TowerView {

    public void draw(Tower tower, TextGraphics g){
        g.setForegroundColor(tower.getTowerColor());

        for (Pixel px : tower.getShape().getPixels()) {
            int x = (int) tower.getPosition().getX() + px.getDx();
            int y = (int) tower.getPosition().getY() + px.getDy();
            g.putString(x, y, String.valueOf(px.getChar()));
        }
    }
}
