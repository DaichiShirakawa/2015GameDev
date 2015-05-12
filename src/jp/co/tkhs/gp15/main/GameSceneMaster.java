package jp.co.tkhs.gp15.main;

import jp.co.tkhs.gp15.content.title.TitleScene;
import jp.co.tkhs.gp15.system.game.scene.GameScene;
import jp.co.tkhs.gp15.system.game.scene.Scenes;

/**
 * 各シーンの切り替えを担当するシングルトン
 *
 * @author tokiha0s
 *
 */
public class GameSceneMaster {
    private static final GameSceneMaster instance = new GameSceneMaster();
    private GameScene currentScene = new TitleScene();

    private GameSceneMaster() {
        // hide
    }

    public static GameSceneMaster getInstance() {
        return instance;
    }

    public void update() {
        for (Scenes scene : Scenes.values()) {
            if (scene.getTrigger().isPressed()) {
                changeSceneIfNotNull(scene);
            }
        }
        currentScene.update();
    }

    public void render() {
        currentScene.render();
    }

    public void changeSceneIfNotNull(Scenes nextScene) {
        GameScene scene = nextScene.newInstance();
        if (scene == null) {
            return;
        }
        currentScene.destroy();
        currentScene = scene;
    }

    public void gameReset() {
        changeSceneIfNotNull(Scenes.TITLE);
    }

    public void gameover() {
        changeSceneIfNotNull(Scenes.GAMEOVER);
    }

    public void destroy() {
        currentScene.destroy();
    }
}
