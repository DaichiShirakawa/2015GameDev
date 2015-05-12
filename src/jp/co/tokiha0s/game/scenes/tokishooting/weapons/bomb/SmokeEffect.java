package jp.co.tokiha0s.game.scenes.tokishooting.weapons.bomb;

import static jp.co.tokiha0s.game.common.CommonMethod.*;
import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.character.shooting.ShootingBasicEffect;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacter;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.texture.Texture;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * ボム弾やボム命中時に発生する煙エフェクト
 * 
 * @author shirakawa
 *
 */
class SmokeEffect extends ShootingBasicEffect {
	private static final Texture SMOKETEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
			+ "smoke.png");
	private boolean reverse;

	public SmokeEffect(ShootingScene parentScene, ShootingCharacter source,
			boolean reverse) {
		super(parentScene, source);
		this.reverse = reverse;
		float tmp = random(0.5f, 1);
		setColor(new Color(tmp, tmp, tmp));
		setWidth(20);
		setHeight(20);
		setScale(tmp = random(0.4f, 0.7f));
		setTexture(SMOKETEXTURE);
	}

	@Override
	protected void setMoveSpeeds() {
		if (reverse) {
			setVX(-getShooter().getVX() * 0.3f);
			setVY(-getShooter().getVY() * 0.3f);
		} else {
			setVX(getShooter().getVX() * 0.3f);
			setVY(getShooter().getVY() * 0.3f);
		}
	}

}