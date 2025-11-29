import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Position> nodes;

    public Path() {
        nodes = new ArrayList<>();
    }

    public void addNode(int x, int y) {
        nodes.add(new Position(x, y));
    }

    public List<Position> getNodes() { return nodes; }

    public void draw(TextGraphics g) {
        g.setForegroundColor(TextColor.Factory.fromString("#FFD700"));

        for (int i = 0; i < nodes.size(); i++) {

            Position p = nodes.get(i);
            g.putString((int)p.getX(), (int)p.getY(), "░");

            if (i < nodes.size() - 1) {
                Position next = nodes.get(i + 1);

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