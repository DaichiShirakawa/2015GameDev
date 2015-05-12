package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.content.tokishooting.weapons.normal.TSNormalWeapon;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * パチンコ砦
 * 
 * @author tokiha0s
 *
 */
class NormalBasion extends TSBasionBase {

    public NormalBasion(ShootingScene scene, TSEarth earth) {
        super(scene, earth, TSNormalWeapon.class);
        setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "base-apple.png"));
        setWidth(15);
        setHeight(15);
        setOffsetY(23);
    }

    @Override
    protected float getPlaceAngle() {
        return 0;
    }

}
