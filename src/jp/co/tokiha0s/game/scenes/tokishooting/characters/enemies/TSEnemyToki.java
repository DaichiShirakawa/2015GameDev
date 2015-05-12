package jp.co.tokiha0s.game.scenes.tokishooting.characters.enemies;

import static jp.co.tokiha0s.game.common.CommonMethod.*;
import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.common.Commons;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.main.FPSManager;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.enemies.TSNormalEnemyBullet;
import jp.co.tokiha0s.game.texture.Texture;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * 中ボス敵
 * 
 * @author shirakawa
 *
 */
public class TSEnemyToki extends TSEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 10;
	private static final int SIZE = 40;
	private static final Texture TEXTURE = TextureLoader.loadTexture(Commons.IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png");
	private static final float ROTATE_SPEED = 0.4f;
	private static final float FALL_SPEED = 0.3f;
	private static final int MONEY = 70;
	private static final TSEnemyProperty property = new TSEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public TSEnemyToki(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected boolean updateProcess() {
		if (getOffsetY() < (WIDTH / 2) - SIZE) {
			setVOffsetY(getVOffsetY() * 0.95f);
		}
		// ランダムで射撃
		if ((FPSManager.totalFrame() % 60 == 0) && persentOf(15)) {
			((TokiShootingScene) getParentScene()).add(new TSNormalEnemyBullet(
					getParentScene(), this));
		}
		return super.updateProcess();
	}

	@Override
	protected Color createColor() {
		return new Color(random(0.4f, 0.6f), random(0.6f, 0.9f), random(0.5f,
				0.7f));
	}

}
