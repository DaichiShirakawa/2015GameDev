package jp.co.tkhs.gp15.content.tokishooting;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;
import java.util.Iterator;

import jp.co.tkhs.gp15.content.tokishooting.character.TSCharacterController;
import jp.co.tkhs.gp15.content.tokishooting.gui.moneycaption.TSMoneyCaption;
import jp.co.tkhs.gp15.content.tokishooting.gui.weaponcaption.TSWeaponGUI;
import jp.co.tkhs.gp15.content.tokishooting.stage.TSStageController;
import jp.co.tkhs.gp15.main.GameSceneMaster;
import jp.co.tkhs.gp15.system.game.GameObject;
import jp.co.tkhs.gp15.system.game.character.impl.PopupTextCharacter;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingCharacterImpl.ShootingTeam;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.GameUtils.BackGroundColor;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * Toki Shooting の基点クラス。 Toki Shooting ゲーム内のオブジェクトはすべてこのクラスから派生してゆく。
 *
 * @author tokiha0s
 *
 */
public class TokiShootingScene extends ShootingScene {
    private int money = 100;
    private TSCharacterController characters;
    private TSStageController stages;
    private boolean pausing = false;
    private TextCharacter cautionCaption;
    private int cautionFrame;
    private TextCharacter pauseCaption;

    public TokiShootingScene() {
        BackGroundColor.DARK_BLUE.set();

        characters = new TSCharacterController(this);
        add(characters);

        stages = new TSStageController(this);
        add(stages);
        add(new TSMoneyCaption(this));
        add(new TSWeaponGUI(characters));


        cautionCaption = new TextCharacter();
        cautionCaption.setScale(0.4f).setColor(new Color(1f, 0.3f, 0f)).setX(CENTER_X)
                .setY(CENTER_Y - 70).hide();
        add(cautionCaption);

        pauseCaption = new TextCharacter("PAUSE");
        pauseCaption.setScale(0.4f).setColor(Color.green).setX(CENTER_X).setY(CENTER_Y + 70).hide();
        add(pauseCaption);
    }

    /**
     * 自機、敵機、弾丸などはSceneにaddせず、下請けクラスに処理を移譲する。
     */
    @Override
    public <T extends GameObject> boolean add(T go) {
        if (go instanceof ShootingCharacter) {
            return characters.add(go);
        } else {
            return super.add(go);
        }
    }

    public int getMoney() {
        return money;
    }

    public int addMoney(int value) {
        String str = (value < 0) ? "-$" : "$";
        add(new PopupTextCharacter(characters.getShip(), str + Math.abs(value))
                .setColor(Color.yellow));
        money += value;
        return money;
    }

    @Override
    public boolean updateProcess() {
        if (isGameOver()) {
            GameSceneMaster.getInstance().gameover();
            return false;
        }
        if (isPausing()) {
            inputProcess();
            return false;
        }
        if (cautionFrame-- <= 0) {
            cautionCaption.hide();
        }
        return super.updateProcess();
    }

    public boolean isPlaying() {
        return stages.isPlaying();
    }

    public boolean isBreakTime() {
        return stages.isBreakTime();
    }

    public boolean isPausing() {
        return pausing;
    }

    public boolean isGameOver() {
        return !(characters.earthArrive());
    }

    @Override
    public boolean inputProcess() {
        // テスト用強制クリア。
        if (Key.A.isPressed()) {
            stages.doClear();
        }

        if (Key.P.isPressed()) {
            if (isPlaying()) {
                pauseCaption.toggleVisible();
                pausing = !pausing;
            }
        }

        return super.inputProcess();
    }

    /**
     * 生存している敵属性のキャラや弾を一掃する
     */
    public void clearEnemies() {
        for (Iterator<GameObject> ite = characters.getIterator(); ite.hasNext();) {
            ShootingCharacter character = (ShootingCharacter) ite.next();
            if (character.getTeam() == ShootingTeam.ENEMY_TEAM) {
                character.damage(Integer.MAX_VALUE);
                ite.remove();
            }
        }
    }

    /**
     * 弾切れや金欠の場合、一定時間目立つメッセージを表示できる
     */
    public void showCaution(String text) {
        cautionCaption.updateText(text);
        cautionCaption.show();
        cautionFrame = 45;
    }
}
