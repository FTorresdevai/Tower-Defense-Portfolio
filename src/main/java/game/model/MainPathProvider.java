package game.model;

public class MainPathProvider {
    public static Path createMainPath() {
        Path path = new Path();
        path.addNode(0, 20);
        path.addNode(20, 20);
        path.addNode(20, 30);
        path.addNode(50, 30);
        path.addNode(50, 18);
        path.addNode(80, 18);
        path.addNode(80, 26);
        path.addNode(110, 26);
        path.addNode(110, 12);
        path.addNode(139, 12);
        return path;
    }
}
