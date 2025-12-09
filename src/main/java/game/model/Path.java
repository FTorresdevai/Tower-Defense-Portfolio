package game.model;

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

    public boolean isOnPath(Position pos, Shape shape) {
        for (Pixel p : shape.getPixels()) {
            int px = (int) pos.getX() + p.getDx();
            int py = (int) pos.getY() + p.getDy();

            if (isTileOnPath(px, py)) return true;
        }
        return false;
    }

    private boolean isTileOnPath(int x, int y) {
        for (int i = 0; i < nodes.size() - 1; i++) {
            Position a = nodes.get(i);
            Position b = nodes.get(i + 1);

            int ax = (int) a.getX();
            int ay = (int) a.getY();
            int bx = (int) b.getX();
            int by = (int) b.getY();

            if (ay == by) {
                if (y == ay && x >= Math.min(ax, bx) && x <= Math.max(ax, bx)) {
                    return true;
                }
            }

            if (ax == bx) {
                if (x == ax && y >= Math.min(ay, by) && y <= Math.max(ay, by)) {
                    return true;
                }
            }
        }

        return false;
    }
}