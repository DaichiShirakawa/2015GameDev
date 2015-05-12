package jp.co.tkhs.gp15.content.title;

import static jp.co.tkhs.gp15.system.Constants.*;
import jp.co.tkhs.gp15.system.game.character.impl.GameCharacterImpl;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * タイトルの背景
 * 
 * @author tokiha0s
 *
 */
class TitleBackGround extends GameCharacterImpl {
    public TitleBackGround() {
        setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "title.png"));
        setX(CENTER_X);
        setY(CENTER_Y);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    @Override
    protected boolean canDisposeTexture() {
        return true;
    }

}
