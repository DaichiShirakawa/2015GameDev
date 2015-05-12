package jp.co.tkhs.gp15.content.tokishooting.character.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth.TSEarth;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.ship.TSShip;
import jp.co.tkhs.gp15.system.game.character.impl.DamagePopupCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBasicEffect;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingRotateCharacter;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

/**
 * 敵キャラの基底クラス
 * 
 * @author tokiha0s
 *
 */
public abstract class TSEnemyBase extends ShootingRotateCharacter {

    TSEnemyProperty property;

    public TSEnemyBase(ShootingScene parentScene, float bornAngle, LR lr, TSEnemyProperty property) {
        super(parentScene, property.power, property.hp);
        this.property = property;
        setWidth(property.size);
        setHeight(property.size);
        // どの角度だろうと外周ピッタリの位置で生成する計算式
        float distanceToOuther =
                (float) (1 / Math.max(Math.abs(Math.sin(Math.toRadians(bornAngle))),
                        Math.abs(Math.cos(Math.toRadians(bornAngle)))));
        setOffsetY((WIDTH / 2 * distanceToOuther) + property.size);
        setVOffsetY(-property.fallSpeed);
        setAngle(bornAngle);
        setVAngle(property.rotateSpeed * lr.signum());
        setTexture(property.texture);
        setTeam(ShootingTeam.ENEMY_TEAM);
        setColor(createColor());
    }

    protected abstract Color createColor();

    @Override
    public TokiShootingScene getParentScene() {
        return (TokiShootingScene) super.getParentScene();
    }

    @Override
    protected void destroyProcess() {
        super.destroyProcess();
        for (int i = 0; i < 5; i++) {
            getParentScene().add(new ShootingBasicEffect(getParentScene(), this));
        }
    }

    public int getMoney() {
        return property.money;
    }

    @Override
    public void hitEffectTo(ShootingCharacter target) {
        if (target instanceof TSShip) {
            return;
        }
        if ((0 < getPower()) && (target instanceof TSEarth)) {
            getParentScene().add(new DamagePopupCharacter(target, (int) getPower()));
        }
        super.hitEffectTo(target);
    }
}
