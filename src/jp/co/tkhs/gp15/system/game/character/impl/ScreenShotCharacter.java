package jp.co.tkhs.gp15.system.game.character.impl;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 生成された時点のスクリーンショットテクスチャをもつキャラクター
 * 
 * @author tokiha0s
 *
 */
public class ScreenShotCharacter extends GameCharacterImpl {

    public ScreenShotCharacter() {
        setTexture(TextureLoader.getScreenShot());
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setX(CENTER_X);
        setY(CENTER_Y);
    }

    @Override
    protected boolean canDisposeTexture() {
        return true;
    }
}
