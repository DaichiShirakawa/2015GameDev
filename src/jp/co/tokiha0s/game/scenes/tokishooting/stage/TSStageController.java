package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import static jp.co.tokiha0s.game.common.Commons.*;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.character.TextCharacter;
import jp.co.tokiha0s.game.classes.scene.GameScene;
import jp.co.tokiha0s.game.io.Key;
import jp.co.tokiha0s.game.main.GameSceneManager;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

/**
 * ステージの遷移を管理するクラス
 * 
 * @author shirakawa
 *
 */
public class TSStageController extends GameScene {
	private TokiShootingScene scene;
	private TextCharacter startText;
	private TextCharacter clearText;
	private StageState stageState = StageState.READY;
	private StageBase currentStage;

	public TSStageController(TokiShootingScene scene) {
		this.scene = scene;
		StageDef.reset();
		currentStage = add(StageDef.getNextStage(scene));
		startText = add(new TextCharacter(getReadyText()));
		clearText = add(new TextCharacter("STAGE CLEAR\n" + "press enter"));
		startText.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setScale(0.5f)
				.setColor(Color.orange.brighter());
		clearText.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setScale(0.5f)
				.setColor(Color.orange.brighter());
	}

	private String getReadyText() {
		return StageDef.getStageName() + "  " + StageDef.getStageDescript()
				+ "\n" + "press enter";
	}

	@Override
	public boolean updateProcess() {
		switch (stageState) {
		case READY:
			startText.show();
			clearText.hide();
			break;
		case PLAYING:
			startText.hide();
			clearText.hide();
			if (currentStage.isClear()) {
				stageState = StageState.PRE_CLEAR;
			}
			break;
		case PRE_CLEAR:
			scene.clearEnemies();
			nextStage();
			if (currentStage == null) {
				stageState = StageState.ALL_CLEAR;
			} else {
				stageState = StageState.CLEAR;
			}
			break;
		case CLEAR:
		case ALL_CLEAR:
			startText.hide();
			clearText.show();
			break;
		}
		return !scene.isPausing();
	}

	@Override
	protected boolean inputProcess() {
		switch (stageState) {
		case READY:
			if (Key.RETURN.isPressed()) {
				stageState = StageState.PLAYING;
			}
			break;
		case CLEAR:
			if (Key.RETURN.isPressed()) {
				stageState = StageState.READY;
			}
			break;
		case ALL_CLEAR:
			if (Key.RETURN.isPressed()) {
				GameSceneManager.getInstance()
						.gameReset();
			}
			break;
		default:
			break;
		}
		return !scene.isBreakTime();
	}

	private void nextStage() {
		currentStage.destroy();
		currentStage = add(StageDef.getNextStage(scene));
		if (StageDef.allClear()) {
			clearText.updateText("GAME CLEAR!\n" + "おめでとう!");
			return;
		}
		startText.updateText(getReadyText());
	}

	private enum StageState {
		READY,
		PLAYING,
		PRE_CLEAR,
		CLEAR,
		ALL_CLEAR,
	}

	public boolean isBreakTime() {
		return (stageState == StageState.READY)
				|| (stageState == StageState.CLEAR);
	}

	public boolean isPlaying() {
		return (stageState == StageState.PLAYING);
	}

	/**
	 * 強制クリア
	 */
	public void doClear() {
		if (stageState != StageState.PLAYING) {
			return;
		}

		stageState = StageState.PRE_CLEAR;
	}
}
