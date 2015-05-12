package jp.co.tkhs.gp15.content.gameover;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.main.FPSManager;
import jp.co.tkhs.gp15.main.GameSceneMaster;
import jp.co.tkhs.gp15.system.game.character.impl.ScreenShotCharacter;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;
import jp.co.tkhs.gp15.system.game.scene.GameScene;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * 呼ばれた時点のスクリーンショットを撮って「GAME OVER」メッセージを表示するシーン。
 *
 * @author tokiha0s
 *
 */
public class GameOverScene extends GameScene {
    TextCharacter gameoverText;

    public GameOverScene() {
        add(new ScreenShotCharacter());

        gameoverText = new TextCharacter("-GAME OVER-");
        gameoverText.setX(CENTER_X).setY(CENTER_Y + 70).setColor(Color.red).setScale(0.5f);
        add(gameoverText);

        TextCharacter gameoverMessage = new TextCharacter("press return to reset game");
        gameoverMessage.setX(CENTER_X).setY(CENTER_Y + 50).setColor(Color.red).setScale(0.3f);
        add(gameoverMessage);
    }

    @Override
    public boolean inputProcess() {
        if (Key.RETURN.isPressed()) {
            GameSceneMaster.getInstance().gameReset();
        }
        return super.inputProcess();
    }

    @Override
    protected boolean updateProcess() {
        if (FPSManager.getTotalFrame() % 60 == 0) {
            gameoverText.toggleVisible();
        }
        return true;
    }
}
