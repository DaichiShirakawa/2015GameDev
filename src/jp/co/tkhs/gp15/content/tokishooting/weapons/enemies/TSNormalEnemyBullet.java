package jp.co.tkhs.gp15.content.tokishooting.weapons.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSBulletBase;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBasicEffect;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingCharacterImpl;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 中ボスが発射する敵弾
 * 
 * @author tokiha0s
 *
 */
public class TSNormalEnemyBullet extends TSBulletBase {
    private static final int BULLET_POWER = 1;
    private static final int BULLET_SIZE = 20;
    private static final int BULLET_RANGE = 300;
    private static final Texture TEXTURE = TextureLoader.loadTexture(IMAGE_FOLDER_STRING
            + "dotTokiIcon.png");
    private static final float SPEED = 0.2f;

    public TSNormalEnemyBullet(TokiShootingScene parentScene, ShootingCharacterImpl shooter) {
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
