package jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth;

import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.sniper.TSSnipeWeapon;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * スナイパー砦
 * 
 * @author shirakawa
 *
 */
class SnipeBasion extends TSBasionBase {

	public SnipeBasion(ShootingScene scene, TSEarth earth) {
		super(scene, earth, TSSnipeWeapon.class);
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
				+ "base-tikuwa.png"));
		setWidth(20);
		setHeight(20);
		setOffsetY(20);
	}

	@Override
	protected float getPlaceAngle() {
		return 120;
	}

}
