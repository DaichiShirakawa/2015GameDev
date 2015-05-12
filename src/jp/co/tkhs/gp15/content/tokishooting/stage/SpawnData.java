package jp.co.tkhs.gp15.content.tokishooting.stage;

import java.lang.reflect.InvocationTargetException;

import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyBase;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

/**
 * 敵の発生タイミングと発生場所の定義
 * 
 * @author tokiha0s
 * 
 */
class SpawnData {
    private Class<? extends TSEnemyBase> enemyClass;
    private long spawnFrame;
    private float angle;
    private LR rotateLR;

    public SpawnData(Class<? extends TSEnemyBase> enemyClass, long spawnFrame, float angle,
            LR rotateLR) {
        this.enemyClass = enemyClass;
        this.spawnFrame = spawnFrame;
        this.angle = angle;
        this.rotateLR = rotateLR;
    }

    public TSEnemyBase spawnTo(ShootingScene parentScene) {
        try {
            return enemyClass.getConstructor(ShootingScene.class, float.class, LR.class)
                    .newInstance(parentScene, angle, rotateLR);
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.err.println("エラー：コンストラクタが呼び出せません");
            e.printStackTrace();
        }
        return null;
    }

    public boolean canSpawn(long frame) {
        return this.spawnFrame == frame;
    }
}
