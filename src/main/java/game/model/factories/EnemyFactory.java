package game.model.factories;

import com.googlecode.lanterna.TextColor;
import game.model.Shape;
import game.model.Enemy;
import game.model.Path;

public class EnemyFactory {

    public static Enemy createBasicEnemy(int startX, int startY, Path path) {
        Shape s = new Shape();
        s.add(0, 0, 'o');
        Enemy e = new Enemy(startX, startY, s, path, 4, false, TextColor.Factory.fromString("#FF6666"));
        e.setSpeed(0.12f);
        e.setBounty(2);
        return e;
    }
    public static Enemy createFastEnemy(int x, int y, Path path) {
        Shape s = new Shape();
        s.add(0, 0, '@');
        Enemy e = new Enemy(x, y, s, path, 4, false, TextColor.Factory.fromString("#FFDD55"));
        e.setSpeed(0.22f);
        e.setBounty(1);
        return e;
    }
    public static Enemy createStrongEnemy(int x, int y, Path path) {
        Shape s = new Shape();
        s.add(0, 0, 'O');
        Enemy e = new Enemy(x, y, s, path, 10, false, TextColor.Factory.fromString("#FFAA33"));
        e.setSpeed(0.10f);
        e.setBounty(2);
        return e;
    }
    public static Enemy createTankEnemy(int x, int y, Path path) {
        Shape s = new Shape();
        s.add(0, 0, '8');
        Enemy e = new Enemy(x, y, s, path, 18, false, TextColor.Factory.fromString("#6699CC"));
        e.setSpeed(0.07f);
        e.setBounty(3);
        return e;
    }
    public static Enemy createCamoEnemy(int x, int y, Path path) {
        Shape s = new Shape();
        s.add(0, 0, '?');
        Enemy e = new Enemy(x, y, s, path, 5, true, TextColor.Factory.fromString("#AA66FF"));
        e.setSpeed(0.12f);
        e.setBounty(2);
        return e;
    }
    public static Enemy createBossEnemy(int x, int y, Path path) {
        Shape s = new Shape();
        s.add(0, 0, '&');
        Enemy e = new Enemy(x, y, s, path, 150, false, TextColor.Factory.fromString("#FF44AA"));
        e.setSpeed(0.09f);
        e.setBounty(15);
        return e;
    }

}
