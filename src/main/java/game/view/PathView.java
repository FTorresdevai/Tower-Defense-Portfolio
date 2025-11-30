package game.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.model.Position;
import game.model.Path;

public class PathView {
    public void draw(Path path, TextGraphics g) {
        g.setForegroundColor(TextColor.Factory.fromString("#FFD700"));

        for (int i = 0; i < path.getNodes().size(); i++) {

            Position p = path.getNodes().get(i);
            g.putString((int)p.getX(), (int)p.getY(), "░");

            if (i < path.getNodes().size() - 1) {
                Position next = path.getNodes().get(i+1);

                int x1 = (int)p.getX();
                int y1 = (int)p.getY();
                int x2 = (int)next.getX();
                int y2 = (int)next.getY();

                if (y1 == y2) {
                    int start = Math.min(x1, x2);
                    int end = Math.max(x1, x2);
                    for (int x = start; x <= end; x++) {
                        g.putString(x, y1, "░");
                    }
                }

                if (x1 == x2) {
                    int start = Math.min(y1, y2);
                    int end = Math.max(y1, y2);
                    for (int y = start; y <= end; y++) {
                        g.putString(x1, y, "░");
                    }
                }
            }
        }
    }
}
