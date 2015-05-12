package jp.co.tkhs.gp15.content.tokishooting.weapons.normal;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSBulletBase;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSWeaponBase;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingRotateCharacter;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 通常弾
 * 
 * @author tokiha0s
 *
 */
public class TSNormalWeapon extends TSWeaponBase {
    static final float DISTANCE_FROM_OWNER = 8;
    static final int SHOOT_DELAY_FRAME = 18;
    public static final int MAX_CHARGE = 50;
    public static final Texture WEAPON_TEXTURE = TextureLoader
            .loadTexture(TOKISHOOTING_FOLDER_STRING + "weapon-apple.png");
    private static final int RELOAD_COST = 1;

    public TSNormalWeapon(TokiShootingScene scene, ShootingRotateCharacter owner, LR equipLR) {
        super(scene, owner, equipLR);
        setTexture(WEAPON_TEXTURE);
        setHeight(20);
        setWidth(10);
    }

    @Override
    protected float getDistanceFromOwner() {
        return DISTANCE_FROM_OWNER;
    }

    @Override
    protected int getShootDelayFrame() {
        return SHOOT_DELAY_FRAME;
    }

    @Override
    public int getMaxBullet() {
        return MAX_CHARGE;
    }

    @Override
    protected TSBulletBase getBulletInstance() {
        return new NormalBullet(getParentScene(), this);
    }

    @Override
    public int getReloadCost() {
        return RELOAD_COST;
    }
}
