package game.model.factories;

import com.googlecode.lanterna.TextColor;
import game.model.Shape;
import game.model.Enemy;
import game.model.Projectile;

public class ProjectileFactory {

    public static Projectile createBasicProjectile(int startX, int startY, Enemy target, String towerType) {
        Shape projectileShape = new Shape();
        projectileShape.add(0, 0, '*');
        Projectile p = new Projectile(startX, startY, projectileShape, target);
        p.setTowerType(towerType);


        switch (towerType) {
            case "basic":
                p.setProjectileColor(TextColor.Factory.fromString("#FFFFFF")); break;

            case "rapid":
                p.setProjectileColor(TextColor.Factory.fromString("#FFEE33")); break;

            case "sniper":
                p.setProjectileColor(TextColor.Factory.fromString("#55FFFF")); break;

            case "bomb":
                p.setProjectileColor(TextColor.Factory.fromString("#FF7733")); break;

            case "frost":
                p.setProjectileColor(TextColor.Factory.fromString("#88CCFF")); break;

            case "tesla":
                p.setProjectileColor(TextColor.Factory.fromString("#CC66FF")); break;
        }

        return p;

    }

}
