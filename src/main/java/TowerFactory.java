public class TowerFactory {


    public static Tower createBasicTower(int x, int y) {
        Shape pyramid = new Shape();


        pyramid.add(0, -2, '^');
        pyramid.add(-1, -1, '^');
        pyramid.add(0, -1, '^');
        pyramid.add(1, -1, '^');

        pyramid.add(-2, 0, '^');
        pyramid.add(-1, 0, '^');
        pyramid.add(0, 0, '^');
        pyramid.add(1, 0, '^');
        pyramid.add(2, 0, '^');

        return new Tower(x, y, pyramid);
    }
}
