package jp.co.tokiha0s.game.scenes.tokishooting.weapons.enemies;

import static jp.co.tokiha0s.game.common.CommonMethod.*;
import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.character.shooting.ShootingBasicEffect;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacterImpl;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.TSBulletBase;
import jp.co.tokiha0s.game.texture.Texture;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * 中ボスが発射する敵弾
 * 
 * @author shirakawa
 *
 */
public class TSNormalEnemyBullet extends TSBulletBase{
	private static final int BULLET_POWER = 1;
	private static final int BULLET_SIZE = 20;
	private static final int BULLET_RANGE = 300;
	private static final Texture TEXTURE = TextureLoader.loadTexture(IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png");
	private static final float SPEED = 0.2f;

	public TSNormalEnemyBullet(TokiShootingScene parentScene,
			ShootingCharacterImpl shooter) {
		super(parentScene, shooter, BULLET_POWER);

		setColor(Color.white);

		double theta = Math.toRadians(getShooter().getAngle() - 180);
		setVX(SPEED * (float) Math.sin(-theta));
		setVY(SPEED * (float) Math.cos(-theta));
		setVAngle(3);
		setColor(Color.orange);
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
	public float damage(float damage) {
		for (int i = 0; i < 2; i++) {
			shoot(new Effect(getParentScene(), this));
		}
		return super.damage(damage);
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		for (int i = 0; i < 2; i++) {
			shoot(new Effect(getParentScene(), this));
		}
	}

	private class Effect extends ShootingBasicEffect {
		public Effect(ShootingScene parentScene, ShootingCharacterImpl shooter) {
			super(parentScene, shooter);
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.5f, 0.8f));
		}
	}
}
