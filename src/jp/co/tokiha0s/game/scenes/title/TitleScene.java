package jp.co.tokiha0s.game.scenes.title;

import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.character.TextCharacter;
import jp.co.tokiha0s.game.classes.scene.GameScene;
import jp.co.tokiha0s.game.common.CommonMethod.BackGroundColor;
import jp.co.tokiha0s.game.io.Key;
import jp.co.tokiha0s.game.main.FPSManager;
import jp.co.tokiha0s.game.main.GameSceneManager;
import jp.co.tokiha0s.game.main.GameSceneManager.SceneCollection;

/**
 * 起動時に表示されるおなじみの画面
 * 
 * PRESS START!
 * 
 * @author shirakawa
 * 
 */
public class TitleScene extends GameScene {
	private static final SceneCollection nextScene = SceneCollection.EDF;
	private TextCharacter pressStartText;

	public TitleScene() {
		BackGroundColor.WHITE.set();
		
		add(new BackGround());
		
		pressStartText = add(new TextCharacter("PRESS RETURN KEY!"));
		pressStartText.setColor(Color.white)
				.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f);
	}

	@Override
	public boolean updateProcess() {
		if (FPSManager.totalFrame() % 45 == 0) {
			pressStartText.toggleVisible();
		}

		if (Key.RETURN.isPressed()) {
			GameSceneManager.getInstance()
					.changeSceneIfNotNull(nextScene);
		}
		return false;
	}
}