package jp.co.tokiha0s.game.scenes.tokishooting;

import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;
import java.util.Iterator;

import jp.co.tokiha0s.game.classes.GameObject;
import jp.co.tokiha0s.game.classes.character.PopupTextCharacter;
import jp.co.tokiha0s.game.classes.character.TextCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacterImpl.ShootingTeam;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.common.CommonMethod.BackGroundColor;
import jp.co.tokiha0s.game.io.Key;
import jp.co.tokiha0s.game.main.GameSceneManager;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.TSCharacterController;
import jp.co.tokiha0s.game.scenes.tokishooting.gui.moneycaption.TSMoneyCaption;
import jp.co.tokiha0s.game.scenes.tokishooting.gui.weaponcaption.TSWeaponGUI;
import jp.co.tokiha0s.game.scenes.tokishooting.stage.TSStageController;

/**
 * Toki Shooting の基点クラス。
 * Toki Shooting ゲーム内のオブジェクトはすべてこのクラスから派生してゆく。
 * 
 * @author shirakawa
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
		
		characters = add(new TSCharacterController(this));
		stages = add(new TSStageController(this));
		add(new TSMoneyCaption(this));
		add(new TSWeaponGUI(characters));

		cautionCaption = add(new TextCharacter());
		cautionCaption.setScale(0.4f)
				.setColor(new Color(1f, 0.3f, 0f))
				.setX(CENTER_X)
				.setY(CENTER_Y - 70)
				.hide();

		pauseCaption = add(new TextCharacter("PAUSE"));
		pauseCaption.setScale(0.4f)
				.setColor(Color.green)
				.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.hide();
	}

	/**
	 * 自機、敵機、弾丸などはSceneにaddせず、下請けクラスに処理を移譲する。
	 */
	@Override
	public <T extends GameObject> T add(T go) {
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
		add(new PopupTextCharacter(characters.getShip(), str + Math.abs(value)).setColor(Color.yellow));
		money += value;
		return money;
	}

	@Override
	public boolean updateProcess() {
		if (isGameOver()) {
			GameSceneManager.getInstance()
					.gameover();
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
