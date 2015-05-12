package jp.co.tkhs.gp15.content.tokishooting.stage;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.main.GameSceneMaster;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;
import jp.co.tkhs.gp15.system.game.scene.GameScene;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * ステージの遷移を管理するクラス
 *
 * @author tokiha0s
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
        currentStage = StageDef.getNextStage(scene);
        add(currentStage);

        startText = new TextCharacter(getReadyText());
        startText.setX(CENTER_X).setY(CENTER_Y + 70).setScale(0.5f)
                .setColor(Color.orange.brighter());
        add(startText);

        clearText = new TextCharacter("STAGE CLEAR\n" + "press enter");
        clearText.setX(CENTER_X).setY(CENTER_Y + 70).setScale(0.5f)
                .setColor(Color.orange.brighter());
        add(clearText);
    }

    private String getReadyText() {
        return StageDef.getStageName() + "  " + StageDef.getStageDescript() + "\n" + "press enter";
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
                    GameSceneMaster.getInstance().gameReset();
                }
                break;
            default:
                break;
        }
        return !scene.isBreakTime();
    }

    private void nextStage() {
        currentStage.destroy();
        currentStage = StageDef.getNextStage(scene);
        add(currentStage);
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
        return (stageState == StageState.READY) || (stageState == StageState.CLEAR);
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
