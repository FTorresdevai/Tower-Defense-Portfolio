package game;

import game.model.*;
import game.model.factories.EnemyFactory;
import game.model.factories.ProjectileFactory;
import game.model.factories.TowerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    private Arena arena;
    private Enemy enemyMock;



    @BeforeEach
    void setup(){
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
        enemyMock = Mockito.mock(Enemy.class);
        arena = new Arena(140, 44, path);
    }


    @Test
    void enemyReachingEndDecreasesLivesAndIsRemoved() {
        Mockito.when(enemyMock.hasReachedEnd()).thenReturn(Boolean.TRUE);

        arena.getEnemies().clear();
        arena.getEnemies().add(enemyMock);


        int oldLives = arena.getLives();

        arena.update();

        assertEquals(oldLives - 1, arena.getLives(), "should update lives");
        assertFalse(arena.getEnemies().contains(enemyMock), "should discard the enemy");
    }
    @Test
    void towerShootsProjectileWhenEnemyInRange() {
        arena.getTowers().clear();
        arena.getEnemies().clear();

        Tower tower = TowerFactory.createBasicTower(10, 10);
        arena.getTowers().add(tower);

        Enemy enemy = EnemyFactory.createBasicEnemy(12, 10, arena.getPath());
        arena.getEnemies().add(enemy);

        arena.update();

        assertFalse(arena.getProjectiles().isEmpty(), "Tower should shoot a projectile when enemy is in range");
    }


    @Test
    void projectileHitsEnemyAndGivesGold() {

        Enemy enemy = EnemyFactory.createBasicEnemy(10, 10, arena.getPath());
        arena.getEnemies().add(enemy);

        Projectile p = ProjectileFactory.createBasicProjectile(10, 10, enemy);
        arena.getProjectiles().add(p);

        int oldGold = arena.getGold();

        arena.update();

        assertFalse(arena.getEnemies().contains(enemy),"enemy should have been hit");
        assertFalse(arena.getProjectiles().contains(p),"projectile should have hit the enemy");
        assertEquals(oldGold + 5, arena.getGold(),"gold should have been updated");
    }




}
