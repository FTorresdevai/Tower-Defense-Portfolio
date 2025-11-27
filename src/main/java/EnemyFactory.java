public class EnemyFactory {

    public static Enemy createBasicEnemy(int startX, int startY, Path path) {
        Shape enemyShape = new Shape();
        enemyShape.add(0, 0, 'o');
        return new Enemy(startX, startY, enemyShape, path);
    }

}
