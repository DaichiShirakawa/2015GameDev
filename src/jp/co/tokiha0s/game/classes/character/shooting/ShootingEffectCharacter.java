package jp.co.tokiha0s.game.classes.character.shooting;

import jp.co.tokiha0s.game.classes.scene.ShootingScene;

/**
 * ダメージ等で発生するエフェクトの基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class ShootingEffectCharacter extends ShootingBulletCharacter {

	public ShootingEffectCharacter(ShootingScene parentScene,
			ShootingCharacter shooter) {
		super(parentScene, shooter, 0);
		destroyAfter(getLifeTime());
		setTeam(ShootingTeam.NO_TEAM);
	}

	abstract protected float getLifeTime();
	
	@Override
	public float damage(float damage) {
		return 0;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		return;
	}
}