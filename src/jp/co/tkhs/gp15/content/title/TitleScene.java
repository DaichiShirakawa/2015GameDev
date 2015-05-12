package jp.co.tkhs.gp15.content.title;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.main.FPSManager;
import jp.co.tkhs.gp15.main.GameSceneMaster;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;
import jp.co.tkhs.gp15.system.game.scene.GameScene;
import jp.co.tkhs.gp15.system.game.scene.Scenes;
import jp.co.tkhs.gp15.system.game.utils.GameUtils.BackGroundColor;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * 起動時に表示されるおなじみの画面
 *
 * PRESS START!
 *
 * @author tokiha0s
 *
 */
public class TitleScene extends GameScene {
    private static final Scenes nextScene = Scenes.TOKI_SHOOTING;
    private TextCharacter pressStartText;

    public TitleScene() {
        BackGroundColor.WHITE.set();

        add(new TitleBackGround());

        pressStartText = new TextCharacter("PRESS RETURN KEY!");
        pressStartText.setColor(Color.white).setX(CENTER_X).setY(CENTER_Y).setScale(0.5f);
        add(pressStartText);
    }

    @Override
    public boolean updateProcess() {
        if (FPSManager.getTotalFrame() % 45 == 0) {
            pressStartText.toggleVisible();
        }

        if (Key.RETURN.isPressed()) {
            GameSceneMaster.getInstance().changeSceneIfNotNull(nextScene);
        }
        return false;
    }
}
