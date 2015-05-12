package jp.co.tkhs.gp15.main;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;
import static org.lwjgl.opengl.GL11.*;
import jp.co.tkhs.gp15.system.io.Key;

import org.lwjgl.opengl.Display;

/**
 * すべてのゲームオブジェクトの処理はこのループから始まる。
 *
 * @author tokiha0s
 *
 */
class GameLoop implements Runnable {
    private GameSceneMaster sceneMaster;

    public GameLoop(GameSceneMaster sceneMaster) {
        this.sceneMaster = sceneMaster;
    }

    @Override
    public void run() {
        while (!Display.isCloseRequested()) {
            initBuffer();

            Key.update();
            sceneMaster.update();
            sceneMaster.render();
            FPSManager.update();
            updateDisplay();
        }
    }

    private void initBuffer() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }


    private void updateDisplay() {
        Display.update();
        Display.sync(FPS);
        Display.setTitle("FRAME:" + String.valueOf(FPSManager.getFrameCycle()) + "FPS:"
                + floatTo0d0(FPSManager.getCalcedFPS()));
    }
}
