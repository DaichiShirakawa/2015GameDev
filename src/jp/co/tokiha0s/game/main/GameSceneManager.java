package jp.co.tokiha0s.game.main;

import jp.co.tokiha0s.game.classes.scene.GameScene;
import jp.co.tokiha0s.game.io.Key;
import jp.co.tokiha0s.game.scenes.gameover.GameOverScene;
import jp.co.tokiha0s.game.scenes.title.TitleScene;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

/**
 * 各シーンの切り替えを担当するシングルトン
 * 
 * @author shirakawa
 * 
 */
public class GameSceneManager extends GameScene {
	private static GameSceneManager instance;

	private GameScene currentScene;

	private GameSceneManager() {
		// hide
		currentScene = add(new TitleScene());
	}

	public static GameSceneManager getInstance() {
		if (null == instance) {
			instance = new GameSceneManager();
		}
		return instance;
	}

	@Override
	public boolean updateProcess() {
		changeSceneIfNotNull(SceneCollection.scanChangeScene());
		return true;
	}

	public void changeSceneIfNotNull(GameScene newScene) {
		if (newScene == null) {
			return;
		}
		currentScene.destroy();
		currentScene = add(newScene);
	}

	public void changeSceneIfNotNull(SceneCollection nextscene) {
		changeSceneIfNotNull(nextscene.newInstance());
	}

	public void gameReset() {
		changeSceneIfNotNull(new TitleScene());
	}

	public void gameover() {
		changeSceneIfNotNull(new GameOverScene(currentScene));
	}

	public enum SceneCollection {
		TITLE(Key.ESCAPE, TitleScene.class),
		EDF(Key.NULL, TokiShootingScene.class), ;

		private final Key trigger;
		private Class<? extends GameScene> sceneClass;

		private SceneCollection(Key trigger,
				Class<? extends GameScene> callClass) {
			this.trigger = trigger;
			this.sceneClass = callClass;
		}

		private GameScene newInstance() {
			try {
				return sceneClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				System.err.println(sceneClass.getName()
						+ " クラスのインスタンス生成でエラーが発生しました。");
				e.printStackTrace();
				return null;
			}
		}

		public static GameScene scanChangeScene() {
			for (SceneCollection scene : SceneCollection.values()) {
				if (scene.trigger.isPressed()) {
					return scene.newInstance();
				}
			}
			return null;
		}
	}
}
