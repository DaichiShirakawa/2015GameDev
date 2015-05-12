package jp.co.tkhs.gp15.system.game.scene;

import jp.co.tkhs.gp15.content.gameover.GameOverScene;
import jp.co.tkhs.gp15.content.title.TitleScene;
import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.system.io.Key;

public enum Scenes {
    TITLE(Key.ESCAPE, TitleScene.class),
    TOKI_SHOOTING(Key.NULL, TokiShootingScene.class),
    GAMEOVER(Key.NULL, GameOverScene.class);

    private final Key trigger;
    private Class<? extends GameScene> sceneClass;

    private Scenes(Key trigger, Class<? extends GameScene> callClass) {
        this.trigger = trigger;
        this.sceneClass = callClass;
    }

    public GameScene newInstance() {
        try {
            return sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println(sceneClass.getName() + " クラスのインスタンス生成に失敗");
            e.printStackTrace();
            return null;
        }
    }

    public Key getTrigger() {
        return trigger;
    }
}
