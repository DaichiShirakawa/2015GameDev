package jp.co.tkhs.gp15.content.tokishooting.stage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyBase;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyBoss;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyRotate;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyStandard;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyToki;
import jp.co.tkhs.gp15.system.game.GameObjectImpl;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.utils.LR;

/**
 * ステージの基底クラス
 *
 * @author tokiha0s
 *
 */
abstract class StageBase extends GameObjectImpl {
    private TokiShootingScene scene;
    private long stageFrame = 0;
    private List<SpawnData> spawns = new LinkedList<>();
    private List<ShootingCharacter> spawnedEnemies = new LinkedList<>();

    public StageBase(TokiShootingScene parentScene) {
        this.scene = parentScene;
        createSpawns();
    }

    protected abstract void createSpawns();

    protected final void addSpawn(Class<? extends TSEnemyBase> enemyClass, int stageFrame,
            float angle, LR lr) {
        spawns.add(new SpawnData(enemyClass, stageFrame, angle, lr));
    }

    @Override
    public boolean updateProcess() {
        for (Iterator<SpawnData> ite = spawns.iterator(); ite.hasNext();) {
            SpawnData data = ite.next();
            if (data.canSpawn(stageFrame)) {
                doSpawn(data);
                ite.remove();
            }
        }
        stageFrame++;
        return true;
    }

    private void doSpawn(SpawnData spawnData) {
        TSEnemyBase enemy = spawnData.spawnTo(scene);
        scene.add(enemy);
        spawnedEnemies.add(enemy);
    }

    @Override
    protected void renderProcess() {
        return;
    }

    public boolean isClear() {
        if (!spawns.isEmpty()) {
            return false;
        }
        for (ShootingCharacter enemy : spawnedEnemies) {
            if (!enemy.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 直進敵発生
     */
    protected void standardSpawn(int frame, float angle) {
        addSpawn(TSEnemyStandard.class, frame, angle, LR.LEFT);
    }

    /**
     * 回転敵発生
     */
    protected void rotateSpawn(int frame, float angle, LR lr) {
        addSpawn(TSEnemyRotate.class, frame, angle, lr);
    }

    /**
     * 中ボス発生
     */
    protected void tokiSpawn(int frame, float angle, LR lr) {
        addSpawn(TSEnemyToki.class, frame, angle, lr);
    }

    /**
     * ボス発生
     */
    protected void bossSpawn(int frame, float angle, LR lr) {
        addSpawn(TSEnemyBoss.class, frame, angle, lr);
    }
}
