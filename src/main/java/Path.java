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

    public List<Position> getNodes() {
        return nodes;
    }
}

