package jp.co.tkhs.gp15.content.tokishooting.weapons.sniper;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSBulletBase;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSWeaponBase;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingRotateCharacter;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;
import jp.co.tkhs.gp15.system.graphics.font.FontTextureMaker;

/**
 * スナイパー武器。レーザーサイトつき／貫通で便利
 * 
 * @author tokiha0s
 *
 */
public class TSSnipeWeapon extends TSWeaponBase {
    static final float DISTANCE_FROM_OWNER = 8;
    static final int SHOOT_DELAY_FRAME = 60;
    public static final int MAX_CHARGE = 20;
    private static final Texture WEAPON_TEXTURE = TextureLoader
            .loadTexture(TOKISHOOTING_FOLDER_STRING + "weapon-tikuwa.png");
    public static final Texture SNIPE_SITE_TEXTURE = FontTextureMaker.createText("|");
    private static final int RELOAD_COST = 5;

    public TSSnipeWeapon(TokiShootingScene scene, ShootingRotateCharacter owner, LR equipLR) {
        super(scene, owner, equipLR);
        setTexture(WEAPON_TEXTURE);
        setHeight(20);
        setWidth(6);
        setOffsetY(getOffsetY() + 5);
        add(new SnipeSite(scene, this));
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
        return new SnipeBullet(getParentScene(), this);
    }

    private class SnipeSite extends ShootingRotateCharacter {
        private TSSnipeWeapon weapon;

        public SnipeSite(ShootingScene scene, TSSnipeWeapon weapon) {
            super(scene, 0);
            this.weapon = weapon;
            setTexture(SNIPE_SITE_TEXTURE);
            setWidth(5);
            setHeight((int) (WIDTH / 2 * 1.5f));
            setColor(Color.red);
            setAlpha(0.5f);
        }

        @Override
        protected boolean updateProcess() {
            setOffsetX(weapon.getOffsetX());
            setOffsetY(weapon.getOffsetY() + getHeight() / 2 + 3);
            setAngle(weapon.getAngle());
            return super.updateProcess();
        }
    }

    @Override
    public int getReloadCost() {
        return RELOAD_COST;
    }
}
