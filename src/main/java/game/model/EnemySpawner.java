package game.model;

import game.model.factories.EnemyFactory;

public class EnemySpawner {

    private Arena arena;
    private Path path;
    private int enemiesToSpawn;
    private int enemiesSpawned;
    private int spawnCooldown;
    private int spawnTimer;

    public EnemySpawner(Arena arena) {
        this.arena = arena;
        this.path = arena.getPath();
    }

    public void startWave(int wave) {
        enemiesSpawned = 0;
        enemiesToSpawn = 7 + wave * 2;

        if (wave == 1) {
            spawnCooldown = 28;
        }
        else if (wave == 2) {
            spawnCooldown = 26;
        }
        else if (wave == 3) {
            spawnCooldown = 24;
        }
        else {
            spawnCooldown = Math.max(20, 55 - wave * 2);
        }

        spawnTimer = 0;
    }


    public void update() {
        if (enemiesSpawned >= enemiesToSpawn) return;

        spawnTimer++;

        if (spawnTimer >= spawnCooldown) {
            spawnTimer = 0;
            spawnEnemy(arena.getWave());
        }
    }

    private void spawnEnemy(int wave) {
        int startX = (int) path.getNodes().get(0).getX();
        int startY = (int) path.getNodes().get(0).getY();

        Enemy e;

        // Seleção inimigos
        if (wave % 10 == 0) {
            e = EnemyFactory.createBossEnemy(startX, startY, path);
        }
        else if (wave > 12 && Math.random() < 0.15) {
            e = EnemyFactory.createCamoEnemy(startX, startY, path);
        }
        else if (wave > 8 && Math.random() < 0.20) {
            e = EnemyFactory.createTankEnemy(startX, startY, path);
        }
        else if (wave > 5 && Math.random() < 0.25) {
            e = EnemyFactory.createStrongEnemy(startX, startY, path);
        }
        else if (wave > 3 && Math.random() < 0.30) {
            e = EnemyFactory.createFastEnemy(startX, startY, path);
        }
        else {
            e = EnemyFactory.createBasicEnemy(startX, startY, path);
        }

        arena.getEnemies().add(e);
        enemiesSpawned++;
    }

    public boolean hasFinishedSpawning() {
        return enemiesSpawned >= enemiesToSpawn;
    }
}