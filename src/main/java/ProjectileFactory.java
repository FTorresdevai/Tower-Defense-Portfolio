public class ProjectileFactory {

    public static Projectile createBasicProjectile(int startX, int startY, Enemy target) {
        Shape projectileShape = new Shape();
        projectileShape.add(0, 0, '*');
        return new Projectile(startX, startY, projectileShape, target);
    }

}
