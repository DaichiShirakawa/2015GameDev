package jp.co.tokiha0s.game.scenes.tokishooting.weapons;

import jp.co.tokiha0s.game.classes.character.shooting.ShootingBulletCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacter;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.enemies.TSEnemyBase;

/**
 * 弾丸の基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class TSBulletBase extends ShootingBulletCharacter {

	public TSBulletBase(TokiShootingScene parentScene, ShootingCharacter shooter,
			float power) {
		this(parentScene, shooter, power, 1);
	}

	public TSBulletBase(TokiShootingScene parentScene, ShootingCharacter shooter,
			float power, float hp) {
		super(parentScene, shooter, power, hp);
	}

	@Override
	public TokiShootingScene getParentScene() {
		return (TokiShootingScene) super.getParentScene();
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		if (target.isDestroyed() && target instanceof TSEnemyBase) {
			((TokiShootingScene) getParentScene()).addMoney(((TSEnemyBase) target).getMoney());
		}
	}
}
