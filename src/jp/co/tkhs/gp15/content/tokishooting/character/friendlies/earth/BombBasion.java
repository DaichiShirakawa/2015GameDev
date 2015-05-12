package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.content.tokishooting.weapons.bomb.TSBombWeapon;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * ボム砦
 * 
 * @author tokiha0s
 *
 */
class BombBasion extends TSBasionBase {

    public BombBasion(ShootingScene scene, TSEarth earth) {
        super(scene, earth, TSBombWeapon.class);
        setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "base-suika.png"));
        setWidth(20);
        setHeight(20);
        setOffsetY(20);
    }

    @Override
    protected float getPlaceAngle() {
        return 240;
    }

}
