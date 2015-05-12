package jp.co.tkhs.gp15.content.tokishooting.weapons;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.enemies.TSEnemyBase;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBulletCharacter;

/**
 * 弾丸の基底クラス
 * 
 * @author tokiha0s
 *
 */
public abstract class TSBulletBase extends ShootingBulletCharacter {

    public TSBulletBase(TokiShootingScene parentScene, ShootingCharacter shooter, float power) {
        this(parentScene, shooter, power, 1);
    }

    public TSBulletBase(TokiShootingScene parentScene, ShootingCharacter shooter, float power,
            float hp) {
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
