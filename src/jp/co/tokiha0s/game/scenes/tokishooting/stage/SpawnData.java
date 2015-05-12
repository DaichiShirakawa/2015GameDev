package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import java.lang.reflect.InvocationTargetException;

import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.enemies.TSEnemyBase;

/**
 * 敵の発生タイミングと発生場所の定義
 * 
 * @author shirakawa
 * 
 */
class SpawnData {
	private Class<? extends TSEnemyBase> enemyClass;
	private long spawnFrame;
	private float angle;
	private LR rotateLR;

	public SpawnData(Class<? extends TSEnemyBase> enemyClass, long spawnFrame,
			float angle, LR rotateLR) {
		this.enemyClass = enemyClass;
		this.spawnFrame = spawnFrame;
		this.angle = angle;
		this.rotateLR = rotateLR;
	}

	public TSEnemyBase spawnTo(ShootingScene parentScene) {
		try {
			return enemyClass.getConstructor(ShootingScene.class, float.class,
					LR.class)
					.newInstance(parentScene, angle, rotateLR);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			System.err.println("エラー：コンストラクタが呼び出せません");
			e.printStackTrace();
		}
		return null;
	}

	public boolean canSpawn(long frame) {
		return this.spawnFrame == frame;
	}
}
