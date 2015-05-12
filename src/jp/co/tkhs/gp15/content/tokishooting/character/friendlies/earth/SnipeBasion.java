package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.content.tokishooting.weapons.sniper.TSSnipeWeapon;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * スナイパー砦
 * 
 * @author tokiha0s
 *
 */
class SnipeBasion extends TSBasionBase {

    public SnipeBasion(ShootingScene scene, TSEarth earth) {
        super(scene, earth, TSSnipeWeapon.class);
        setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "base-tikuwa.png"));
        setWidth(20);
        setHeight(20);
        setOffsetY(20);
    }

    @Override
    protected float getPlaceAngle() {
        return 120;
    }

}
