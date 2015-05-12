package jp.co.tokiha0s.game.scenes.tokishooting.characters.enemies;

import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.texture.Texture;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * 回転軌道でせまってくる敵
 * 
 * @author shirakawa
 *
 */
public class TSEnemyRotate extends TSEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 1;
	private static final int SIZE = 25;
	private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
			+ "ika.png");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.2f;
	private static final int MONEY = 10;
	private static final TSEnemyProperty property = new TSEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public TSEnemyRotate(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected Color createColor() {
		return null;
	}

}
