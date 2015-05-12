package jp.co.tokiha0s.game.classes.character.shooting;

import static java.lang.Math.*;
import jp.co.tokiha0s.game.classes.character.DamagePopupCharacter;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.texture.Texture;

/**
 * シューティング用
 * 弾丸キャラクター基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class ShootingBulletCharacter extends ShootingCharacterImpl {
	private ShootingCharacter shooter = null;
	private ShootingCharacter target = null;
	private float remainRange = 0;

	public ShootingBulletCharacter(ShootingScene parentScene,
			ShootingCharacter shooter, float power, float hp) {
		super(parentScene, power, hp);
		this.shooter = shooter;
		this.remainRange = getBulletRange();
		setTeam(shooter.getTeam());
		setX(shooter.getX());
		setY(shooter.getY());
		setWidth(getBulletSize());
		setHeight(getBulletSize());
		setTexture(getBulletTexture());
	}

	public ShootingBulletCharacter(ShootingScene parentScene,
			ShootingCharacter shooter, float power) {
		this(parentScene, shooter, power, 1);
	}

	abstract protected Texture getBulletTexture();

	abstract protected int getBulletSize();

	public ShootingCharacter getParentCharacter() {
		return getShooter();
	}

	@Override
	public boolean updateProcess() {
		remainRange -= sqrt(pow(getVX(), 2) + pow(getVY(), 2));
		if (remainRange <= 0) {
			destroy();
		}
		return super.updateProcess();
	}

	public float getBulletRange() {
		return 200;
	}

	protected ShootingCharacter getShooter() {
		return shooter;
	}

	public ShootingCharacter getTarget() {
		return target;
	}

	protected void setTarget(ShootingCharacter target) {
		this.target = target;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		if ((0 < getPower()) && !(target instanceof ShootingBulletCharacter)) {
			getParentScene().add(
					new DamagePopupCharacter(target, (int) getPower()));
		}
		super.hitEffectTo(target);
	}

	@Override
	public float damage(float damage) {
		super.damage(damage);
		if (zeroHP()) {
			destroy();
		}
		return getHP();

	}
}
