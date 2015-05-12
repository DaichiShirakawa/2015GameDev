package jp.co.tokiha0s.game.classes.character.shooting;

import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.GameObjectImpl;
import jp.co.tokiha0s.game.classes.character.GameCharacter;
import jp.co.tokiha0s.game.classes.character.GameCharacterMoveMode;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;

/**
 * 画面中心を基点として、円を描いて回転する動きを持つキャラクターの基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class ShootingRotateCharacter extends ShootingCharacterImpl {
	// 自身から見て、この補正値の距離だけ前後に進む。
	private float offsetY;
	private float vOffsetY;
	// 自身から見て、この補正値の距離だけ横に進む。
	private float offsetX;
	private float vOffsetX;
	private DamageVibrator damageVibrator = new DamageVibrator();

	public ShootingRotateCharacter(ShootingScene scene, float power) {
		super(scene, power);
		setMoveModeX(GameCharacterMoveMode.UNLIMITED);
		setMoveModeY(GameCharacterMoveMode.UNLIMITED);
		add(damageVibrator);
	}

	public ShootingRotateCharacter(ShootingScene scene, float power, float hp) {
		super(scene, power, hp);
		setMoveModeX(GameCharacterMoveMode.UNLIMITED);
		setMoveModeY(GameCharacterMoveMode.UNLIMITED);
		add(damageVibrator);
	}

	@Override
	protected boolean updateProcess() {
		setOffsetY(getOffsetY() + getVOffsetY());
		setOffsetX(getOffsetX() + getVOffsetX());
		return super.updateProcess();
	}

	@Override
	public GameCharacter setAngle(float angle) {
		super.setAngle(angle);
		double theta = getTheta();
		// thisから見た縦方向の移動
		setX(CENTER_X + getOffsetY() * (float) Math.sin(-theta));
		setY(CENTER_Y + getOffsetY() * (float) Math.cos(-theta));
		// thisから見た横方向の移動
		setX(getX() + getOffsetX() * (float) Math.cos(theta));
		setY(getY() + getOffsetX() * (float) Math.sin(theta));
		return this;
	}

	/**
	 * 座標演算に使うθを返す
	 */
	protected double getTheta() {
		return Math.toRadians(getAngle() + damageVibrator.getAngle());
	}

	@Override
	public float damage(float damage) {
		if (damage > 0) {
			damageVibrator.init();
		}
		return super.damage(damage);
	}

	public float getOffsetY() {
		return offsetY;
	}

	public ShootingRotateCharacter setOffsetY(float offsetY) {
		this.offsetY = offsetY;
		return this;
	}

	public float getVOffsetY() {
		return vOffsetY;
	}

	public ShootingRotateCharacter setVOffsetY(float vOffsetY) {
		this.vOffsetY = vOffsetY;
		return this;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getVOffsetX() {
		return vOffsetX;
	}

	public void setVOffsetX(float vOffsetX) {
		this.vOffsetX = vOffsetX;
	}

	private class DamageVibrator extends GameObjectImpl {
		private float damageVibrateOffset = 0;
		private float damageVibrateTheta = 0;

		@Override
		protected boolean updateProcess() {
			damageVibrateOffset *= 0.90f;
			damageVibrateTheta += 1;
			return true;
		}

		public void init() {
			damageVibrateOffset = 3;
			damageVibrateTheta = 0;
		}

		public double getAngle() {
			return Math.sin(damageVibrateTheta) * damageVibrateOffset;
		}

		@Override
		protected void renderProcess() {
			//
		}
	}
}