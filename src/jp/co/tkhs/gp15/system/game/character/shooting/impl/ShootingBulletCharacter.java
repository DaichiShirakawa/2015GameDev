package jp.co.tkhs.gp15.system.game.character.shooting.impl;

import static java.lang.Math.*;
import jp.co.tkhs.gp15.system.game.character.impl.DamagePopupCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;

/**
 * シューティング用 弾丸キャラクター基底クラス
 * 
 * @author tokiha0s
 *
 */
public abstract class ShootingBulletCharacter extends ShootingCharacterImpl {
    private ShootingCharacter shooter = null;
    private ShootingCharacter target = null;
    private float remainRange = 0;

    public ShootingBulletCharacter(ShootingScene parentScene, ShootingCharacter shooter,
            float power, float hp) {
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

    public ShootingBulletCharacter(ShootingScene parentScene, ShootingCharacter shooter, float power) {
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
            getParentScene().add(new DamagePopupCharacter(target, (int) getPower()));
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
