package jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth;

import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.normal.TSNormalWeapon;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * パチンコ砦
 * 
 * @author shirakawa
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
