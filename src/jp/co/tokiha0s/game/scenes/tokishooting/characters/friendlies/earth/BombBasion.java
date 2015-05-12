package jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth;

import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.bomb.TSBombWeapon;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * ボム砦
 * 
 * @author shirakawa
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
