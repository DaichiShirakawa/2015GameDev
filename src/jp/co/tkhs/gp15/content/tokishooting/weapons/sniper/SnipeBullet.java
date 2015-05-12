package jp.co.tkhs.gp15.content.tokishooting.weapons.sniper;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;
import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSBulletBase;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBasicEffect;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingCharacterImpl;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * スナイパー弾
 * 
 * @author tokiha0s
 *
 */
class SnipeBullet extends TSBulletBase {
    private static final int BULLET_POWER = 3;
    private static final int BULLET_HP = 5;
    private static final int BULLET_SIZE = 4;
    private static final int BULLET_RANGE = 400;
    private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
            + "bullet-tikuwa.png");
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
