package jp.co.tokiha0s.game.main;

import static org.lwjgl.opengl.GL11.*;
import jp.co.tokiha0s.game.io.Key;

import org.lwjgl.opengl.Display;

/**
 * すべてのゲームオブジェクトの処理はこのループから始まる。
 * 
 * @author shirakawa
 *
 */
class GameLoop implements Runnable {
	private GameSceneManager sceneManager;

	public GameLoop(GameSceneManager sceneMaster) {
		this.sceneManager = sceneMaster;
	}

	@Override
	public void run() {
		FPSManager fpsManager = FPSManager.getInstance();

		while (!Display.isCloseRequested()) {
			initBuffer();

			Key.update();

			sceneManager.update();
			sceneManager.render();

			fpsManager.update();
		}
	}

	private void initBuffer() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

}
