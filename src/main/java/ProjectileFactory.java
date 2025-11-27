public class ProjectileFactory {

    public static Projectile createBasicProjectile(int startX, int startY, Enemy target) {
        Shape projectileShape = new Shape();
        projectileShape.add(0, 0, '*'); // basic projectile represented by '*'
        return new Projectile(startX, startY, projectileShape, target);
    }

}
