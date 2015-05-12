package jp.co.tokiha0s.game.scenes.tokishooting.weapons.sniper;

import static jp.co.tokiha0s.game.common.CommonMethod.*;
import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingBasicEffect;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacterImpl;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.TSBulletBase;
import jp.co.tokiha0s.game.texture.Texture;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * スナイパー弾
 * 
 * @author shirakawa
 *
 */
class SnipeBullet extends TSBulletBase {
	private static final int BULLET_POWER = 3;
	private static final int BULLET_HP = 5;
	private static final int BULLET_SIZE = 4;
	private static final int BULLET_RANGE = 400;
	private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "bullet-tikuwa.png");
	private static final float SPEED = 15;

	public SnipeBullet(TokiShootingScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter, BULLET_POWER, BULLET_HP);

		double theta = Math.toRadians(-getShooter().getAngle());
		setVX(SPEED * (float) Math.sin(theta));
		setVY(SPEED * (float) Math.cos(theta));
		setHeight(getHeight() * 4);
		setAngle(shooter.getAngle());
	}

	@Override
	public float getBulletRange() {
		return BULLET_RANGE;
	}

	@Override
	public int getBulletSize() {
		return BULLET_SIZE;
	}

	@Override
	public Texture getBulletTexture() {
		return TEXTURE;
	}

	@Override
	public float getPower() {
		return BULLET_POWER;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		for (int i = 0; i < 4; i++) {
			shoot(new Effect(getParentScene(), this));
		}
	}

	private class Effect extends ShootingBasicEffect {
		public Effect(ShootingScene parentScene, ShootingCharacterImpl shooter) {
			super(parentScene, shooter);
			setScale(random(1f, 3f));
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.5f, 0.8f));
		}
	}
}
