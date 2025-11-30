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

    public boolean isOnPath(Position position, Shape shape) {
        int minDx = 0; int maxDx = 0;
        int minDy = 0; int maxDy = 0;

        for (Pixel p : shape.getPixels()) {
            if (p.getDx() < minDx) minDx = p.getDx();
            if (p.getDx() > maxDx) maxDx = p.getDx();
            if (p.getDy() < minDy) minDy = p.getDy();
            if (p.getDy() > maxDy) maxDy = p.getDy();
        }

        int tMinX = (int) position.getX() + minDx;
        int tMaxX = (int) position.getX() + maxDx;
        int tMinY = (int) position.getY() + minDy;
        int tMaxY = (int) position.getY() + maxDy;

        for (int i = 0; i < nodes.size() - 1; i++) {
            Position p1 = nodes.get(i);
            Position p2 = nodes.get(i + 1);

            int pMinX = Math.min((int) p1.getX(), (int) p2.getX());
            int pMaxX = Math.max((int) p1.getX(), (int) p2.getX());
            int pMinY = Math.min((int) p1.getY(), (int) p2.getY());
            int pMaxY = Math.max((int) p1.getY(), (int) p2.getY());

            if (tMaxX >= pMinX && tMinX <= pMaxX && tMaxY >= pMinY && tMinY <= pMaxY) {
                return true;
            }
        }
        return false;
    }
}