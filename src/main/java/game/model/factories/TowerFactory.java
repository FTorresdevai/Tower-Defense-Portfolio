package game.model.factories;

import com.googlecode.lanterna.TextColor;
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

        Tower t = new Tower(x, y, pyramid);
        t.setTowerColor(TextColor.Factory.fromString("#EEEEEE"));
        t.setTowerType("basic");

        return t;

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
        t.setRange(13);
        t.setMaxCooldown(14);
        t.setTowerColor(TextColor.Factory.fromString("#66FF66"));
        t.setTowerType("rapid");
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
        t.setDamage(6);
        t.setRange(50);
        t.setMaxCooldown(90);
        t.setCanSeeCamo(true);
        t.setTowerColor(TextColor.Factory.fromString("#55CCFF"));
        t.setTowerType("sniper");
        return t;
    }
    public static Tower createBombTower(int x, int y) {
        Shape s = new Shape();

        s.add(0, -2, '(');
        s.add(1, -2, ')');

        s.add(-1, -1, '(');
        s.add(2, -1, ')');

        s.add(0, 0, '(');
        s.add(1, 0, ')');

        Tower t = new Tower(x, y, s);
        t.setDamage(3);
        t.setRange(12);
        t.setMaxCooldown(75);
        t.setTowerType("bomb");
        t.setTowerColor(TextColor.Factory.fromString("#FF5533"));
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
        t.setRange(10);
        t.setMaxCooldown(80);
        t.setTowerType("frost");
        t.setTowerColor(TextColor.Factory.fromString("#66CCFF"));
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
        t.setRange(16);
        t.setMaxCooldown(60);
        t.setCanSeeCamo(true);
        t.setTowerType("tesla");
        t.setTowerColor(TextColor.Factory.fromString("#AA66FF"));
        return t;
    }

    public static Shape getBasicTowerShape() {
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

        return pyramid;
    }

    public static Shape getRapidTowerShape() {
        Shape gun = new Shape();

        gun.add(0, -2, '|');
        gun.add(0, -1, '|');
        gun.add(1, -2, '|');
        gun.add(1, -1, '|');

        gun.add(-1, 0, '[');
        gun.add(0, 0, '=');
        gun.add(1, 0, '=');
        gun.add(2, 0, ']');

        return gun;
    }

    public static Shape getSniperTowerShape() {
        Shape s = new Shape();

        s.add(0, -3, '^');
        s.add(0, -2, '|');
        s.add(0, -1, '|');
        s.add(-1, 0, '/');
        s.add(1, 0, '\\');

        return s;
    }

    public static Shape getBombTowerShape() {
        Shape s = new Shape();

        s.add(0, -2, '(');
        s.add(1, -2, ')');

        s.add(-1, -1, '(');
        s.add(2, -1, ')');

        s.add(0, 0, '(');
        s.add(1, 0, ')');

        return s;
    }

    public static Shape getFrostTowerShape() {
        Shape s = new Shape();

        s.add(0, -2, '*');
        s.add(-1, -1, '*');
        s.add(0, -1, '*');
        s.add(1, -1, '*');
        s.add(0, 0, '*');

        return s;
    }

    public static Shape getTeslaTowerShape() {
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

        return s;
    }

}
