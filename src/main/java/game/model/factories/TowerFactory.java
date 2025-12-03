package game.model.factories;

import game.model.Shape;
import game.model.Tower;

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
    public static Tower createRapidTower(int x, int y) {
        Shape gun = new Shape();

        gun.add(0, -2, '|');
        gun.add(0, -1, '|');
        gun.add(1, -2, '|');
        gun.add(1, -1, '|');


        gun.add(-1, 0, '[');
        gun.add(0, 0, '=');
        gun.add(1, 0, '=');
        gun.add(2, 0, ']');

        Tower t = new Tower(x, y, gun);
        t.setDamage(1);
        t.setRange(5);
        t.setMaxCooldown(10);
        return t;
    }
    public static Tower createSniperTower(int x, int y) {
        Shape s = new Shape();

        s.add(0, -3, '^');
        s.add(0, -2, '|');
        s.add(0, -1, '|');
        s.add(-1, 0, '/');
        s.add(1, 0, '\\');

        Tower t = new Tower(x, y, s);
        t.setDamage(5);
        t.setRange(12);
        t.setCanSeeCamo(true);
        t.setMaxCooldown(60);
        return t;
    }
    public static Tower createBombTower(int x, int y) {
        Shape s = new Shape();

        s.add(0, -2, '(');
        s.add(1, -2, ')');

        s.add(-1, -1, '(');
        s.add(0, -1, ' ');
        s.add(1, -1, ')');

        s.add(0, 0, '(');
        s.add(1, 0, ')');

        Tower t = new Tower(x, y, s);
        t.setDamage(3);
        t.setRange(5);
        t.setMaxCooldown(45);
        t.setTowerType("bomb");
        return t;
    }
    public static Tower createFrostTower(int x, int y) {
        Shape s = new Shape();

        s.add(0, -2, '*');
        s.add(-1, -1, '*');
        s.add(0, -1, '*');
        s.add(1, -1, '*');
        s.add(0, 0, '*');

        Tower t = new Tower(x, y, s);
        t.setDamage(0);
        t.setRange(6);
        t.setMaxCooldown(60);
        t.setTowerType("frost");
        return t;
    }
    public static Tower createTeslaTower(int x, int y) {
        Shape s = new Shape();

        s.add(-1, -2, '/');
        s.add(1, -2, '\\');
        s.add(-2, -1, '/');
        s.add(-1, -1, '=');
        s.add(0, -1, '=');
        s.add(1, -1, '=');
        s.add(2, -1, '\\');
        s.add(-1, 0, '\\');
        s.add(1, 0, '/');

        Tower t = new Tower(x, y, s);
        t.setDamage(2);
        t.setRange(6);
        t.setCanSeeCamo(true);
        t.setMaxCooldown(50);
        t.setTowerType("tesla");
        return t;
    }


}
