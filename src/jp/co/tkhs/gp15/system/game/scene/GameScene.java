package jp.co.tkhs.gp15.system.game.scene;

import jp.co.tkhs.gp15.system.game.GameObjectImpl;

/**
 * ゲームシーンの基底クラス
 * 
 * @author tokiha0s
 * 
 */
public abstract class GameScene extends GameObjectImpl {

    @Override
    protected final void renderProcess() {
        // シーンそのものは何も描写しない
        return;
    }
}
